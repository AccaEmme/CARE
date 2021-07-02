package it.unisannio.CARE.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BloodBagNotFoundException extends RuntimeException {

	String path;
	
	public BloodBagNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public BloodBagNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BloodBagNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

    public  BloodBagNotFoundException(String mess, String path) {
        
    	super(mess);
    	this.path = path;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
