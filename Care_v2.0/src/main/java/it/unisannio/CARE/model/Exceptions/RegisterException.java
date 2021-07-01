package it.unisannio.CARE.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public  class RegisterException extends RuntimeException{
	
    public RegisterException(String mess) {
        
    	super(mess);
    }

	public RegisterException() {

		super();
	}
	
}

