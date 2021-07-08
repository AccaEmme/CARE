package it.unisannio.CARE.spring.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Bean of requests
 */

@SuppressWarnings("serial")
public class RequestBean implements Serializable{
	
	private String 	id_requester,
			serial,
			date,
			note,
			state,
			priority;
	
	public RequestBean() {}


	/**
	************************************************** ************************
	* Method for creating the RequestBean class
	* @param id_requester Id of the request
	* @param serial Serial of the request
	* @param date date of the request
	* @param note notes on the request
	* @param state Status of the request
	* @param priority priority of the request
	************************************************** ************************
	*/
	public RequestBean( String id_requester, String serial, String date, String note, String state, String priority) {
		
		this.id_requester = id_requester;
		this.serial = serial;
		this.date = date;
		this.note = note;
		this.state = state;
		this.priority = priority;
	}
	

	/**
    ************************************************** ************************
    * String GET method to get the request ID
    * @return returns the request id as a String
    ************************************************** ************************
   */
	public String getId_requester() {
		return id_requester;
	}


	/**
    ************************************************** ************************
    * String SET method to enter the new request ID
    * @param id_requester ID of the request
    ************************************************** ************************
   */
	public void setId_requester(String id_requester) {
		this.id_requester = id_requester;
	}
	
	
	/**
    ************************************************** ************************
    * String GET method to get the serial
    * @return returns the serial entered in the request as a String
    ************************************************** ************************
   */
	public String getSerial() {
		return serial;
	}


	/**
    ************************************************** ************************
    * String SET method to insert the serial
    * @param serial new serial to insert
    ************************************************** ************************
   */
	public void setSerial(String serial) {
		this.serial = serial;
	}


	/**
    ************************************************** ************************
    * String GET method to get the DATE
    * @return returns the date of the request as a String
    ************************************************** ************************
   */
	public String getDate() {
		return date;
	}


	/**
    ************************************************** ************************
    * String SET method to insert a new date
    * @param date new date to insert
    ************************************************** ************************
   */
	public void setDate(String date) {
		this.date = date;
	}



	/**
    ************************************************** ************************
    * String GET method to get the notes
    * @return returns the notes regarding the request as a String
    ************************************************** ************************
   */
	public String getNote() {
		return note;
	}


	/**
    ************************************************** ************************
    * String SET method to insert the notes of the request
    * @param notes new notes to insert
    ************************************************** ************************
   */
	public void setNote(String note) {
		this.note = note;
	}


	/**
    ************************************************** ************************
    * String GET method to get the status
    * @return returns the status of the request as a String
    ************************************************** ************************
   */
	public String getState() {
		return state;
	}


	/**
    ************************************************** ************************
    * String SET method to insert a new request
    * @param state new request status
    ************************************************** ************************
   */
	public void setState(String state) {
		this.state = state;
	}


	/**
    ************************************************** ************************
    * String GET method to get the priority of the request
    * @return returns the priority associated with the request as a String
    ************************************************** ************************
   */
	public String getPriority() {
		return priority;
	}


	/**
    ************************************************** ************************
    * String SET method insert a new priority
    * @param priority new priority value
    ************************************************** ************************
   */
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
