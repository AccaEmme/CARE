package it.unisannio.CARE.spring.bean;



public class ErrorBean {

    private String timestamp, status, error, description, path;
    
    

	public ErrorBean(String timestamp, String status, String error, String description, String path) {
		
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.description = description;
		this.path = path;
	}

	
	
	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
