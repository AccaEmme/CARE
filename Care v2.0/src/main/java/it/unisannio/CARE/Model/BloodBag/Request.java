package it.unisannio.CARE.Model.BloodBag;


import java.text.SimpleDateFormat;
import java.util.Date;


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
     * @param User userRequester, BloodBag requestedBloodBag, Date requestDate
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
     * @return idRequester
     **************************************************************************
     */
	public String getIdRequester() {
		return idRequester;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire l'id della locazione della richiesta
     * @param idRequester
     **************************************************************************
     */
	public void setIdRequester(String idRequester) {
		this.idRequester = idRequester;
	}

	

	/**
     **************************************************************************
     * Metodo GET ottenere il seriale della sacca richiesta
     * @return requestedBagSerial
     **************************************************************************
     */
	public String getRequestedBagSerial() {
		return requestedBagSerial;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire il seriale della sacca richiesta
     * @param String requestedBagSerial
     **************************************************************************
     */
	public void setRequestedBagSerial(String requestedBagSerial) {
		this.requestedBagSerial = requestedBagSerial;
	}
	
	
	
	/**
     **************************************************************************
     * Metodo GET ottenere la data della richiesta
     * @return requestDate
     **************************************************************************
     */
	public Date getRequestDate() {
		return requestDate;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire la data della richiesta
     * @param Date requestDate
     **************************************************************************
     */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	
	
	public String getNote() {
		return note;
	}

	
	
	public void setNote(String note) {
		this.note = note;
	}

	
	
	/**
     **************************************************************************
     * Metodo GET ottenere lo stato della richiesta
     * @return requestState
     **************************************************************************
     */
	public RequestState getState() {
		return state;
	}

	
	
	/**
     **************************************************************************
     * Metodo SET inserire lo stato della richiesta
     * @param RequestState requestState
     **************************************************************************
     */
	public void setState(RequestState state) {
		this.state = state;
	}

	
	
	public RequestPriority getPriority() {
		return priority;
	}

	
	
	public void setPriority(RequestPriority priority) {
		this.priority = priority;
	}

	
	
	/**
     **************************************************************************
     * Metodo ToString per ottenere tutte le informazioni della classe Request
     * @return Username, requestedBloodBag, requestDate, requestState
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