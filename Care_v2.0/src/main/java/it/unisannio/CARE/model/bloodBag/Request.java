package it.unisannio.CARE.model.bloodBag;


import java.text.SimpleDateFormat;
import java.util.Date;
import it.unisannio.CARE.model.bloodBag.RequestState;
import it.unisannio.CARE.model.bloodBag.RequestPriority;

/**
 * Class that deals with requests made or to be made for blood bags
 */

public class Request {
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	private String			idRequester;
	private	String			requestedBagSerial;
	private	Date			requestDate;
	private String			note;
	private RequestState 	state;
	private RequestPriority priority;
	
	
	
	
	/**
     **************************************************************************
      * Constructor method for creating a request
      * @param idRequester ID of the requesting node
      * @param requestedBagSerial ID of the requested bag
      * @param requestDate Date of the request
      * @param note Notes for the request
      * @param state status of the request
      * @param priority priority of the request
     **************************************************************************
     */
	public Request( String idRequester, String requestedBagSerial, 
			Date requestDate, String note, RequestState state, RequestPriority priority) {
		
		this.idRequester = idRequester;
		this.requestedBagSerial = requestedBagSerial;
		this.requestDate = requestDate;
		this.note = note;
		this.state = state;
		this.priority = priority;
	}



	/**
     **************************************************************************
      * GET method get the id of the location of the request
      * @return returns the ID of the selected request
     **************************************************************************
     */
	public String getIdRequester() {
		return idRequester;
	}

	
	
	/**
     **************************************************************************
      * SET method enter the id of the location of the request
      * @param idRequester new request ID
     **************************************************************************
     */
	public void setIdRequester(String idRequester) {
		this.idRequester = idRequester;
	}

	

	/**
     **************************************************************************
      * GET method get the serial of the requested bag
      * @return returns the serial of the bag
     **************************************************************************
     */
	public String getRequestedBagSerial() {
		return requestedBagSerial;
	}

	
	
	/**
     **************************************************************************
      * SET method enter the serial number of the required bag
      * @param requestedBagSerial New serial of the bag
     **************************************************************************
     */
	public void setRequestedBagSerial(String requestedBagSerial) {
		this.requestedBagSerial = requestedBagSerial;
	}
	
	
	
	/**
     **************************************************************************
      * GET method get the date of the request
      * @return returns the request date
     **************************************************************************
     */
	public Date getRequestDate() {
		return requestDate;
	}

	
	
	/**
     **************************************************************************
      * SET method enter the date of the request
      * @param requestDate new request date
     **************************************************************************
     */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	
	/**
     **************************************************************************
      * GET method to get notes
      * @return returns the notes written in the request
     **************************************************************************
     */
	public String getNote() {
		return note;
	}

	
	/**
     **************************************************************************
      * SET method to insert notes
      * @param notes new notes to insert
     **************************************************************************
     */
	public void setNote(String note) {
		this.note = note;
	}

	
	
	/**
     **************************************************************************
      * GET method to get the status of the request
      * @return returns the status of the request
     **************************************************************************
     */
	public RequestState getState() {
		return state;
	}

	
	
	/**
     **************************************************************************
      * SET method enter the status of the request
      * @param state Object to identify the status of the request	
     **************************************************************************
     */
	public void setState(RequestState state) {
		this.state = state;
	}

	
	/**
     **************************************************************************
      * GET method to get priority
      * @return returns the priority of the request
     **************************************************************************
     */
	public RequestPriority getPriority() {
		return priority;
	}

	
	/**
     **************************************************************************
      * SET method to enter the priority
      * @param priority Object to insert the priority of the request
     **************************************************************************
     */
	public void setPriority(RequestPriority priority) {
		this.priority = priority;
	}

	
	
	/**
     **************************************************************************
      * ToString method to get all the information of the Request class
      * @return returns all the information of the requested class in string format
     **************************************************************************
     */
	public String toString() {
		return "{"
				+ "\"id_requester\": \""+this.idRequester+"\","
				+ "\"serial\": \""+this.requestedBagSerial+"\","
				+ "\"date\": \""+DATE_FORMAT.format(this.requestDate)+"\","
				+ "\"note\": \""+this.note+"\","
				+ "\"state\": \""+this.state.toString()+"\","
				+ "\"priority\": \""+this.priority.toString()+"\"}";
	}

}