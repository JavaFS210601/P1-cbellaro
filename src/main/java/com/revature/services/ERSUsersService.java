package com.revature.services;

import com.revature.daos.ERSUsersDAO;
import com.revature.models.ERSUsers;

public class ERSUsersService {

	private ERSUsersDAO eUDAO = new ERSUsersDAO();
		
	public ERSUsers getUser(String username) {
		return eUDAO.getUserByUsername(username);
	}	
}
