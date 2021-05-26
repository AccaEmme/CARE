package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Location.Location;

public class BloodBagManager2 {
	
	private BloodBagManager2(LinkedList<BloodBag> bloodBags, HashMap<String, Location> locations) {
		
		
		this.bloodBags = bloodBags;
		this.locations = locations;
	}
	
	public BloodBagManager2() {
		
		this.bloodBags = new LinkedList();
		this.locations = new HashMap();
	}
	
	public boolean addBloodBag(BloodBag bloodBag) {
		
		bloodBags.add(bloodBag);
	}
	
	public boolean useBag(BloodBag bloodBag) {
		
		bloodBags.get(bloodBags.indexOf(bloodBag)).useBag();;
	}
	
	public Iterator<BloodBag> getBloodBags(){
		
		return bloodBags.iterator();
	}
	
	private LinkedList<BloodBag> bloodBags;
	private HashMap<String, Location> locations;

}
