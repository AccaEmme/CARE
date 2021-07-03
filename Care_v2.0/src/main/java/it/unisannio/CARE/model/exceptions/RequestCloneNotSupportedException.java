package it.unisannio.CARE.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class RequestCloneNotSupportedException extends RuntimeException {

	public RequestCloneNotSupportedException() {
		// TODO Auto-generated constructor stub
	}

	public RequestCloneNotSupportedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RequestCloneNotSupportedException(String message, String path) {
		
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
