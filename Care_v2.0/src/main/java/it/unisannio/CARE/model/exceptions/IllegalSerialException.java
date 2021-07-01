package it.unisannio.CARE.model.exceptions;

public class IllegalSerialException extends RuntimeException {

	public IllegalSerialException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalSerialException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

    public  IllegalSerialException(String message, String path) {
        
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
