package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class Request {
	private String userRequesting;
	private String serialBloodBag;
	private Date requestedDate;
	private State state;
	
	public Request(String userRequesting, String SerialBloodBag,Date requestedDate,State state) {
		this.userRequesting=userRequesting;
		this.serialBloodBag=serialBloodBag;
		this.requestedDate=requestedDate;
		this.state=state;
	}

	public String getUserRequesting() {
		return userRequesting;
	}

	

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	public String getSerial() {
		return serialBloodBag;
	}
	
}
