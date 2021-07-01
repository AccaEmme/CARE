package it.unisannio.CARE.model.Exceptions;

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




