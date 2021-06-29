package it.unisannio.CARE.Model.BloodBag;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe che si occupa delle richieste effettuate o da fare per le sacche di sangue 
 */

public class Request {
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private String			idRequester;
	private	String			requestedBagSerial;
	private	Date			requestDate;
	private String			note;
	private	RequestState	state;
	private RequestPriority	priority;
	
	
	
	/**
     **************************************************************************
     * Metodo costruttore per la creazione di una richiesta
     * @param String ID della richeista
     * @param String ID della sacca richiesta
     * @param Date Data della richiesta
     * @param String Note per la richiesta
     * @param RequestState stato della richiesta
     * @param RequestPriority priorità della richiesta
     **************************************************************************
     */
	public Request( String idRequester, String requestedBagSerial, 
			Date requestDate, String note, RequestState state, RequestPriority priority) {
		
		this.idRequester = idRequester;
		this.requestedBagSerial = requestedBagSerial;
		this.requestDate = requestDate;
		this.note = note;
		this.state = state;
		this.priority = priority;
	}



	/**
     **************************************************************************
     * Metodo GET ottenere l'id della locazione della richiesta
     * @return ritorna l'ID della richiesta selezionata
     **************************************************************************
     */
	public String getIdRequester() {
		return idRequester;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire l'id della locazione della richiesta
     * @param String nuovo ID della richiesta
     **************************************************************************
     */
	public void setIdRequester(String idRequester) {
		this.idRequester = idRequester;
	}

	

	/**
     **************************************************************************
     * Metodo GET ottenere il seriale della sacca richiesta
     * @return ritorna il seriale della sacca 
     **************************************************************************
     */
	public String getRequestedBagSerial() {
		return requestedBagSerial;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire il seriale della sacca richiesta
     * @param String Nuovo seriale della sacca 
     **************************************************************************
     */
	public void setRequestedBagSerial(String requestedBagSerial) {
		this.requestedBagSerial = requestedBagSerial;
	}
	
	
	
	/**
     **************************************************************************
     * Metodo GET ottenere la data della richiesta
     * @return ritorna la data di richiesta
     **************************************************************************
     */
	public Date getRequestDate() {
		return requestDate;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire la data della richiesta
     * @param Date nuova data di richiesta 
     **************************************************************************
     */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	
	/**
     **************************************************************************
     * Metodo GET per ottenere le note
     * @return ritorna le note scritte nella richiesta 
     **************************************************************************
     */
	public String getNote() {
		return note;
	}

	
	/**
     **************************************************************************
     * Metodo SET per inserire le note
     * @param String nuove note da inserire 
     **************************************************************************
     */
	public void setNote(String note) {
		this.note = note;
	}

	
	
	/**
     **************************************************************************
     * Metodo GET ottenere lo stato della richiesta
     * @return ritorna lo stato della richista
     **************************************************************************
     */
	public RequestState getState() {
		return state;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire lo stato della richiesta
     * @param RequestState Oggetto per identificare lo stato della richiesta
     **************************************************************************
     */
	public void setState(RequestState state) {
		this.state = state;
	}

	
	/**
     **************************************************************************
     * Metodo GET per ottenere la priorita'
     * @return ritorna la priorita' della richiesta
     **************************************************************************
     */
	public RequestPriority getPriority() {
		return priority;
	}

	
	/**
     **************************************************************************
     * Metodo SET per inserire la priorita'
     * @param RequestPriority Oggetto per inserire la priorita' della richiesta
     **************************************************************************
     */
	public void setPriority(RequestPriority priority) {
		this.priority = priority;
	}

	
	
	/**
     **************************************************************************
     * Metodo ToString per ottenere tutte le informazioni della classe Request
     * @return ritorna in formato di stringa tutte le informazioni della classe richiesta
     **************************************************************************
     */
	public String toString() {
		return "{"
				+ "\"id_requester\": \""+this.idRequester+"\","
				+ "\"serial\": \""+this.requestedBagSerial+"\","
				+ "\"date\": \""+DATE_FORMAT.format(this.requestDate)+"\","
				+ "\"note\": \""+this.note+"\","
				+ "\"state\": \""+this.state.toString()+"\","
				+ "\"priority\": \""+this.priority.toString()+"\"}";
	}
	
	
	
	/**
	 **************************************************************************
	 * Metodo ENUM per i vari stati della richiesta
	 **************************************************************************
	 */
	public enum RequestState {
		
		accepted, refused, pending;
	}

	
	
	/**
	 **************************************************************************
	 * Metodo ENUM per le varie priorità della richiesta
	 **************************************************************************
	 */
	public enum RequestPriority {
		
		red, yellow, green;
	}
}