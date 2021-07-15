package it.unisannio.CARE.spring.bean;

/**
 * Class for authentication via bean
 */
public class AuthenticationRequestBean {

	private String username;
	private String password;

	/**
	 * Constructor of the bean AuthenticationRequestBean class
	 * 
	 * @param username Username of the user
	 * @param password Password of the user
	 */
	public AuthenticationRequestBean(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AuthenticationRequestBean() {
	}

	/**
	 * GET method to get the username
	 * 
	 * @return returns the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * SET method to enter the username
	 * 
	 * @param username variable string of the username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * GET method to get the password
	 * 
	 * @return returns the user's password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * SET method to enter the password
	 * 
	 * @param password variable String for the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
