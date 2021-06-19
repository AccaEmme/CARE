package it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request;

import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class Request {
	private User userRequesting;
	private BloodBag bloodBag;
	private Date requestedDate;
	private State state;
	
	public Request(User  userRequesting, BloodBag bloodBag,Date requestedDate,State state) {
		this.userRequesting=userRequesting;
		this.bloodBag=bloodBag;
		this.requestedDate=requestedDate;
		this.state=state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
	
	
	
}
