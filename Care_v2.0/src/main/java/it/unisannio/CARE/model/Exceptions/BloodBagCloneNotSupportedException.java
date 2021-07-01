package it.unisannio.CARE.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public  class BloodBagCloneNotSupportedException extends RuntimeException{
	
	public  BloodBagCloneNotSupportedException() {

		super();
	}
	
	
	
    public  BloodBagCloneNotSupportedException(String message) {
        
    	super(message);
    }

    public  BloodBagCloneNotSupportedException(String message, String path) {
        
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
