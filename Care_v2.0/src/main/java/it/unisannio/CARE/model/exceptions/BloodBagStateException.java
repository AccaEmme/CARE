package it.unisannio.CARE.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BloodBagStateException extends RuntimeException {

	public BloodBagStateException() {

		super();
	}

	public BloodBagStateException(String m) {

		super(m);
	}

	public BloodBagStateException(String message, String path) {

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
