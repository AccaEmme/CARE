package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces;

import java.text.ParseException;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodGroup;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Serial;

public interface BloodBagInterface {

	/*
	 * Per scelta implementativa l'interfaccia di BloodBag espone soltanto i metodi getter.
	 * I metodi di set non vengono esposti.
	 */
	
	public Serial getSerial();
	public BloodGroup getBloodType();
	public Date getCreationDate() throws ParseException;
	public Date getExpirationDate() throws ParseException;
	public String getDonatorCF();
	
	
	/*
	 * @TODO: mettere protected tutti i metodi della classe.
	 */
}
