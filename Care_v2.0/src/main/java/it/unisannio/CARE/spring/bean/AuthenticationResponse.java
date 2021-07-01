package it.unisannio.ingsof20_21.group8.Care.Spring;

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
