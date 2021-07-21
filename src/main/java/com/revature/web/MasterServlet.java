package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.ERSManagerController;
import com.revature.controllers.ERSUsersController;
import com.revature.controllers.LoginController;

public class MasterServlet extends HttpServlet {

	private ERSManagerController eMC = new ERSManagerController();
	private ERSUsersController eUC = new ERSUsersController();
	private LoginController lC = new LoginController();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("application/json");
		res.setStatus(404);
		final String URI = req.getRequestURI().replace("/P1/", "");
		URI.toLowerCase();
		System.out.println(URI);
		switch (URI) {
			case "login":
				//if(req.getSession(false) != null) {
					lC.login(req, res);
				//} else {
					//res.setStatus(403);
				//}
			break;
				
			case "user/addreimbursements":
				//if(req.getSession(false) != null) {
					eUC.addReimbursement(req, res);
				//} else {
				//	res.setStatus(403);
				//}
				
				break;
			
			case "user/pendingreimbursements":
				eUC.pendingReimbursements(res);
				break;
				
			case "user/pastreimbursements":
				eUC.pastReimbursements(res);
				break;
				
			case "manager/resolvereimbursements":
				eMC.resolveReimbursement(req, res);
				break;
			
			case "manager/pendingreimbursements":
				eMC.pendingReimbursements(res);
				break;
				
			case "manager/allreimbursements":
				eMC.allReimbursements(res);
				break;
				
		} 
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
