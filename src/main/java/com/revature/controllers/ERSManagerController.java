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

public class ERSManagerController {
	
	private ERSReimbursementService ers = new ERSReimbursementService();
	private ObjectMapper om = new ObjectMapper();
	
	public void resolveReimbursement(HttpServletRequest req, HttpServletResponse res) throws IOException {
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
			ers.resolveReimbursement(rDTO.reimb_id, rDTO.reimb_status_id_fk);		
			res.setStatus(200);
		}
	}
	
	public void pendingReimbursements (HttpServletResponse res) throws IOException {
		List<ERSReimbursement> reimbursements = ers.pendingReimbursements();
		String json = om.writeValueAsString(reimbursements);
		res.getWriter().print(json);
		res.setStatus(200);
	}
	
	public void allReimbursements(HttpServletResponse res) throws IOException {
		List<ERSReimbursement> reimbursements = ers.allReimbursements();
		String json = om.writeValueAsString(reimbursements);
		res.getWriter().print(json);
		res.setStatus(200);
	}
}
