package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Exceptions.StateException;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.Interfaces.BloodBagInterface;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Node.*;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;



public class BloodBag implements BloodBagInterface, Cloneable, Comparable<BloodBag>{

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
	
	
	public BloodBag(BloodGroup bloodGroup, String donatorCF, String nodeCode) throws ParseException {
		
		this.serial = new Serial(bloodGroup);
		this.bloodGroup = bloodGroup;
		this.creationDate = this.getCreationDate();
		this.expirationDate = this.getExpirationDate();
		this.donatorCF = donatorCF;
		this.nodeCode = nodeCode;
		this.node = null;
		this.note = null;
		this.bloodBagState = BloodBagState.Available;
	}
	
	public BloodBag(BloodGroup bloodGroup, String donatorCF, String nodeCode, String note) throws ParseException {
		
		this.serial = new Serial(bloodGroup);
		this.bloodGroup = bloodGroup;
		this.creationDate = this.getCreationDate();
		this.expirationDate = this.getExpirationDate();
		this.donatorCF = donatorCF;
		this.nodeCode = nodeCode;
		this.node = null;
		this.note = note;
		this.bloodBagState = BloodBagState.Available;
	}
	
	public BloodBag() { 
		
		serial = null;
		bloodGroup = null;
	}
	
	public static BloodBag read(Scanner sc) {
		
		
		
			if(!sc.hasNext()) return null;
			BloodGroup bloodGroupR = BloodGroup.valueOf(sc.next());
			if(!sc.hasNext()) return null;
			String donatorCFR = sc.next();
			if(!sc.hasNext()) return null;
			String nodeCodeR = sc.next();
				
			try {
				
				if(!sc.hasNext()) {
					
					return new BloodBag(bloodGroupR, donatorCFR, nodeCodeR);
				}
				
				String noteR = sc.next();
				return new BloodBag(bloodGroupR, donatorCFR, nodeCodeR, noteR);
		}catch(ParseException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public Serial getSerial() { return this.serial;}
	
	public BloodGroup getBloodGroup() { return this.bloodGroup;}
	
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
	
	public String getDonatorCF() { return this.donatorCF;}
	
	public String getNodeCode() { return nodeCode;}
	
	public Node2 getNode() { return node; }
	
	public String getNote() { return note;}

	
	
	private void setSerial(Serial serialR) { this.serial = serialR;}
	
	private void setBloodGroup(BloodGroup bloodGroup) { this.bloodGroup = bloodGroup;}
	
	private void setCreationDate(Date creationDate) { this.creationDate = creationDate;}
	
	private void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate;}
	
	private void setDonatorCF(String fisCode) { this.donatorCF = fisCode;}
	
	private void setNodeCode(String nodeCode) { this.nodeCode = nodeCode;}
	
	public void setNode(Node2 node) { this.node = node;}
	
	public void overWriteNote(String note) {
		// overwrites note value.
		this.note=note;	
	}
	
	public void appendNote(String note) {
		// append to existing note value.
		StringBuffer sb = new StringBuffer();
		sb.append(this.note);
		sb.append(note);
		this.note=sb.toString();
	}

	
	
	/*
	 * Il metodo che verifica lo stato della sacca.
	 * @return false se la sacca di sangue non è disponibile: è stata usata, trasferita o eliminata.
	 */
	public boolean checkState() {
		
		//return !(bloodBagState == BloodBagState.Used || bloodBagState == BloodBagState.Transfered || bloodBagState == BloodBagState.Dropped);
		return (bloodBagState == BloodBagState.Available);
	}
	
	/*
	 * Omni-star: implementazione con un eccezione
	 * in modo tale che si visualizzi un messaggio
	 * di eccezione nel caso non si possa
	 * usare o trasferire la sacca per motivi
	 * specificati nella creazione dell'istanza dell'eccezione sotto.
	 */
	public void transferBag() throws StateException { 
		
		if(!checkState()) {
			throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		}
		else {
			
			bloodBagState = BloodBagState.Transfered;
		}
	}
	
	public void useBag() throws StateException {
		
		if(!checkState()) {
			throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		}
		else {
			bloodBagState = BloodBagState.Used;
		}
	}
	
	public void dropBag() throws StateException {
		
		if(!checkState()) {
			throw new StateException("Stato della sacca non compatibile con l'operazione da eseguire. La sacca potrebbe essere stata trasferita o cestinata precedentemente.");
		}
		else {
			bloodBagState = BloodBagState.Dropped;
		}
	}
	
	@Override
	public String toString() {
		return "BloodBag [serial=" + serial + ", group=" + bloodGroup + ", expireDate=" + new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate) + ", state = " + bloodBagState + "]\nnote: '" + note + "'";
	}
	
	public int hashCode() {
		
		int h = 	31 * serial.hashCode();
		h += 		31 * bloodGroup.hashCode();
		h += 		31 * donatorCF.hashCode();
		
		return h;
	}

	@Override
	public boolean equals(Object obj) {
		
		if(obj instanceof BloodBag) {
			
			BloodBag bBag = (BloodBag) obj;
			return (serial.equals(bBag.getSerial()) && bloodGroup.equals(bBag.getBloodGroup()) && donatorCF.equals(bBag.getDonatorCF()) && node.equals(bBag.getNode()));
		}
			return false;
	}
	
	public int compareTo(BloodBag bloodBag) {
		
		return this.serial.compareTo(bloodBag.serial);
	}
	
	public BloodBag clone(){
			
			BloodBag bg = new BloodBag();
			bg.serial = this.serial;
			bg.bloodGroup = this.bloodGroup;
			bg.creationDate = this.creationDate;
			bg.expirationDate = this.expirationDate;
			bg.donatorCF = this.donatorCF;
			bg.nodeCode = this.nodeCode;
			bg.node = this.node;
			bg.bloodBagState = this.bloodBagState;
			bg.note = this.note;		
			
			return bg;
			
	}
	
	
	public void print() {
		
		System.out.println("Seriale: " +serial.toString()+ ";\nGruppo: " +bloodGroup+ ";\nData Creazione: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(creationDate)+ ";\nData scadenza: " +new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(expirationDate)+ ";\nCodice fiscale donatore: " +donatorCF+ ";\nCodice nodo: " +nodeCode+ ";\nStato: " +bloodBagState+ ";\nNote: " +note+ ".\n");
	}
	
	
	public enum BloodBagState{ 
		/*
		 *  Hermann l'enumeratore deve avere visibilità pubblica non privata, perché altrimenti non può essere usato come tipo per argomento da altre classi (es. OfficerInterface->BloodBagManager).
		 *  Non sussiste alcun rischio di sicurezza di codice, perché a runtime non è possibile aggiungere o eliminare valori dall'enumerator.
		 *  
		 */
		Available,	// Sacca disponibile per essere usata o trasferita o eliminata
		Transfered,	// Sacca trasferita a un altro nodo, viene comunque lasciata l'informazione nel database del nodo trasferente nello stato "trasferita"
		Used,		// Sacca adoperata. Non utilizzabile, non trasferibile, non eliminabile
		Dropped;	// Sacca eliminata (es. per scadenza o altre motivazioni)
	}

	private /*final*/Serial		serial;
	private /*final*/BloodGroup bloodGroup;
	private Date				creationDate;
	private Date 				expirationDate;
	private String				donatorCF; //=null;	 *** Attenzione al rischio di null pointer exception se richiamato il donatorCF
	private String				nodeCode;
	private Node2				node;
	private BloodBagState 		bloodBagState;
	private String 				note;
	
	public static final int 	monthIncrementAmount = 1;
}
