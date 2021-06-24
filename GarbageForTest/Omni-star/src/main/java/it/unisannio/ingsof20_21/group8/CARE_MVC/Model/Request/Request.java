package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Util.Constants;

public class Request {
	
	private User			UserRequester;
	private BloodBag		requestedBloodBag;
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
				+ "\"date\": \""+new SimpleDateFormat(Constants.DATE_FORMAT_STRING).format(requestDate)+"\","
				+ "\"state\": \""+this.requestState.toString()+"\"}";
	}

}