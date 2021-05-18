package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BloodBag {
	/*
	Rendiamo obbligatorio il codice fiscale paziente
	
	public BloodBag(Blood b) throws ParseException {
		this.serial=new Serial(b);
		this.blood=b;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
	}
	*/

	public BloodBag(Blood b, String donatorCF) throws ParseException {
		this.serial=new Serial(b);
		this.blood=b;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
		this.donatorCF=donatorCF;
	}
	
	public Serial getSerial() {
		return this.serial;
	}
	
	public Blood getBloodType() {
		return this.blood;
	}

	public void setDonatorCF(String donatorCF) {
		this.donatorCF=donatorCF;
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
		cal.add(Calendar.MONTH, 1);
		expirationDate =  cal.getTime();
		//SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
		//System.out.println( "scadenza:"+ft.format(dateobj) );
		return expirationDate;
	} 
	
	@Override
	public String toString() {
		return "BloodBag [serial=" + serial + ", group=" + blood /*+ ", expireDate=" + expireDate + */+"]";
	}

	private final Serial 	serial;
	private final Blood 	blood;
	private Date			creationDate;
	private Date 			expirationDate;
	private String			donatorCF; //=null;	 *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
}
