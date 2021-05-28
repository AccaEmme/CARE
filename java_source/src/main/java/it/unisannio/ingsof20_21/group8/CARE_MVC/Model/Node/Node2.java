package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node;

import java.util.LinkedList;
import java.util.Scanner;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.BloodBagCloneException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class Node2 {

	public Node2(String nodeCode, String nodeName, String country, String region, String province) {
		this.nodeCode = nodeCode;
		this.nodeName = nodeName;
		this.country = country;
		this.region = region;
		this.province = province;
        this.bloodBags = new LinkedList<BloodBag>();
	}
	
	public static Node2 read(Scanner sc) {
		
		if(!sc.hasNext()) return null;
		String nodeCodeR = sc.next();
		if(!sc.hasNext()) return null;
		String nodeNameR = sc.next();
		if(!sc.hasNext()) return null;
		String countryR = sc.next();
		if(!sc.hasNext()) return null;
		String regionR = sc.next();
		if(!sc.hasNext()) return null;
		String provinceR = sc.next();
		
		return new Node2(nodeCodeR, nodeNameR, countryR, regionR, provinceR);
	}
	
	/*
	 * TODO: ArrayList<User> impiegati bisogna inserirli?
	 * 
	 * Omni-star: si penso che abbia senso inserire impiegati visto che dobbiamo anche
	 * tracciare il luogo da cui avvengono le modifiche.
	 */
	
	public String getNodeCode() { return nodeCode;}

	public String getNodeName() { return nodeName;}
	
	public String geCountry() { return country;}
	
	public String getRegion() { return region;}
	
	public String getProvince() { return province;}
	
	public LinkedList<BloodBag> getBloodBags() { return bloodBags;}
	
	
	
	public void addBloodBag(BloodBag bloodBag) throws BloodBagCloneException {
		
		if(!bloodBags.contains(bloodBag)) 
			bloodBags.add(bloodBag);
		else throw new BloodBagCloneException("Impossibile aggiungere una sacca che già presente.");
	}

	public void print() {
		
		System.out.println("Codice nodo: " +nodeCode+ ";\nDenominazione: " +nodeName+ ";\nNazione: " +country+ ";\nRegione: " +region+ ";\nProvincia: " +province+ ".\n");
	}
	
	private String nodeCode, nodeName, country, region, province;
	private LinkedList<BloodBag> bloodBags;
}

