package it.unisannio.CARE.Model.BloodBag;


import java.util.Date;


public class Request {
	
	private String			idLocation;
	private String			idRequest;
	private	String		requestedBag;
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

	public Request(String idLocation, String idRequest, String requestedBag, 
			Date requestDate, String note, RequestState state, RequestPriority priority) {
		
		this.idLocation = idLocation;
		this.idRequest = idRequest;
		this.requestedBag = requestedBag;
		this.requestDate = requestDate;
		this.note = note;
		this.state = state;
		this.priority = priority;
	}
	
	public String getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(String idLocation) {
		this.idLocation = idLocation;
	}

	public String getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(String idRequest) {
		this.idRequest = idRequest;
	}

	/**
     **************************************************************************
     * Metodo GET ottenere la richiesta delle sacche di sangue 
     * @return requestedBloodBag
     **************************************************************************
     */
	public String getRequestedBag() {
		return requestedBag;
	}

	/**
     **************************************************************************
     * Metodo SET inserire la richiesta delle sacche di sangue 
     * @param BloodBag requestedBloodBag
     **************************************************************************
     */
	public void setRequestedBag(String requestedBag) {
		this.requestedBag = requestedBag;
	}
	
	/**
     **************************************************************************
     * Metodo GET ottenere la richiesta della data
     * @return requestDate
     **************************************************************************
     */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
     **************************************************************************
     * Metodo SET inserire la richiesta della data
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
	

	
	public String	toString() {
		return "{"
				+ "\"id_requester\": \""+this.idLocation+"\","
				+ "\"id_request\": \""+this.idRequest+"\","
				+ "\"serial\": \""+this.requestedBag+"\","
				+ "\"date\": \""+this.requestDate.toString()+"\","
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