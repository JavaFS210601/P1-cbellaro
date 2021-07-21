package com.revature.services;

import java.util.List;

import com.revature.daos.ERSReimbursementDAO;
import com.revature.models.ERSReimbursement;

public class ERSReimbursementService {

	ERSReimbursementDAO erd = new ERSReimbursementDAO();
	
	public void addReimbursement(int reimb_type_id_fk, String reimb_description, double reimb_amount) {
		if(reimb_type_id_fk >= 1 && reimb_type_id_fk <= 4) {
			erd.addReimbursement(reimb_type_id_fk, reimb_description, reimb_amount);
		} else {
			System.out.println("Couldn't add reimbursement, please try again");
		}
	}
	
	public List<ERSReimbursement> pendingReimbursements() {
		return erd.pendingReimbursements();
	}
	
	public List<ERSReimbursement> pastReimbursements() {
		return erd.pastReimbursements();
	}

	public void resolveReimbursement(int reimb_id, int reimb_status_id_fk) {
		if(reimb_status_id_fk >= 0 && reimb_status_id_fk <= 3) {
			erd.resolveReimbursement(reimb_id, reimb_status_id_fk);
		} else {
			System.out.println("Couldn't resolve reimbursement, please try again");
		}
	}
	
	public List<ERSReimbursement> allReimbursements() {
		return erd.allReimbursements();
	}
}
