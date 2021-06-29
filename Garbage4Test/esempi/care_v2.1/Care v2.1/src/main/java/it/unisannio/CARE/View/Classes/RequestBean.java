package it.unisannio.CARE.View.Classes;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class RequestBean implements Serializable{


	private String 	id_requester,
			serial,
			date,
			note,
			state,
			priority;
	
	public RequestBean() {}

	
	
	public RequestBean( String id_requester, String serial, String date, String note, String state, String priority) {
		
		this.id_requester = id_requester;
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
