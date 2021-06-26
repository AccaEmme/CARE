package it.unisannio.CARE.View.Classes;

public class RequestBean {

	String 	id_requester, 
			id_request,
			serial,
			date,
			note,
			state,
			priority;
	
	public RequestBean() {
		// TODO Auto-generated constructor stub
		
		
	}

	
	
	public RequestBean(String id_requester, String id_request, String serial, String date, String note, String state, String priority) {
		
		this.id_requester = id_requester;
		this.id_request = id_request;
		this.serial = serial;
		this.date = date;
		this.note = note;
		this.state = state;
		this.priority = priority;
	}

	
	
	public String getId_requester() {
		return id_requester;
	}



	public void setId_requester(String id_requester) {
		this.id_requester = id_requester;
	}



	public String getId_request() {
		return id_request;
	}



	public void setId_request(String id_request) {
		this.id_request = id_request;
	}



	public String getSerial() {
		return serial;
	}



	public void setSerial(String serial) {
		this.serial = serial;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getPriority() {
		return priority;
	}



	public void setPriority(String priority) {
		this.priority = priority;
	}

}
