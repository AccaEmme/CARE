package it.unisannio.CARE.model.Exceptions;

public class IllegalDateException extends RuntimeException {

	public IllegalDateException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalDateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

    public  IllegalDateException(String message, String path) {
        
    	super(message);
    	this.path = path;
    }
    
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	String path;
	

}
