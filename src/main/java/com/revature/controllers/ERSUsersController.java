package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ERSReimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.services.ERSReimbursementService;

public class ERSUsersController {
	private ERSReimbursementService ers = new ERSReimbursementService();
	private ObjectMapper om = new ObjectMapper();
	
	public void addReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
		if(req.getMethod().equals("POST")) {
			BufferedReader br = req.getReader();
			StringBuilder sb = new StringBuilder();
			String rl = br.readLine();
			
			while(rl != null) {
				sb.append(rl);
				rl = br.readLine();
			}
			
			String rmb = new String(sb);
			ReimbursementDTO rDTO = om.readValue(rmb, ReimbursementDTO.class);
			ers.addReimbursement(rDTO.reimb_type_id_fk, rDTO.reimb_description, rDTO.reimb_amount);		
			res.setCharacterEncoding("UTF-8");
			res.getWriter().print("here");
			res.setStatus(200);
		}
	}
	
	public void pendingReimbursements (HttpServletResponse res) throws IOException {
		List<ERSReimbursement> reimbursements = ers.pendingReimbursements();
		String json = om.writeValueAsString(reimbursements);
		res.getWriter().print(json);
		res.setStatus(200);
	}
	
	public void pastReimbursements(HttpServletResponse res) throws IOException {
		List<ERSReimbursement> reimbursements = ers.pastReimbursements();
		System.out.println("test" + reimbursements);
		String json = om.writeValueAsString(reimbursements);
		res.getWriter().print(json);
		res.setStatus(200);
	}
}
