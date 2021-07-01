package it.unisannio.CARE.spring.bean;

public class AuthenticationResponse {

	private String token;
	
	/*
	 * bean del Token
	 */
	
	public AuthenticationResponse()
	{
		
	}

	public AuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}
