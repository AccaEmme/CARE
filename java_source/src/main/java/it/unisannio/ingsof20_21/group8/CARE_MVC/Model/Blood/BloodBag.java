package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BloodBag {
	public BloodBag(Blood b) throws ParseException {
		this.serial=new Serial(b);
		this.blood=b;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
	}

	public BloodBag(Blood b, String donatorID) throws ParseException {
		this.serial=new Serial(b);
		this.blood=b;
		this.creationDate=this.getCreationDate();
		this.expirationDate=this.getExpirationDate();
		this.donatorID=donatorID;
	}
	
	public Serial getSerial() {
		return this.serial;
	}
	
	public Blood getBloodType() {
		return this.blood;
	}

	public void setDonatorID(String donatorID) {
		this.donatorID=donatorID;
	}
	
	public String getDonatorID() {
		return this.donatorID;
	}
	
	public Date getCreationDate() throws ParseException {
		// We can't allow user set a wrong "creation date", so the system will stamp the right updated date.
		String datestr = serial.toString().substring(
							serial.toString().length()-13, 	// extract date from serial
							serial.toString().length()-5	// extract date from serial
						 );
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		Date date = ft.parse(datestr);
		//System.out.println( "->"+ft.format(date) );
		return date;
	}
	
	public Date getExpirationDate() throws ParseException {
		// We can't allow user set a wrong "expiration date", so the system will stamp the right updated date.
		Calendar cal = Calendar.getInstance();
		cal.setTime( this.getCreationDate() );
		//cal.setTime( new SimpleDateFormat("yyyyMMdd").parse("20210131") );
		//cal.setTime( new SimpleDateFormat("yyyyMMdd").parse("20211231") );
		cal.add(Calendar.MONTH, 1);
		Date dateobj =  cal.getTime();
		//SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
		//System.out.println( "scadenza:"+ft.format(dateobj) );
		return dateobj;
	} 
	
	@Override
	public String toString() {
		return "BloodBag [serial=" + serial + ", group=" + blood /*+ ", expireDate=" + expireDate + */+"]";
	}

	private final Serial 	serial;
	private final Blood 	blood;
	private final Date		creationDate, expirationDate;
	private String			donatorID=null;
}
