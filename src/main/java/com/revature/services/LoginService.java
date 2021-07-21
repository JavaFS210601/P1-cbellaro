package com.revature.services;

import com.revature.daos.LoginDAO;

public class LoginService {

	LoginDAO lDAO = new LoginDAO();
	
	public boolean login(String ers_username, String ers_password) {
		
		if(ers_username.equals(lDAO.getUsername(ers_username)) && ers_password.equals(lDAO.getPassword(ers_password))) { 
			return true;
		}
		return false;
	}
}