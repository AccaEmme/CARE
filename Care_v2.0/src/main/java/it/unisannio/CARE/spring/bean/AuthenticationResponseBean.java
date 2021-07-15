package it.unisannio.CARE.spring.bean;

/**
 * Class for the bean authentication response
 */
public class AuthenticationResponseBean {

	private String token;

	/*
	 * bean del Token
	 */

	public AuthenticationResponseBean() {

	}

	/**
	 * Constructor method of the AuthenticationResponseBean class
	 * 
	 * @param token authentication token in string format
	 */
	public AuthenticationResponseBean(String token) {
		super();
		this.token = token;
	}

	/**
	 * GET method to get the token
	 * 
	 * @return returns the token in string format
	 */
	public String getToken() {
		return token;
	}

	/**
	 * SET method to insert the token
	 * 
	 * @param token String variable for the token
	 */
	public void setToken(String token) {
		this.token = token;
	}

}
