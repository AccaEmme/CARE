package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces;

import java.util.Date;
import java.util.Iterator;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag.BloodBagState;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;

public interface OfficerInterface {
	
	public boolean checkBloodBag(BloodBag b);
	public boolean checkBloodBag(Serial s);
	
	// per seriale, per nodo, per sangue, per data, per nodo 
	// per donatoreCF, per location, per stato
	
	public void requestBloodBag(BloodBag b);
	public void responseBloodBag(BloodBag b);
	//public Iterator<Requests> getRequestList();
	//public Iterator<Responses> getResponsesList();
	
	public Iterator<BloodBag> getBloodBagsBySerial(Serial s);
	public Iterator<BloodBag> getBloodBagsByNode(Node n);
	public Iterator<BloodBag> getBloodBagsByBloodGroup(BloodGroup blood);
	public Iterator<BloodBag> getBloodBagsByDonatorCF(String donatorCF);				// deve poter accettare regex
	public Iterator<BloodBag> getBloodBagsByLocation(Location location);
	public Iterator<BloodBag> getBloodBagsByState(BloodBagState state);
	public Iterator<BloodBag> getBloodBagsByNote(String note);							// deve poter accettare regex
	
	public Iterator<BloodBag> getBloodBagsBetweenCreationDates(Date from, Date to); 	// se oggi è 26/05/2021, voglio tutte le sacche dal 25/05/2021
	public Iterator<BloodBag> getBloodBagsBetweenExpirationDates(Date from, Date to);
	
	
	
	/*
	tutti i metodi di getBloodBags escludono a priori le sacche che già sono expired, deve usare solo quelle disponibili.
	Forse è meglio evitare lo stato "transferred", perché quando dal nodoA viene traferita al nodoB
	*/
	
	public Iterator<BloodGroup> canDonate(BloodGroup blood);
	public Iterator<BloodGroup> canReceive(BloodGroup blood);
	public short compareBags(BloodBag b1, BloodBag b2);
}
