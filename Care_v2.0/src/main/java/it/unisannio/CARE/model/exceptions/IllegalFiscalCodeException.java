package it.unisannio.CARE.model.exceptions;

public class IllegalFiscalCodeException extends RuntimeException {

	public IllegalFiscalCodeException() {
		// TODO Auto-generated constructor stub
	}

	public IllegalFiscalCodeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

    public  IllegalFiscalCodeException(String message, String path) {
        
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
