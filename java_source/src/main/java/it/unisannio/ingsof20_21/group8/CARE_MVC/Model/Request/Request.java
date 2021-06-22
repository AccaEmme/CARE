package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request;


import java.util.Date;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class Request {
	
	// codice Hermannnnnnnn
	private User			UserRequester;
	private	BloodBag		requestedBloodBag;
	private	Date			requestDate;
	private	RequestState	requestState;
	
	public Request(User userRequester, BloodBag requestedBloodBag, Date requestDate) {	
		this.UserRequester 		= userRequester;
		this.requestedBloodBag 	= requestedBloodBag;
		this.requestDate 		= requestDate;
		this.requestState 		= RequestState.pending;
	}
	
	
	public User getUserRequester() {
		return UserRequester;
	}

	public void setUserRequester(User userRequester) {
		UserRequester = userRequester;
	}

	public BloodBag getRequestedBloodBag() {
		return requestedBloodBag;
	}

	public void setRequestedBloodBag(BloodBag requestedBloodBag) {
		this.requestedBloodBag = requestedBloodBag;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public RequestState getRequestState() {
		return requestState;
	}

	public void setRequestState(RequestState requestState) {
		this.requestState = requestState;
	}
	
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