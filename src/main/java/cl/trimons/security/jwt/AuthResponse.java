package cl.trimons.security.jwt;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
	private String username;
	private String accessToken;
	
	
	
	public AuthResponse(String username, String accessToken) {
		super();
		this.username = username;
		this.accessToken = accessToken;
	}



	public AuthResponse() {
	}	
}
