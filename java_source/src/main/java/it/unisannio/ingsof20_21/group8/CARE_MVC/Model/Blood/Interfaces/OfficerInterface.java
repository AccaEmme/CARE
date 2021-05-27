package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces;

import java.util.Date;
import java.util.Iterator;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag.BloodBagState;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;

public interface OfficerInterface {
	
	public boolean checkBloodBag(BloodBag b);
	public boolean checkBloodBag(Serial s);
	
	// per data, per nodo, per seriale, per sangue, 
	// per donatoreCF, per location, per stato
	
	public Iterator<BloodBag> getBloodBagsByNode(Node n);
	public Iterator<BloodBag> getBloodBagsBetweenCreationDates(Date from, Date to); // se oggi è 26/05/2021, voglio tutte le sacche dal 25/05/2021
	public Iterator<BloodBag> getBloodBagsBetweenExpirationDates(Date from, Date to);
	public Iterator<BloodBag> getBloodBagsByBloodGroup(BloodGroup blood);
	public Iterator<BloodBag> getBloodBagsByDate(Date d);
	public Iterator<BloodBag> getBloodBagsByState(BloodBagState state);
	public Iterator<BloodBag> getBloodBagsByDonatorCF(String donatorCF);	// deve poter accettare regex
	
	/*
	tutti i metodi di getBloodBags escludono a priori le sacche che già sono expired, deve usare solo quelle disponibili.
	Forse è meglio evitare lo stato "transferred", perché quando dal nodoA viene traferita al nodoB
	*/
	
	public Iterator<Blood> canDonate(Blood b);
	public Iterator<Blood> canReceive(Blood b);
	public compareBags(Bag b1, Bag b2);
}
