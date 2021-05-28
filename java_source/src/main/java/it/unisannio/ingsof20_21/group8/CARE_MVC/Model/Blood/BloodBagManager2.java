package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StateException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.*;

public class BloodBagManager2 {
	
	private BloodBagManager2(HashSet<BloodBag> bloodBags, HashMap<String, Node> nodes) {
		
		
		this.bloodBags = bloodBags;
		this.nodes = nodes;
	}
	
	public BloodBagManager2() {
		
		this.bloodBags = new HashSet();
		this.nodes = new HashMap();
	}
	
	public boolean addBloodBag(BloodBag bloodBag) {
		
		Node node = nodes.get(bloodBag.getNodeCode());
		return (bloodBags.add(bloodBag) && nodes.put(bloodBag.getNodeCode(), node) != null);
		
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
	
	public void transferBag(BloodBag bloodBag, Node node) {
		
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
	
	public Iterator<BloodBag> getBloodBags(){
		
		return bloodBags.iterator();
	}
	
	private HashSet<BloodBag> bloodBags;
	private HashMap<String, Node> nodes;

}
