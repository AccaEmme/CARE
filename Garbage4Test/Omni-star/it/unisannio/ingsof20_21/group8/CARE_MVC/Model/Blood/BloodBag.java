package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StateException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.Node;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;



public class BloodBag implements BloodBagInterface, Cloneable, Comparable<BloodBag>{
	private Serial				serial;
	private BloodGroup 			bloodGroup;
	private Date				creationDate;
	private Date 				expirationDate;
	private String				donatorCF; //=null;	 TODO: *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
	private Node				node;
	private BloodBagState 		bloodBagState;
	private String 				note;
	
	public static final int 	monthIncrementAmount = 1;


	public BloodBag(BloodGroup bloodGroup, String donatorCF, Node node) throws ParseException {
		/* Attenzione: new BloodBag() va usato solo per creare sacche perché incrementa il seriale. */ 
		this.serial 		= new Serial(bloodGroup);
		this.setBloodGroup	  ( bloodGroup );
		this.setCreationDate  ( this.generateCreationDate() );
		this.setExpirationDate( this.generateExpirationDate() );
		this.setDonatorCF(donatorCF);
		this.setNode(node);
		this.setNote("");		/* TODO: gestirlo con classe Optional per evitare NullPointerException */
		this.setBloodBagState( BloodBagState.Available );
	}
		
	
	public Serial 		getSerial() 							{ return 			this.serial; }
	
	
	private void 		setSerial(Serial serial) 				{
		Serial.validateSerial(serial.toString());
		this.serial = serial;
	}
	
	public BloodGroup 	getBloodGroup() 						{ return 			this.bloodGroup; }
	
	private void 		setBloodGroup(BloodGroup bloodGroup) 	{
		if(bloodGroup==null) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodGroupNotValid+bloodGroup );
		this.bloodGroup = bloodGroup;
	}
		
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
	
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	private void 		setCreationDate(Date creationDate) throws ParseException 		{
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
	

	private Date 		generateExpirationDate() throws ParseException {
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
	
	public Date getExpirationDate() {
		return this.expirationDate;
	}
	
	private void 		setExpirationDate(Date expirationDate) { 
		if(!expirationDate.after(creationDate)) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"expirationDate "+expirationDate+" not > creationDate "+creationDate );
		this.expirationDate = expirationDate;
	}
	
	public String getDonatorCF() { return this.donatorCF; }
	
	private void setDonatorCF(String fisCode) {
		if( !fisCode.matches(Constants.RegexDonatorCF) )
			throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"donatorCF "+donatorCF+" do not match pattern "+Constants.RegexDonatorCF );
		this.donatorCF = fisCode;
	}
	
	public Node getNode() { return node; }
	
	protected void setNode(Node node) {
		if(node == null) throw new IllegalArgumentException( Constants.ExceptionIllegalArgument_BloodBagNotValid+"node is null ");
		this.node = node;
	}
	
	public String getNote() { return note;}	
	
	protected void setNote(String note) {
		this.note=note;			// overwrites notes
	}
	
	public void appendNote(String note) {
		// append to existing note value.
		StringBuffer sb = new StringBuffer();
		sb.append(this.note);
		sb.append(note);
		this.note=sb.toString();
	}

	public BloodBagState getBloodBagState() {
		return this.bloodBagState;
	}
	
	/*
	 * Il metodo che verifica lo stato della sacca.
	 * @return false se la sacca di sangue non è disponibile: è stata usata, trasferita o eliminata.
	 */
	public boolean checkBloodBagState() {
		return (bloodBagState == BloodBagState.Available); //return !(bloodBagState == BloodBagState.Used || bloodBagState == BloodBagState.Transfered || bloodBagState == BloodBagState.Dropped);
	}
	
	private void setBloodBagState(BloodBagState s) {
		this.bloodBagState = s;
	}
	
	public void transferBag() throws StateException { 
		if( !checkBloodBagState() )		throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");	
		setBloodBagState(BloodBagState.Transfered);
	}
	
	public void useBag() throws StateException {
		if( !checkBloodBagState() )		throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		setBloodBagState(BloodBagState.Used);
	}
	
	public void dropBag() throws StateException {
		if( !checkBloodBagState() )		throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		bloodBagState = BloodBagState.Dropped;
	}

	@Override
	public String toString() {
		return   "{\"serial\": \"" 	  			+ this.serial   		+ "\""
				+", \"bloodGroup\": \""  		+ this.bloodGroup 		+ "\"" 
				+", \"creationDate\": \"" 		+ new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate) 	+ "\""
				+", \"expireDate\": \"" 		+ new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate) + "\""
				+", \"donatorCF\": \"" 			+ this.donatorCF 		+ "\""
				+", \"node\": " 				+ this.node.toString()	+ ""
				+", \"bloodBagState\": \"" 		+ this.bloodBagState 	+ "\""
				+", \"note\": \"" + this.note 	+ "\""
				+ "}";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BloodBag) {
			BloodBag bBag = (BloodBag) obj;
			return (
					serial.equals(bBag.getSerial()) 		&& 
					bloodGroup.equals(bBag.getBloodGroup()) && 
					donatorCF.equals(bBag.getDonatorCF()) 	&& 
					node.equals(bBag.getNode())
					);
		}
		return false;
	}
	
	public int compareTo(BloodBag bloodBag) {
		return this.creationDate.compareTo(bloodBag.creationDate);
	}

	public void print() {		
		System.out.println("Seriale: " +serial.toString()+ ";\nGruppo: " +bloodGroup+ ";\nData Creazione: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate)+ ";\nData scadenza: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate)+ ";\nCodice fiscale donatore: " +donatorCF+ ";\nCodice nodo: " +node.getCodStr()+ ";\nStato: " +bloodBagState+ ";\nNote: " +note+ ".\n");
	}


	public enum BloodBagState{ 
		Available,	// Sacca disponibile per essere usata o trasferita o eliminata
		Transfered,	// Sacca trasferita a un altro nodo, viene comunque lasciata l'informazione nel database del nodo trasferente nello stato "trasferita"
		Used,		// Sacca adoperata. Non utilizzabile, non trasferibile, non eliminabile
		Dropped;	// Sacca eliminata (es. per scadenza o altre motivazioni)
	}
}