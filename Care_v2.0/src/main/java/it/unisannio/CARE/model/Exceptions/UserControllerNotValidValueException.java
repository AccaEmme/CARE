package it.unisannio.CARE.model.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason="Not Valid Value")
public class UserControllerNotValidValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
