package com.revature.models;

public class ReimbursementDTO {

	public int reimb_id;
	public double reimb_amount;
	public String reimb_description;
	public int reimb_status_id_fk;
	public int reimb_type_id_fk;
	
	public ReimbursementDTO() {
		super();
	}
	
	public ReimbursementDTO(int reimb_type_id_fk, String reimb_description, double reimb_amount) {
		super();
		this.reimb_type_id_fk = reimb_type_id_fk;
		this.reimb_description = reimb_description;
		this.reimb_amount = reimb_amount;
	}
	
	public ReimbursementDTO(int reimb_status_id_fk) {
		super();
		this.reimb_status_id_fk = reimb_status_id_fk;
	}
	
	public ReimbursementDTO(int reimb_id, int reimb_status_id_fk) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_status_id_fk = reimb_status_id_fk;
	}
	
}
