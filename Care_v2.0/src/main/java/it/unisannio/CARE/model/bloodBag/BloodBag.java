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
 * La classe BloodBag contiene tutte le informazioni sulla sacca di sangue registrata, come il seriale, la data di creazione e scadenza
 * ed il codice donatore.
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
     * Metodo costruttore per la creazione della sacca
     * @param bloodGroup  Oggetto che viene passato come paramentro per indicare a quale gruppo sanguigno appartiene 
     * @param donatorCF  coodice fiscale del donatore
     * @exception ParseException
     * @throws  
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
     * Metodo costruttore per la creazione della sacca
     * @param serial  E' il Seriale che riconosce in modo univoco la sacca
     * @param valueOf   Enumeratore: specifica il gruppo sanguinio tra i gruppi definiti
     * @param cd  Data di creazione della sacca
     * @param ed  Data di scadenza della sacca
     * @param donatorCF2  Codice fiscale del donatore
     * @param valueOf2   Enumeratore: specifica lo stato di una sacca
     * @param note2  note riguardo la sacca
     **************************************************************************
     */
	public BloodBag(Serial serial, BloodGroup valueOf, Date cd, Date ed, String donatorCF, 
			BloodBagState valueOf2, String note2) {
		this.serial = serial;
		this.setBloodGroup(valueOf);
		this.creationDate =cd;
		this.expirationDate=ed;
		this.setDonatorCF(donatorCF);
		this.note=note2;		/* TODO: gestirlo con classe Optional per evitare NullPointerException */
		this.bloodBagState=valueOf2;
	}

	/**
     **************************************************************************
     * Metodo GET per ottenere il seriale 
     * @return String ritorna il seriale della sacca
     **************************************************************************
     */
	public Serial getSerial(){ 
		return this.serial; 
		}
	
	/**
     **************************************************************************
     * Metodo SET per modificare il seriale di una sacca
     * @param serial  Nuovo seriale della sacca
     **************************************************************************
     */
	private void setSerial(Serial serial){
		Serial.validateSerial(serial.toString());
		this.serial = serial;
	}
	
	
	/**
     **************************************************************************
     * Metodo GET per ottenere il gruppo della sacca di sangue
     * @return bloodGruop ritorno dell gruppo sanguineo della sacca selezionata
     **************************************************************************
     */
	public BloodGroup getBloodGroup(){ 
		return this.bloodGroup; 
	}
	
	/**
     **************************************************************************
     * Metodo privato per modificare il gruppo della sacca di sangue 
     * @param bloodGroup  nuovo gruppo sanguineo della sacca
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
     * Metodo privato per creare una data 
     * @exception ParseException
     * @return viene ritornata la nuova data di creazione
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
     * @return ritorna la data di creazione della sacca registrata
     **************************************************************************
     */
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	
	
	/**
     **************************************************************************
     * Metodo privato per creare una data 
     * @exception ParseException
     * @param creationDate  nuova data di creazione della sacca
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
     * Metodo privato per creare una data di scadenza
     * @exception ParseException
     * @return ritorna la nuova data di scadenza 
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
     * @return ritorna la data di scadenza della sacca selezionata
     **************************************************************************
     */
	public Date getExpirationDate() {
		return this.expirationDate;
	}
	
	
	/**
     **************************************************************************
     * Metodo per ottenere la data di scadenza di una sacca
     * @return format1.format( (TemporalAccessor) this.expirationDate)
     **************************************************************************
     */
	public String getExpirationDateS() {
		DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
		return format1.format( (TemporalAccessor) this.expirationDate);
	}
	
	/**
     **************************************************************************
     * Metodo per ottenere la data di creazione di una sacca
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
     * @param expirationDate  nuova data di scadenza
     **************************************************************************
     */
	public void setExpirationDate(Date expirationDate) { 
		if(!expirationDate.after(creationDate)) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"expirationDate "+expirationDate+" not > creationDate "+creationDate );
		this.expirationDate = expirationDate;
	}
	
	/**
     **************************************************************************
     * Metodo per ottere il codice fiscale del donatore
     * @return ritorna il codice fiscalde del donatore della sacca di sangue
     **************************************************************************
     */
	public String getDonatorCF() { return this.donatorCF; }
	
	/**
     **************************************************************************
     * Metodo privato per modificare il codice fiscale
     * @exception IllegalArgumentException
     * @param fisCode  codice fiscale del donatore
	 * @throws NullPasswordException 
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
     * Metodo GET per ottenere il tipo di sangue
     * @return ritorna l'oggetto bloodGroup che si riferisce al tipo di sacca di sangue slezionato
     **************************************************************************
     */
	public BloodGroup getBloodType() {
		return this.bloodGroup;
	}


	
	/**
     **************************************************************************
     * Metodo GET per ottenere le note riguardo la sacca 
     * @return ritorna le note scritte per quanto riguarda la sacca di sangue
     **************************************************************************
     */
	public String getNote() { return note;}	
	
	
	/**
     **************************************************************************
     * Metodo protetto per modificare le note della sacca
     * @param note  Note riguardo informazioni aggiuntive sulla sacca di sangue
     **************************************************************************
     */
	public void setNote(String note) {
		this.note=note;			// overwrites notes
	}
	
	/**
     **************************************************************************
     * Metodo per aggiungere le note alla sacca
     * @param note Note riguardo informazioni aggiuntive sulla sacca di sangue
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
     * @return ritorna la stato della sacca di sangue
     **************************************************************************
     */
	public BloodBagState getBloodBagState() {
		return this.bloodBagState;
	}
	
	
	
	/**
     **************************************************************************
     * Metodo per modificare lo stato delle sacche 
     * @return false se la sacca di sangue non è disponibile: è stata usata, trasferita o eliminata.
     **************************************************************************
     */
	public void setBloodBagState(BloodBagState s) {
		this.bloodBagState = s;
	}

	
	
	/**
     **************************************************************************
     * Metodo TO String per ottenere tutte le informazioni della sacca 
     * @return ritorna il formato di stringa tutte le informazioni della sacca selezionata
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
     * Metodo EQUALS per verificare che due sacche siano uguali
     * @param obj Viene passata come oggetto la sacca da paragonare
     * @return ritorna falso in caso in cui le sacche paragonate non sono uguali 
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
     * @return ritorna le informazioni della sacca in formato xml
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
	 * @param bloodBag Viene passato come oggetto una sacca di dangue
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
	

	
	/**
	 **************************************************************************
	 * Metodo per ottenere un bean
	 * @return ritorna un bean ovvero un'oggetto contentente dei tipi di dato primitivi
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