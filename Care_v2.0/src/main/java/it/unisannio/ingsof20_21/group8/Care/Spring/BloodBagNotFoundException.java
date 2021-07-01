package it.unisannio.ingsof20_21.group8.Care.Spring;

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
