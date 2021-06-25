package it.unisannio.ingsof20_21.group8.CARE_MVC.Control.Managers;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;

import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Blood.BloodBag;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.Request;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.Request.RequestState;
import it.unisannio.ingsof20_21.group8.CARE_MVC.Model.User.User;

public class RequestManager {
	ArrayList<Request> requestsList;

	// SEGRETARIA:
	public RequestManager() {
		this.requestsList = new ArrayList<Request>();
	}
	
	public void pushRequest(Request r) {
		requestsList.add(r);
	}
	
	public ArrayList<Request> pullRequests(){
		return requestsList;
	}
	
	public ArrayList<Request> findRequestsByUser(User u) {
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getUserRequester().equals(u) )
				results.add(r);
		return results;
	}
	
	public ArrayList<Request> findRequestsByBloodBag(BloodBag bb) {
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestedBloodBag().equals(bb) )
				results.add(r);
		return results;
	}

	public ArrayList<Request> findRequestsByBeforeDate(Date d) {
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestDate().before(d) )
				results.add(r);
		return results;		
	}

	public ArrayList<Request> findRequestsByEqualDate(Date d) {
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestDate().equals(d) )
				results.add(r);
		return results;		
	}
	
	public ArrayList<Request> findRequestsByAfterDate(Date d) {
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestDate().after(d) )
				results.add(r);
		return results;		
	}
	

	public ArrayList<Request> findRequestsByState(RequestState rs) {
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestState().equals(rs) )
				results.add(r);
		return results;
	}

	public void		print(PrintStream ps) {
		for(Request r: requestsList)
			ps.println(r.toString());
	}
	
	
	// MAGAZZINIERE METHODS'
	public ArrayList<Request>	acceptRequest(Request rq){
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestState().equals(rq) )
				r.setRequestState(RequestState.accepted);
		return results;
	}

	public ArrayList<Request>	refuseRequest(Request rq){
		ArrayList<Request> results = new ArrayList<Request>(); 
		for(Request r: requestsList)
			if( r.getRequestState().equals(rq) )
				r.setRequestState(RequestState.refused);
		return results;
	}
	
}