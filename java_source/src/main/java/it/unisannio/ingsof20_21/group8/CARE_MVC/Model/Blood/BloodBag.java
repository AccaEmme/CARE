package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BloodBag {

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
		this.serial=new Serial(bloodGroup);
		this.bloodGroup =bloodGroup;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
		this.donatorCF=donatorCF;
	}
	
	public BloodBag(BloodGroup bloodGroup, String donatorCF, String note) throws ParseException {
		this.serial=new Serial(bloodGroup);
		this.bloodGroup =bloodGroup;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
		this.donatorCF=donatorCF;
		this.note = note;
	}
	
	protected Serial getSerial() {
		return this.serial;
	}
	
	public BloodGroup getBloodType() {
		return this.bloodGroup;
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
	
	@Override
	public String toString() {
		return "BloodBag [serial=" + serial + ", group=" + bloodGroup /*+ ", expireDate=" + expireDate + */+"]";
	}

	public BloodGroup getBlood() {
		return bloodGroup;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	private final Serial 	serial;
	private final BloodGroup bloodGroup;
	private Date			creationDate;
	private Date 			expirationDate;
	private String			donatorCF; //=null;	 *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
	private String 			note;

	public static final int monthIncrementAmount = 1;
}
