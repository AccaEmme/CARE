package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.text.ParseException;
import java.util.Date;


public interface BloodBagInterface {

	/*
	 * Per scelta implementativa l'interfaccia di BloodBag espone soltanto i metodi getter.
	 * I metodi di set non vengono esposti.
	 */
	
	Serial 		getSerial();
	BloodGroup 	getBloodType();
	Date 		getCreationDate() throws ParseException;
	Date 		getExpirationDate() throws ParseException;
	String 		getDonatorCF();
	
	
	/*
	 * Il manager:
	 * BloodBagInterface b = new BloodBag();
	 * 
	 */
	
	/*
	 * @TODO: mettere protected tutti i metodi della classe.
	 */
}
