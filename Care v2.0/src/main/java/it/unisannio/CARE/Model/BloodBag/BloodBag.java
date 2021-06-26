package it.unisannio.CARE.Model.BloodBag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.bson.Document;

import it.unisannio.CARE.Model.BloodBag.*;
import it.unisannio.CARE.Model.Exceptions.StateException;
import it.unisannio.CARE.Model.Util.Constants;



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


	public BloodBag(BloodGroup bloodGroup, String donatorCF) throws ParseException {
		/* Attenzione: new BloodBag() va usato solo per creare sacche perché incrementa il seriale. */ 
		this.serial 		= new Serial(bloodGroup);
		this.setBloodGroup	  ( bloodGroup );
		this.setCreationDate  ( this.generateCreationDate() );
		this.setExpirationDate( this.generateExpirationDate() );
		this.setDonatorCF(donatorCF);
		this.setNote("");		/* TODO: gestirlo con classe Optional per evitare NullPointerException */
		this.setBloodBagState( BloodBagState.Available );
	}
		
	/**
     **************************************************************************
     * Metodo costruttore per creare la sacca di sangue 
     * @param Serial serial, BloodGroup valueOf, Date cd, Date ed, String donatorCF2, Node n1,
			BloodBagState valueOf2, String note2
     **************************************************************************
     */
	public BloodBag(Serial serial, BloodGroup valueOf, Date cd, Date ed, String donatorCF2, 
			BloodBagState valueOf2, String note2) {
		this.serial 		= serial;
		this.bloodGroup=valueOf;
		this.creationDate =cd;
		this.expirationDate=ed;
		this.donatorCF=donatorCF2;
		this.note=note2;		/* TODO: gestirlo con classe Optional per evitare NullPointerException */
		this.bloodBagState=valueOf2;
	}

	/**
     **************************************************************************
     * Metodo GET per ottenere il seriale 
     * @return serial
     **************************************************************************
     */
	public Serial getSerial(){ 
		return this.serial; 
		}
	
	/**
     **************************************************************************
     * Metodo SET per modificare il seriale di una sacca
     * @param Serial serial
     **************************************************************************
     */
	private void setSerial(Serial serial){
		Serial.validateSerial(serial.toString());
		this.serial = serial;
	}
	
	
	/**
     **************************************************************************
     * Metodo GET per ottenere il gruppo della sacca di sangue
     * @return bloodGruop
     **************************************************************************
     */
	public BloodGroup getBloodGroup(){ 
		return this.bloodGroup; 
	}
	
	/**
     **************************************************************************
     * Metodo privato per modificare il gruppo della sacca di sangue 
     * @param BloodGroup bloodGroup
     * @exception IllegalArgumentException
     **************************************************************************
     */
	private void setBloodGroup(BloodGroup bloodGroup) 	{
		if(bloodGroup==null) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodGroupNotValid+bloodGroup );
		this.bloodGroup = bloodGroup;
	}
	
	
	/**
     **************************************************************************
     * Metodo privato per creare una data 
     * @exception ParseException
     * @return creationDate
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
     * Metodo GET per ottenere la creazione delle date
     * @return creationDate
     **************************************************************************
     */
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	
	
	/**
     **************************************************************************
     * Metodo privato per creare una data 
     * @exception ParseException
     * @return creationDate
     **************************************************************************
     */
	private void setCreationDate(Date creationDate) throws ParseException 		{
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
			throw new IllegalArgumentException( 
					Constants.ExceptionIllegalArgument_BloodBagNotValid
					+"creationDate "
					+creationDate
					+" can't be < -7days "
					+dateLimitBeforeCreationDate );
		
		if( creationDate.after(dateLimitAfterCreationDate)	)
			throw new IllegalArgumentException( 
					Constants.ExceptionIllegalArgument_BloodBagNotValid
					+"creationDate "
					+creationDate
					+" can't be > today "
					+dateLimitAfterCreationDate );
		
		this.creationDate = creationDate;
	}
	

	/**
     **************************************************************************
     * Metodo privato per creare una data di scadenza
     * @exception ParseException
     * @return expirationDate
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
     * Metodo GET per ottenere la data di scadenza
     * @return expirationDate
     **************************************************************************
     */
	public Date getExpirationDate() {
		return this.expirationDate;
	}
	
	
	/**
     **************************************************************************
     * Metodo per creare la data di scadenza S?
     * @return format1.format( (TemporalAccessor) this.expirationDate)
     **************************************************************************
     */
	public String getExpirationDateS() {
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return format1.format( (TemporalAccessor) this.expirationDate);
	}
	
	/**
     **************************************************************************
     * Metodo per ottenere la data di scadenza S?
     * @return expirationDate
     **************************************************************************
     */
	public String getCreationDateS() {
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return format1.format( (TemporalAccessor) this.creationDate);
	}
	
	/**
     **************************************************************************
     * Metodo privato per modificare la data di scadenza
     * @exception IllegalArgumentException
     * @param Date expirationDate
     **************************************************************************
     */
	private void setExpirationDate(Date expirationDate) { 
		if(!expirationDate.after(creationDate)) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"expirationDate "+expirationDate+" not > creationDate "+creationDate );
		this.expirationDate = expirationDate;
	}
	
	/**
     **************************************************************************
     * Metodo per ottere il codice fiscale del donatore
     * @return donatorCF
     **************************************************************************
     */
	public String getDonatorCF() { return this.donatorCF; }
	
	/**
     **************************************************************************
     * Metodo privato per modificare il codice fiscale
     * @exception IllegalArgumentException
     * @param String fisCode
     **************************************************************************
     */
	private void setDonatorCF(String fisCode) {
		if( !fisCode.matches(it.unisannio.CARE.Model.Util.Constants.RegexDonatorCF) )
			throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"donatorCF "+donatorCF+" do not match pattern "+Constants.RegexDonatorCF );
		this.donatorCF = fisCode;
	}
	
	
	
	public BloodGroup getBloodType() {
		return this.bloodGroup;
	}


	
	/**
     **************************************************************************
     * Metodo GET per ottenere le note riguardo la sacca 
     * @return note
     **************************************************************************
     */
	public String getNote() { return note;}	
	
	
	/**
     **************************************************************************
     * Metodo protetto per modificare le note della sacca
     * @param String note
     **************************************************************************
     */
	protected void setNote(String note) {
		this.note=note;			// overwrites notes
	}
	
	/**
     **************************************************************************
     * Metodo per aggiungere le note alla sacca
     * @param String note
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
     * Metodo GET per ottenere lo stato della sacca
     * @return bloodBagState
     **************************************************************************
     */
	public BloodBagState getBloodBagState() {
		return this.bloodBagState;
	}
	

	/**
     **************************************************************************
     * Metodo per verificare lo stato della sacca
     * @return false se la sacca di sangue non è disponibile: è stata usata, trasferita o eliminata.
     **************************************************************************
     */
	public boolean checkBloodBagState() {
		return (bloodBagState == BloodBagState.Available); //return !(bloodBagState == BloodBagState.Used || bloodBagState == BloodBagState.Transfered || bloodBagState == BloodBagState.Dropped);
	}
	
	/**
     **************************************************************************
     * Metodo per modificare lo stato delle sacche 
     * @return false se la sacca di sangue non è disponibile: è stata usata, trasferita o eliminata.
     **************************************************************************
     */
	private void setBloodBagState(BloodBagState s) {
		this.bloodBagState = s;
	}
	
	/**
     **************************************************************************
     * Metodo per il traferimento di una sacca di sangue
     * @exception StateException
     **************************************************************************
     */
	public void transferBag() throws StateException { 
		if( !checkBloodBagState() )		throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");	
		setBloodBagState(BloodBagState.Transfered);
	}
	
	/**
     **************************************************************************
     * Metodo per identificare una sacca usata
     * @exception StateException
     **************************************************************************
     */
	public void useBag() throws StateException {
		if( !checkBloodBagState() )		throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		setBloodBagState(BloodBagState.Used);
	}
	
	/**
     **************************************************************************
     * Metodo per identificare una sacca cestinata
     * @exception StateException
     **************************************************************************
     */
	public void dropBag() throws StateException {
		if( !checkBloodBagState() )		throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		bloodBagState = BloodBagState.Dropped;
	}

	/**
     **************************************************************************
     * Metodo TO String per ottenere tutte le informazioni della sacca 
     * @return serial, bloodGroup, creationDate, expirationDate, donatorCT, node, bloodbagstate, note
     **************************************************************************
     */
	@Override
	public String toString() {
		return   "{\"serial\": \"" 	  			+ this.serial   		+ "\""
				+", \"bloodGroup\": \""  		+ this.bloodGroup 		+ "\"" 
				+", \"creationDate\": \"" 		+ new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate) 	+ "\""
				+", \"expireDate\": \"" 		+ new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate) + "\""
				+", \"donatorCF\": \"" 			+ this.donatorCF 		+ "\""
				+", \"bloodBagState\": \"" 		+ this.bloodBagState 	+ "\""
				+", \"note\": \"" + this.note 	+ "\""
				+ "}";
	}

	/**
     **************************************************************************
     * Metodo EQUALS per verificare che due sacche siano uguali
     * @param Object obj
     * @return serial, bloodGroup, donatorCF, Node
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
     * Metodo per il return della sacca come document xml
     * @return document
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
	 * Metodo per comaprare due sacche 
	 * @param BloodBag bloodBag
	 **************************************************************************
	 */
	public int compareTo(BloodBag bloodBag) {
		return this.creationDate.compareTo(bloodBag.creationDate);
	}

	/**
	 **************************************************************************
	 * Metodo per stampare tutte le informazioni della sacca
	 **************************************************************************
	 */
	public void print() {		
		System.out.println("Seriale: " +serial.toString()+ ";\nGruppo: " +bloodGroup+ ";\nData Creazione: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate)+ ";\nData scadenza: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate)+ ";\nCodice fiscale donatore: " +donatorCF+ ";\nStato: " +bloodBagState+ ";\nNote: " +note+ ".\n");
	}


	public enum BloodBagState{ 
		Available,	// Sacca disponibile per essere usata o trasferita o eliminata
		Transfered,	// Sacca trasferita a un altro nodo, viene comunque lasciata l'informazione nel database del nodo trasferente nello stato "trasferita"
		Used,		// Sacca adoperata. Non utilizzabile, non trasferibile, non eliminabile
		Dropped;	// Sacca eliminata (es. per scadenza o altre motivazioni)
	}
}