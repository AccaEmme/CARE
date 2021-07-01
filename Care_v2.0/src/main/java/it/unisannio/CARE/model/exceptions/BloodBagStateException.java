package it.unisannio.CARE.model.exceptions;

public class BloodBagStateException extends RuntimeException {

	public BloodBagStateException() {
		
		super();
	}
	
	public BloodBagStateException(String m) {
		
		super(m);
	}

    public  BloodBagStateException(String message, String path) {
        
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




