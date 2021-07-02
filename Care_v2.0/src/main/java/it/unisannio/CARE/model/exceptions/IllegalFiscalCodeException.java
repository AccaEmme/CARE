package it.unisannio.CARE.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class IllegalFiscalCodeException extends RuntimeException {

	public IllegalFiscalCodeException() {
		super();
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
