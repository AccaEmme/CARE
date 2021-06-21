package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node;

import java.util.Iterator;
import java.util.LinkedList;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.BloodBagCloneException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public class Node {
	public Node(String codstr, String nodeName, Location warehouse) {
		this.codstr = codstr;
		this.nodeName = nodeName;
		this.warehouse = warehouse;
        this.bloodBags = new LinkedList<BloodBag>();
	}
	
	public String getCodstr() {
		return codstr;
	}
	
	public String getNodeName() {
		return nodeName;
	}
	
	public Location getWarehouse() {
		return warehouse;
	}
	
	public Iterator<BloodBag> getBloodBagsIterator() { return bloodBags.iterator();}

	
	public void setCodstr(String codstr) {
		this.codstr = codstr;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public void setWarehouse(Location warehouse) {
		this.warehouse = warehouse;
	}
	
	public void addBloodBag(BloodBag bloodBag) throws BloodBagCloneException {
		
		if(!bloodBags.contains(bloodBag)) 
			bloodBags.add(bloodBag);
		else throw new BloodBagCloneException("Impossibile aggiungere una sacca che già presente.");
	}


	private String codstr, nodeName;
	private Location warehouse;
	private LinkedList<BloodBag> bloodBags;
}
