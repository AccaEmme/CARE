package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node;

import java.util.LinkedList;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class Node {
	public Node(String codstr, String denominazione, Location magazzino) {
		this.codstr = codstr;
		this.denominazione = denominazione;
		this.magazzino = magazzino;
        //this.bloodBags = new LinkedList<BloodBag>();
	}
	/*
	 * TODO: ArrayList<User> impiegati bisogna inserirli?
	 */
	
	String codstr, denominazione;
	Location magazzino;
	//private LinkedList<BloodBag> bloodBags;
}
