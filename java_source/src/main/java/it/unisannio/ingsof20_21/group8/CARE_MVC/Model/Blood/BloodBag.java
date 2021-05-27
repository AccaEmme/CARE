package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Location;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.InitSettings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Scanner;



public class BloodBag implements BloodBagInterface, Cloneable {

	//Rendiamo obbligatorio il codice fiscale paziente
	//mi dispiace ma devo rimettere questo costruttore perchè il database non è per nulla allineato alle classi

	/*
	public BloodBag(BloodGroup bloodGroup) throws ParseException {
		this.serial=new Serial(bloodGroup);		//usiamo le variabili che abbiano un nome comprensibile pls
		this.bloodGroup=bloodGroup;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
		this.donatorCF = "Not present right now";	//ovviamente da gestire meglio, quando le tabelle del database saranno allineate.
	}
	*/


	public BloodBag(BloodGroup bloodGroup, String donatorCF) throws ParseException {
		
		this.serial = new Serial(bloodGroup);
		this.bloodGroup = bloodGroup;
		this.creationDate = this.getCreationDate();
		this.expirationDate = this.getExpirationDate();
		this.donatorCF = donatorCF;
		this.location = new Location(/*null, null, null, null, donatorCF, donatorCF*/);
		this.bloodBagState = BloodBagState.Available;
	}
	
	public BloodBag(BloodGroup bloodGroup, String donatorCF, String note) throws ParseException {
		
		this.serial = new Serial(bloodGroup);
		this.bloodGroup = bloodGroup;
		this.creationDate = this.getCreationDate();
		this.expirationDate = this.getExpirationDate();
		this.donatorCF = donatorCF;
		this.note = note;
		this.location = new Location();
		this.bloodBagState = BloodBagState.Available;
	}
	
	public BloodBag clone(){
		
		try {
			return new BloodBag(this.bloodGroup, this.donatorCF, this.note);
		}catch(Exception e) { 
			
			e.printStackTrace(); 
			return null;
			
		}
	}
	
	public Serial getSerial() {
		return this.serial;
	}
	
	public BloodGroup getBloodType() {
		return this.bloodGroup;
	}

	public void metodofigo() {
		
	}

	public String getDonatorCF() {
		return this.donatorCF;
	}
	
	public Date getCreationDate() throws ParseException {
		// We can't allow user set a wrong "creation date", so the system will stamp the right updated date.
		String datestr = serial.toString().substring(
							serial.toString().length()-13, 	// extract date from serial
							serial.toString().length()-5	// extract date from serial
						 );
		SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
		creationDate = ft.parse(datestr);
		//System.out.println( "->"+ft.format(date) );
		return creationDate;
	}
	
	public Date getExpirationDate() throws ParseException {
		// We can't allow user set a wrong "expiration date", so the system will stamp the right updated date.
		Calendar cal = Calendar.getInstance();
		cal.setTime( this.getCreationDate() );
		//cal.setTime( new SimpleDateFormat(Constants.DATE_FORMAT).parse("20210131") );
		//cal.setTime( new SimpleDateFormat(Constants.DATE_FORMAT).parse("20211231") );
		cal.add(Calendar.MONTH, this.monthIncrementAmount);
		expirationDate =  cal.getTime();
		//SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
		//System.out.println( "scadenza:"+ft.format(dateobj) );
		return expirationDate;
	} 
	
	public BloodGroup getBlood() {
		return bloodGroup;
	}

	public String getNote() {
		return note;
	}
	
	public Location getLocation() { return location; }

	public void setNote(String note) {
		this.note = note;
	}
	
	protected void setLocation(Location locationR) { location = locationR; }
	
	/*
	 * Il metodo che verifica lo stato della sacca.
	 * @return false se la sacca di sangue non è disponibile: è stata usata, trasferita o eliminata.
	 */
	public boolean checkState() {
		
		//return !(bloodBagState == BloodBagState.Used || bloodBagState == BloodBagState.Transfered || bloodBagState == BloodBagState.Dropped);
		return (bloodBagState == BloodBagState.Available);
	}
	
	
	public boolean transferTo(Location locationR) { 
		
		if(!checkState()) return false;
		location = locationR;
		bloodBagState = BloodBagState.Transfered;
		return true;
		
	}
	
	public boolean useBag() {
		
		if(!checkState()) return false;
		bloodBagState = BloodBagState.Used;
		return true;
	}
	
	@Override
	public String toString() {
		return "BloodBag [serial=" + serial + ", group=" + bloodGroup + ", expireDate=" + new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate) + ", state = " + bloodBagState + "]\nnote: '" + note + "'";
	}
	
	public void HashCode() {
		
		
	}

	private enum BloodBagState{
		
		Available, Transfered, Used, Dropped;
	}

	private final Serial 	serial;
	private final BloodGroup bloodGroup;
	private Date			creationDate;
	private Date 			expirationDate;
	private String			donatorCF; //=null;	 *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
	private String 			note;
	private Location		location;
	private BloodBagState 	bloodBagState;
	
	public static final int monthIncrementAmount = 1;
}
