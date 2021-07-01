package it.unisannio.CARE.spring.bean;

public class AuthenticationRequest {
	
	private String username;
	private String password;
	
	
	/* è qullo che canfora chiam Bean due stringhe username e password
	 * che permettono di autenticarsi
	 */
	
	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public AuthenticationRequest()
	{
		
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
