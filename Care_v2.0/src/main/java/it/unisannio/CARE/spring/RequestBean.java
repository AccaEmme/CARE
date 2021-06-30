package it.unisannio.CARE.spring;

import java.io.IOException;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * 
 */

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

	
	
	/**
	**************************************************************************
	 * Metodo per la creazione della classe RequestBean
	 * @param id_requester Id della richiesta
	 * @param serial Seriale della richiesta
	 * @param date data della richiesta
	 * @param note note sulla richiesta 
	 * @param state Stato della richiesta
	 * @param priority priorita' della richiesta
	 **************************************************************************
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
     **************************************************************************
     * Metodo GET per ottenere l'ID della richiesta
     * @return ritorna l'id della richiesta
     **************************************************************************
    */
	public String getId_requester() {
		return id_requester;
	}


	/**
     **************************************************************************
     * Metodo SET per inserire il nuovo ID della richiesta
     * @param id_requester ID della richiesta
     **************************************************************************
    */
	public void setId_requester(String id_requester) {
		this.id_requester = id_requester;
	}
	
	
	/**
     **************************************************************************
     * Metodo GET per ottenere il seriale
     * @return ritorna il seriale inserito nella richiesta
     **************************************************************************
    */
	public String getSerial() {
		return serial;
	}


	/**
     **************************************************************************
     * Metodo SET per inserire il seriale
     * @param serial nuovo seriale da inserire 
     **************************************************************************
    */
	public void setSerial(String serial) {
		this.serial = serial;
	}


	/**
     **************************************************************************
     * Metodo GET per ottenere la DATA
     * @return ritorna la data della richiesta
     **************************************************************************
    */
	public String getDate() {
		return date;
	}


	/**
     **************************************************************************
     * Metodo SET per inserire una nuova data
     * @param date nuova data da inserire
     **************************************************************************
    */
	public void setDate(String date) {
		this.date = date;
	}



	/**
     **************************************************************************
     * Metodo GET per ottenere le note
     * @return ritorna le note riguardanti la richiesta
     **************************************************************************
    */
	public String getNote() {
		return note;
	}


	/**
     **************************************************************************
     * Metodo SET per inserire le note della richiesta
     * @param note nuove note da inserire 
     **************************************************************************
    */
	public void setNote(String note) {
		this.note = note;
	}


	/**
     **************************************************************************
     * Metodo GET per ottenere lo stato
     * @return ritorna lo stato della richiesta
     **************************************************************************
    */
	public String getState() {
		return state;
	}


	/**
     **************************************************************************
     * Metodo SET per inserire una nuova richiesta
     * @param state nuovo stato della richiesta
     **************************************************************************
    */
	public void setState(String state) {
		this.state = state;
	}


	/**
     **************************************************************************
     * Metodo GET per ottenere la priorita' della richiesta
     * @return ritorna la priorita' associata alla richiesta
     **************************************************************************
    */
	public String getPriority() {
		return priority;
	}


	/**
     **************************************************************************
     * Metodo SET inserire una nuova priorita'
     * @param priority nuovo valore della priorita'
     **************************************************************************
    */
	public void setPriority(String priority) {
		this.priority = priority;
	}

}
