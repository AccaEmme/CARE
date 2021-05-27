package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node;

import java.util.LinkedList;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class Node {
	public Node(String codstr, String nodeName, Location warehouse) {
		this.codstr = codstr;
		this.nodeName = nodeName;
		this.warehouse = warehouse;
        //this.bloodBags = new LinkedList<BloodBag>();
	}
	
	
	
	/*
	 * TODO: ArrayList<User> impiegati bisogna inserirli?
	 */
	
	public String getCodstr() {
		return codstr;
	}
	
	public void setCodstr(String codstr) {
		this.codstr = codstr;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}



	public Location getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(Location warehouse) {
		this.warehouse = warehouse;
	}


	String codstr, nodeName;
	Location warehouse;
	//private LinkedList<BloodBag> bloodBags;
}
