package it.unisannio.CARE.spring.bean;

/**
 * Generic error handling class related to the bean
 */

public class ErrorBean {

	private String timestamp, status, error, description, path;

	/**
	 * Constructor method of the ErrorBean class
	 * 
	 * @param timestamp   timestamp of the error
	 * @param status      status of the error
	 * @param error       message type
	 * @param description description of the error
	 * @param path        path of the error
	 */
	public ErrorBean(String timestamp, String status, String error, String description, String path) {

		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.description = description;
		this.path = path;
	}

	/**
	 * GET method of timeStamp
	 * 
	 * @return returns the timeStamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Set method of the time stamp
	 * 
	 * @param timestamp string variable for inserting the time stamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * GET method for status return
	 * 
	 * @return returns the status in string format
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * SET method of status
	 * 
	 * @param status variable String for entering the status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * GET method for error return
	 * 
	 * @return returns the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * SET method to insert the error
	 * 
	 * @param error variable string of the error
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * GET method to get the description
	 * 
	 * @return returns the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * SET method to enter the description
	 * 
	 * @param description variable string of the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * GET method to get the Path
	 * 
	 * @return returns the Path of the error
	 */
	public String getPath() {
		return path;
	}

	/**
	 * SET method to insert the error
	 * 
	 * @param path path in string format of the error
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
