package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request;


import java.util.Date;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class Request {
	
	private User			UserRequester;
	private	BloodBag		requestedBloodBag;
	private	Date			requestDate;
	private	RequestState	requestState;
	
	/**
     **************************************************************************
     * Metodo costruttore per la creazione di una richiesta
     * @param User userRequester, BloodBag requestedBloodBag, Date requestDate
     **************************************************************************
     */
	public Request(User userRequester, BloodBag requestedBloodBag, Date requestDate) {	
		this.UserRequester 		= userRequester;
		this.requestedBloodBag 	= requestedBloodBag;
		this.requestDate 		= requestDate;
		this.requestState 		= RequestState.pending;
	}
	
	
	/**
     **************************************************************************
     * Metodo GET ottenere lo UserRequester
     * @return UserRequester
     **************************************************************************
     */
	public User getUserRequester() {
		return UserRequester;
	}

	/**
     **************************************************************************
     * Metodo SET ottenere lo UserRequester
     * @param User userRequester
     **************************************************************************
     */
	public void setUserRequester(User userRequester) {
		UserRequester = userRequester;
	}

	/**
     **************************************************************************
     * Metodo GET ottenere la richiesta delle sacche di sangue 
     * @return requestedBloodBag
     **************************************************************************
     */
	public BloodBag getRequestedBloodBag() {
		return requestedBloodBag;
	}

	/**
     **************************************************************************
     * Metodo SET inserire la richiesta delle sacche di sangue 
     * @param BloodBag requestedBloodBag
     **************************************************************************
     */
	public void setRequestedBloodBag(BloodBag requestedBloodBag) {
		this.requestedBloodBag = requestedBloodBag;
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

	/**
     **************************************************************************
     * Metodo GET ottenere lo stato della richiesta
     * @return requestState
     **************************************************************************
     */
	public RequestState getRequestState() {
		return requestState;
	}

	/**
     **************************************************************************
     * Metodo SET inserire lo stato della richiesta
     * @param RequestState requestState
     **************************************************************************
     */
	public void setRequestState(RequestState requestState) {
		this.requestState = requestState;
	}
	
	
	/**
     **************************************************************************
     * Metodo ToString per ottenere tutte le informazioni della classe Request
     * @return Username, requestedBloodBag, requestDate, requestState
     **************************************************************************
     */
	public String	toString() {
		return "{"
				+ "\"username\": \""+this.UserRequester.getUsername()+"\","
				+ "\"bloodbag\": \""+this.requestedBloodBag.getSerial()+"\","
				+ "\"bloodbag\": \""+this.requestDate.toString()+"\","
				+ "\"bloodbag\": \""+this.requestState.toString()+"\"}";
	}




	// codice Luigi
	/*
	private String userRequesting;
	private String serialBloodBag;
	private Date requestedDate;
	private RequestState state;
	public Request(String userRequesting, String SerialBloodBag, Date requestedDate, RequestState state) {
		this.userRequesting=userRequesting;
		this.serialBloodBag=serialBloodBag;
		this.requestedDate=requestedDate;
		this.state=state;
	}

	public String getUserRequesting() {
		return userRequesting;
	}

	

	public String getSerialBloodBag() {
		return serialBloodBag;
	}

	public Date getRequestedDate() {
		return requestedDate;
	}

	public RequestState getState() {
		return state;
	}

	public void setState(RequestState state) {
		this.state = state;
	}
	
	public String getSerial() {
		return serialBloodBag;
	}
	*/
}