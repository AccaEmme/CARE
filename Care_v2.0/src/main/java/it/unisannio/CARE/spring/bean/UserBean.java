package it.unisannio.CARE.spring.bean;

/**
 * is the Bean class of User.class, that is, the complex types of User.class are
 * simplified
 */
public class UserBean {
	private String username;
	private String password;
	private String role;

	/**
	 ************************************************** ************************ String Get method to get the role
	 * 
	 * @return returns the role ************************
	 */
	public String getRole() {
		return role;
	}

	/**
	 ************************************************** ************************ String SET method to insert the role
	 * 
	 * @param role enter the role chosen as String as a parameter
	 **************************************************             ************************
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 ************************************************** ************************ String Get method to get the username
	 * 
	 * @return returns the Username as a String ************************
	 */
	public String getUsername() {
		return username;
	}

	/**
	 ************************************************** ************************ String SET method to enter the new username
	 * 
	 * @param username new username as a String ************************
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 ************************************************** ************************ String Get method to get the password
	 * 
	 * @return returns the password as a String ************************
	 */
	public String getPassword() {
		return password;
	}

	/**
	 ************************************************** ************************ String SET method to enter a new password
	 * 
	 * @param password new password to be entered as a String
	 **************************************************                 ************************
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
