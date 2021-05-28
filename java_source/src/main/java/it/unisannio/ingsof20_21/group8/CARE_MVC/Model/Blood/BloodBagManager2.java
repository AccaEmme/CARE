package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.BloodBagCloneException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.NodeNotFoundException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StateException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.*;

public class BloodBagManager2 {
	
	public BloodBagManager2(Scanner nodeSc, Scanner bloodSc) {
		
		this.bloodBags = new HashSet();
		this.nodes = new HashMap();
		
		Node2 node = Node2.read(nodeSc);
		while(node != null) {
			
			nodes.put(node.getNodeCode(), node);
			node = Node2.read(nodeSc);
		}
		
		BloodBag bloodBag = BloodBag.read(bloodSc);
		while(bloodBag != null) {
			
			try {
					Node2 n = searchNodeByCode(bloodBag.getNodeCode());
					bloodBag.setNode(n);
					n.addBloodBag(bloodBag);
					bloodBags.add(bloodBag);
			}catch(NodeNotFoundException e) {
				
				e.printStackTrace();
				System.err.println(e.getMessage());
			}catch(BloodBagCloneException e) {
				
				e.printStackTrace();
				System.err.println(e.getMessage());
			}
					bloodBag = BloodBag.read(bloodSc);
		}
		
	}
	
	private BloodBagManager2(HashSet<BloodBag> bloodBags, HashMap<String, Node2> nodes) {
		
		
		this.bloodBags = bloodBags;
		this.nodes = nodes;
	}
	
	public Node2 searchNodeByCode(String nodeCode) throws NodeNotFoundException{
		
		Set<String> keySet = nodes.keySet();
		for(String nodeCodeR : keySet) {
		
			if(nodeCode.equals(nodeCode)) return nodes.get(nodeCode);
				
		}
		throw new NodeNotFoundException("Il nodo avente codice: " +nodeCode+ " non è stato trovato o è inesistente.");
	}
	
	public BloodBagManager2 filteredByProvince(String province) {
		
		HashSet<BloodBag> bloodBagsF = new HashSet<>();
		HashMap<String, Node2> nodesF = new HashMap<>();
		
		Set<String> keySet = nodes.keySet();
		for(String nodeCodeR : keySet) {
		
			Node2 node = nodes.get(nodeCodeR);
			if(node.getProvince().equals(province)) {
				
				nodesF.put(nodeCodeR, node);
				for(BloodBag bloodBag : node.getBloodBags()) {
					
					bloodBagsF.add(bloodBag);
				}
			}
				
		}
		
		return new BloodBagManager2(bloodBagsF, nodesF);
	}
	
	public void addBloodBag(BloodBag bloodBag) throws BloodBagCloneException{
		
		Node2 node = nodes.get(bloodBag.getNodeCode());
		node.addBloodBag(bloodBag);
		bloodBags.add(bloodBag);
		nodes.put(bloodBag.getNodeCode(), node);
		
	}
	
	public void useBag(BloodBag bloodBag) {
		
		for(BloodBag bg : bloodBags) {
		
			if(bg.equals(bloodBag)) {
				try {
					bg.useBag();
				}catch(StateException e) {
					
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void transferBag(BloodBag bloodBag, Node2 node) {
		
		for(BloodBag bg : bloodBags) {
			
			if(bg.equals(bloodBag) && !(bg.getNode().equals(node)) && bg.checkState()) {
				try {
					
					bg.setNode(node);
					BloodBag bg2 = bg.clone();
					bg.transferBag();
					bloodBags.add(bg2);
				}catch(StateException e) {
					
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
	}
	
	public void dropBag(BloodBag bloodBag) {
		
		for(BloodBag bg : bloodBags) {
			
			if(bg.equals(bloodBag) && bg.checkState()) {
				try {
					
					bg.dropBag();
				}catch(StateException e) {
					
					System.err.println(e);
					e.printStackTrace();
				}
			}
		}
	}
	
	public Iterator<BloodBag> getBloodBags(){
		
		return bloodBags.iterator();
	}
	
	public void printBloodBags() {
		
		System.out.println("\n------------SACCHE------------\n");
		
		for(BloodBag bloodBag : bloodBags) {
			
			bloodBag.print();
		}
	}
	
	public void printNodes() {
		
		System.out.println("\n------------NODI------------\n");
		
		Set<String> keySet = nodes.keySet();
			for(String nodeCodeR : keySet) {
					
				nodes.get(nodeCodeR).print();
			}
	}
	
	public void print() {
		
		printNodes();
		printBloodBags();
	}
	
	private HashSet<BloodBag> bloodBags;
	private HashMap<String, Node2> nodes;

}
