package it.unisannio.CARE.spring.bean;

public class AuthenticationResponseBean {

	private String token;
	
	/*
	 * bean del Token
	 */
	
	public AuthenticationResponseBean()
	{
		
	}

	public AuthenticationResponseBean(String token) {
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
