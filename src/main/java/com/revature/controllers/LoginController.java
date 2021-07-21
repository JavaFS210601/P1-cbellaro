package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ERSUsers;
import com.revature.models.LoginDTO;
import com.revature.services.ERSUsersService;
import com.revature.services.LoginService;

public class LoginController {

	private ObjectMapper om = new ObjectMapper();
	private LoginService ls = new LoginService();
	private ERSUsersService eus = new ERSUsersService();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		if(req.getMethod().equals("POST")) {
			BufferedReader reader = req.getReader();
			StringBuilder sb = new StringBuilder();
			String rl = reader.readLine();
			
			while(rl != null) {
				sb.append(rl);
				rl = reader.readLine();
			}
			
			String body = new String(sb);
			LoginDTO loginDTO = om.readValue(body, LoginDTO.class);
			
			if(ls.login(loginDTO.ers_username, loginDTO.ers_password)) {
				HttpSession ses = req.getSession();			
				res.setStatus(200);
				ERSUsers eu = eus.getUser(loginDTO.ers_username);
				String json = om.writeValueAsString(eu);
				res.getWriter().print(json);		
				ses.setAttribute("username", loginDTO); 
				ses.setAttribute("loggedIn", true);
				System.out.println("here");
			} else {
				HttpSession ses = req.getSession(false);
				
				if(ses != null) {
					ses.invalidate();
				}
				
				res.setStatus(401);
				res.getWriter().print("Unable to login, please try again.");
			} 	
			
		}  

	}

}
