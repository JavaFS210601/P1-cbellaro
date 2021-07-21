package com.revature.models;

public class LoginDTO {

	public String ers_username;
	public String ers_password;
	
	public LoginDTO() {
		super();
	}

	public LoginDTO(String ers_username, String ers_password) {
		super();
		this.ers_username = ers_username;
		this.ers_password = ers_password;
	}
	
}
