package com.revature.daos;

import java.util.List;

import com.revature.models.ERSReimbursement;

public interface ERSReimbursementInterface {

	//private int reimb_id;
	//private double reimb_amount;
	//private String reimb_submitted;
	//private String reimb_resolved;
	//private String reimb_description;
	//private int reimb_author_fk;
	//private int reimb_resolver_fk;
	//private int reimb_status_id_fk;
	//private int reimb_type_id_fk;
	
	public void addReimbursement(int reimb_type_id_fk, String reimb_description, double reimb_amount);
	public List<ERSReimbursement> pendingReimbursements();	
	public List<ERSReimbursement> pastReimbursements();
	public void resolveReimbursement(int reimb_id, int reimb_status_id_fk);
	public List<ERSReimbursement> allReimbursements();
}
