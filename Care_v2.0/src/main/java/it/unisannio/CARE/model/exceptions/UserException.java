package it.unisannio.CARE.model.exceptions;

public class UserException extends Exception {
	public UserException() {
		super();
	}

	public UserException(String m) {
		super(m);
	}

	public UserException(String message, String path) {
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
