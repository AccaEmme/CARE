package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Testers;

import java.util.ArrayList;
import java.util.TreeMap;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.City;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Country;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Province;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location.Region;

public class NodeTester {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		Node n = new Node(
				"206",
				"Moscati",
				new Location(
						Country.Italy, Region.Campania,Province.Avellino,City.Avellino,"via Volpe", "55","82021"
						)
				);
		/*
		 * Filling TreeMap
		ArrayList<Integer> ABnegLimits = new ArrayList<Integer>();	ABnegLimits.add(2); ABnegLimits.add(5);
		ArrayList<Integer> ABposLimits = new ArrayList<Integer>();	ABposLimits.add(3);	ABposLimits.add(6);	
		ArrayList<Integer> AposLimits = new ArrayList<Integer>();	AposLimits.add(4); 	AposLimits.add(8);
		ArrayList<Integer> ZEROnegLimits = new ArrayList<Integer>();	AposLimits.add(5); 	AposLimits.add(7);
		
		TreeMap<BloodGroup, ArrayList<Integer>> quantities = new TreeMap();
		quantities.put(BloodGroup.ABneg, ABnegLimits);
		quantities.put(BloodGroup.ABpos, ABposLimits);
		quantities.put(BloodGroup.Apos, AposLimits);
		quantities.put(BloodGroup.ZEROneg, AposLimits);
		
		n.setAllowedQuantities(quantities);
		n.printAllowedQuantities(System.out, quantities);
		*/
		
		n.setAllowedQuantity(BloodGroup.ZEROneg, 3, 5);
		n.setAllowedQuantity(BloodGroup.ABneg, 4, 6);
		n.setAllowedQuantity(BloodGroup.ABpos, 2, 4);
		n.setAllowedQuantity(BloodGroup.Apos, 5, 8);
		n.setAllowedQuantity(BloodGroup.ABneg, 10, 20); // sovrascrive valore già esistente. Perfect
		n.printAllowedQuantities(System.out);
	}

}
