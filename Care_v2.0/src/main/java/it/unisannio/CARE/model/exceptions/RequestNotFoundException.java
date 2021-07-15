package it.unisannio.CARE.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RequestNotFoundException extends RuntimeException {

	public RequestNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public RequestNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RequestNotFoundException(String message, String path) {
		super(message);
		this.path = path;
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	String path;
}
