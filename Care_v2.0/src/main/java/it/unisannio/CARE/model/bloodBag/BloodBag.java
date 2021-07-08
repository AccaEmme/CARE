package it.unisannio.CARE.model.bloodBag;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.bson.Document;

import it.unisannio.CARE.model.exceptions.IllegalDateException;
import it.unisannio.CARE.model.exceptions.NullPasswordException;
import it.unisannio.CARE.model.util.Constants;
import it.unisannio.ingsof20_21.group8.Care.Spring.BloodBagDAO;

/**
  * The BloodBag class contains all the information about the registered blood bag, such as the serial number, creation date and expiration date
  * and the donor code.
 */

public class BloodBag implements Cloneable, Comparable<BloodBag>{
	private Serial				serial;
	private BloodGroup 			bloodGroup;
	private Date				creationDate;
	private Date 				expirationDate;
	private String				donatorCF; //=null;	 TODO: *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
	private BloodBagState 		bloodBagState;
	private String 				note;
	
	public static final int 	monthIncrementAmount = 1;
	private static final String ELEMENT_GROUP 			= "BloodGroup";
    private static final String ELEMENT_SERIAL 			= "serial";
    private static final String ELEMENT_CREATIONDATE 	= "creationDate";
    private static final String ELEMENT_EXPIRATIONDATE 	= "expirationDate";
    private static final String ELEMENT_DONATORCF 		= "donatorCF";
    private static final String ELEMENT_BLOODBAGSTATE 	= "bloodBagState";
    private static final String ELEMENT_NOTE 			= "note";


    
    /**
     **************************************************************************
      * Constructor method for creating the bag
      * @param bloodGroup Object that is passed as a parameter to indicate which blood group it belongs to
      * @param donatorCF tax code of the donor
      * @throws ParseException
     **************************************************************************
     */
	public BloodBag(BloodGroup bloodGroup, String donatorCF) throws ParseException {
		/* Attenzione: new BloodBag() va usato solo per creare sacche perché incrementa il seriale. */ 
		this.serial 		= new Serial(bloodGroup);
		this.setBloodGroup	  ( bloodGroup ) ;
		this.setCreationDate  ( this.generateCreationDate() );
		this.setExpirationDate( this.generateExpirationDate() );
		this.setDonatorCF(donatorCF);
		this.setNote("");		/* TODO: gestirlo con classe Optional per evitare NullPointerException */
		this.setBloodBagState( BloodBagState.Available );
	}
		
	/**
     **************************************************************************
      * Constructor method for creating the bag
      * @param serial It is the Serial that uniquely recognizes the bag
      * @param valueOf Enumerator: specifies the blood group among the defined groups
      * @param cd Date of creation of the bag
      * @param ed Bag expiration date
      * @param donatorCF Tax code of the donor
      * @param valueOf2 Enumerator: specifies the status of a bag
      * @param note2 notes about the bag
     **************************************************************************
     */
	public BloodBag(Serial serial, BloodGroup valueOf, Date cd, Date ed, String donatorCF, 
			BloodBagState valueOf2, String note2) {
		this.setSerial(serial);
		this.setBloodGroup(valueOf);
		this.creationDate =cd;
		this.expirationDate=ed;
		this.setDonatorCF(donatorCF);
		this.note=note2;		/* TODO: gestirlo con classe Optional per evitare NullPointerException */
		this.bloodBagState=valueOf2;
	}

	/**
     **************************************************************************
      * Method To get the serial
      * @return serial The new serial of the bag
     **************************************************************************
     */
	public Serial getSerial(){ 
		return this.serial; 
		}
	
	/**
     **************************************************************************
      * Method To change the serial of a bag
      * @param serial The new serial of the bag
     **************************************************************************
     */
	private void setSerial(Serial serial){
		Serial.validateSerial(serial.toString());
		this.serial = serial;
	}
	
	
	/**
     **************************************************************************
      * Method To get the blood bag assembly
      * @return bloodGruop The blood group of the selected bag
     **************************************************************************
     */
	public BloodGroup getBloodGroup(){ 
		return this.bloodGroup; 
	}
	
	/**
     **************************************************************************
      * Private method To change the blood bag group
      * @param bloodGroup The new blood group of the bag
      * @exception IllegalArgumentException
     **************************************************************************
     */
	private void setBloodGroup(BloodGroup bloodGroup) 	{
		if(bloodGroup==null) 
			throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodGroupNotValid+bloodGroup );
		this.bloodGroup = bloodGroup;
	}
	
	
	/**
     **************************************************************************
      * Private method To create a date
      * @throws ParseException
      * @return creationDate The new creation date
     **************************************************************************
     */
	private Date generateCreationDate() throws ParseException {
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
	
	
	/**
     **************************************************************************
      * Method To get the creation of the dates
      * @return creationDate The creation date of the registered bag
     **************************************************************************
     */
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	
	
	/**
     **************************************************************************
      * Private method To create a date
      * @param creationDate The new creation date of the bag
      * @throws ParseException
     **************************************************************************
     */
	public void setCreationDate(Date creationDate) throws ParseException 		{
		Calendar cal = Calendar.getInstance();
		cal.setTime( this.creationDate );
		cal.add(Calendar.DAY_OF_MONTH, -7);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
		Date dateLimitBeforeCreationDate =  cal.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime( new Date() );
	    cal2.set(Calendar.HOUR_OF_DAY, 0);
	    cal2.set(Calendar.MINUTE, 0);
	    cal2.set(Calendar.SECOND, 0);
	    cal2.set(Calendar.MILLISECOND, 0);
		Date dateLimitAfterCreationDate =  cal2.getTime();

		/* Si definisce il tempo limite di setCreationDate: non oltre -7gg prima, non futura a oggi. */
		if( creationDate.before(dateLimitBeforeCreationDate) )
			throw new IllegalDateException( 
					Constants.ExceptionIllegalArgument_BloodBagNotValid
					+"creationDate "
					+creationDate
					+" can't be < -7days "
					+dateLimitBeforeCreationDate, "/bloodbag/setdate" );
		
		if( creationDate.after(dateLimitAfterCreationDate)	)
			throw new IllegalDateException( 
					Constants.ExceptionIllegalArgument_BloodBagNotValid
					+"creationDate "
					+creationDate
					+" can't be > today "
					+dateLimitAfterCreationDate, "/bloodbag/setdate" );
		
		this.creationDate = creationDate;
	}
	

	/**
     **************************************************************************
      * Private method to create an expiration date
      * @throws ParseException
      * @return returns the new expiration date
     **************************************************************************
     */
	private Date generateExpirationDate() throws ParseException {
		// We can't allow user set a wrong "expiration date", so the system will stamp the right updated date.
		Calendar cal = Calendar.getInstance();
		cal.setTime( this.creationDate );
		//cal.setTime( new SimpleDateFormat(Constants.DATE_FORMAT).parse("20210131") );
		//cal.setTime( new SimpleDateFormat(Constants.DATE_FORMAT).parse("20211231") );
		cal.add(Calendar.MONTH, BloodBag.monthIncrementAmount);
		expirationDate =  cal.getTime();
		//SimpleDateFormat ft = new SimpleDateFormat(Constants.DATE_FORMAT);
		//System.out.println( "scadenza:"+ft.format(dateobj) );
		return expirationDate;
	} 
	
	/**
     **************************************************************************
      * GET method to get the expiration date
      * @return returns the expiration date of the selected bag
     **************************************************************************
     */
	public Date getExpirationDate() {
		return this.expirationDate;
	}
	
	
	/**
     **************************************************************************
      * Method for obtaining the expiration date of a bag
      * @return format1.format ((TemporalAccessor) this.expirationDate)
     **************************************************************************
     */
	public String getExpirationDateS() {
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return format1.format( (TemporalAccessor) this.expirationDate);
	}
	
	/**
     **************************************************************************
      * Method for obtaining the creation date of a bag
      * @return format1.format ((TemporalAccessor) this.creationDate)
     **************************************************************************
     */
	public String getCreationDateS() {
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return format1.format( (TemporalAccessor) this.creationDate);
	}
	
	/**
     **************************************************************************
      * Private method to change the expiration date
      * @exception IllegalArgumentException
      * @param expirationDate new expiration date
     **************************************************************************
     */
	public void setExpirationDate(Date expirationDate) { 
		if(!expirationDate.after(creationDate)) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"expirationDate "+expirationDate+" not > creationDate "+creationDate );
		this.expirationDate = expirationDate;
	}
	
	/**
     **************************************************************************
      * Method to obtain the donor's tax code
      * @return returns the tax code of the donor of the blood bag
     **************************************************************************
     */
	public String getDonatorCF() { return this.donatorCF; }
	
	/**
     **************************************************************************
      * Private method to change the tax code
      * @exception IllegalArgumentException
      * @param fisCode tax code of the donor
     **************************************************************************
     */

	public void setDonatorCF(String fisCode)/*throws IllegalArgumentException */ {
	if( !fisCode.matches(Constants.RegexDonatorCF) )
			throw new IllegalArgumentException();
		this.donatorCF = fisCode;
		}
/*
	private void setDonatorCF(String fisCode) {
		if( !fisCode.toUpperCase().matches(Constants.RegexDonatorCF) )
			throw new IllegalFiscalCodeException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"donatorCF "+donatorCF+" do not match pattern "+Constants.RegexDonatorCF, "/bloodbag/add" );
		this.donatorCF = fisCode.toUpperCase();

	}*/
	
	
	/**
     **************************************************************************
      * GET method to obtain the blood type
      * @return returns the bloodGroup object which refers to the type of blood bag selected
     **************************************************************************
     */
	public BloodGroup getBloodType() {
		return this.bloodGroup;
	}


	
	/**
     **************************************************************************
      * GET method to get the notes about the bag
      * @return returns the written notes regarding the blood bag
     **************************************************************************
     */
	public String getNote() { return note;}	
	
	
	/**
     **************************************************************************
      * Secure method to edit bag notes
      * @param note Notes regarding additional information on the blood bag
     **************************************************************************
     */
	public void setNote(String note) {
		this.note=note;			// overwrites notes
	}
	
	/**
     **************************************************************************
      * Method to add notes to the bag
      * @param note Notes regarding additional information on the blood bag
     **************************************************************************
     */
	public void appendNote(String note) {
		// append to existing note value.
		StringBuffer sb = new StringBuffer();
		sb.append(this.note);
		sb.append(note);
		this.note=sb.toString();
	}

	/**
     **************************************************************************
      * GET method to obtain the status of the bag
      * @return returns the status of the blood bag
     **************************************************************************
     */
	public BloodBagState getBloodBagState() {
		return this.bloodBagState;
	}
	
	
	
	/**
     **************************************************************************
      * Method to change the status of the bags
      * @param s Condition of the bag
     **************************************************************************
     */
	public void setBloodBagState(BloodBagState s) {
		this.bloodBagState = s;
	}

	
	
	/**
     **************************************************************************
      * TO String method to get all the information of the bag
      * @return returns the string format all information of the selected bag
     **************************************************************************
     */
	@Override
	public String toString() {
		return   "{\"serial\": \"" 	  			+ this.serial   		+ "\""
				+", \"creation_date\": \"" 		+ this.creationDate.getTime()	+ "\""
				+", \"donator\": \"" 			+ this.donatorCF 		+ "\""
				+", \"expiration_date\": \"" 		+ this.expirationDate.getTime() + "\""
				+", \"group\": \""  		+ this.bloodGroup 		+ "\"" 
				+", \"notes\": \"" + this.note 	+ "\""
				+", \"state\": \"" 		+ this.bloodBagState 	+ "\""
				+ "}";
	}

	/**
     **************************************************************************
      * EQUALS method to verify that two bags are the same
      * @param obj The bag to be compared is passed as an object
      * @return returns false in case the compared bags are not the same 
     **************************************************************************
     */
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BloodBag) {
			BloodBag bBag = (BloodBag) obj;
			return (
					serial.equals(bBag.getSerial()) 		&& 
					bloodGroup.equals(bBag.getBloodGroup()) && 
					donatorCF.equals(bBag.getDonatorCF()) 
					); 
		}
		return false; 
	}
	
	 /**
     **************************************************************************
      * Method for returning the bag as document xml
      * @return returns the information of the bag in xml format	
     **************************************************************************
     */
	 public Document getDocument(){
	        Document document = new Document("BloodGroup",this.getBloodGroup()).append("serial", this.getSerial())
	        		.append("creationDate",this.getCreationDateS()).append("expirationDate", this.getExpirationDateS())
	        		.append("donatorCF", this.getDonatorCF()).append("bloodBagSate", this.bloodBagState)
	        		.append("note",this.getNote());
	 
	        return document;
	    }
	 
	 /**
	 **************************************************************************
	 * Method for buying two bags
	 * @param bloodBag A blood bag is passed as an object
	 **************************************************************************
	 */
	public int compareTo(BloodBag bloodBag) {
		return this.creationDate.compareTo(bloodBag.creationDate);
	}

	/**
	 **************************************************************************
	 * Method to print all the information of the bag
	 **************************************************************************
	 */
	public void print() {		
		System.out.println("Seriale: " +serial.toString()+ ";\nGruppo: " +bloodGroup+ ";\nData Creazione: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate)+ ";\nData scadenza: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate)+ ";\nCodice fiscale donatore: " +donatorCF+ ";\nStato: " +bloodBagState+ ";\nNote: " +note+ ".\n");
	}
	

	
	/**
	 **************************************************************************
	 * Method to get a bean
	 * @return returns a bean that is an object containing primitive data types
	 **************************************************************************
	 */
	public BloodBagDAO getBean(){
		BloodBagDAO bean = new BloodBagDAO();
		bean.setSerial(this.serial.toString());
		bean.setGroup(this.bloodGroup.toString());
		bean.setDonator(this.donatorCF.toString());

		Timestamp tsCreation = new Timestamp(this.creationDate.getTime());
		Timestamp tsExpiration = new Timestamp(this.expirationDate.getTime());
		bean.setCreationDate(tsCreation.getTime());		//qui abbiamo i millisecondi
		bean.setExpirationDate(tsExpiration.getTime());
		bean.setState(this.bloodBagState.toString());
		bean.setNotes(this.note);

		return bean;
	}
}