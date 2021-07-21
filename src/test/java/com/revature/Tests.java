package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.revature.services.LoginService;
import com.revature.daos.LoginDAO;
import com.revature.services.ERSReimbursementService;
import com.revature.services.ERSUsersService;

public class Tests {

	LoginService ls = new LoginService();
	ERSReimbursementService ers = new ERSReimbursementService();
	ERSUsersService eus = new ERSUsersService();
	LoginDAO lDAO = new LoginDAO();
	
	public String ers_username;
	public String ers_password;
	public int reimb_type_id_fk;
	public String reimb_description;
	public double reimb_amount;
	
	@Test
	public void testValidLogin() {
		System.out.println("testing valid login...");
		assertTrue(ls.login("cbellaro", "password1"));
	}
	
	@Test
	public void testInvalidLogin() {
		System.out.println("testing invalid login...");
		assertFalse(ls.login("cbellaro", "asdf"));
	}

	@Test
	public void testgetUsername() {
		System.out.println("testing getUsername...");
		assertEquals(ers_username = "dkelly", lDAO.getUsername(ers_username));
	}
	
	@Test
	public void testgetPassword() {
		System.out.println("testing getPassword...");
		assertEquals(ers_password = "password2", lDAO.getPassword(ers_password));
	}
	
	@Test
	public void testPendingReimbursements() {
		System.out.println("testing pendingReimburesements...");
		assertEquals(ArrayList.class, ers.pendingReimbursements().getClass());
	}
	
	@Test
	public void testPastReimbursements() {
		System.out.println("testing pastReimburesements...");
		assertEquals(ArrayList.class, ers.pastReimbursements().getClass());
	}
	
	@Test
	public void testPendingVsPastReimbursements() {
		System.out.println("testing pendingReimbursements and pastReimbursements return different values...");
		assertNotEquals(ers.pendingReimbursements(), ers.pastReimbursements());
	}
	
	@Test
	public void testAllReimbursements() {
		System.out.println("testing allReimburesements...");
		assertEquals(ArrayList.class, ers.allReimbursements().getClass());
	}
	
	@Test
	public void testAllvsPendingReimbursements() {
		System.out.println("testing allReimbursements and pendingReimbursements return different values...");
		assertNotEquals(ers.pendingReimbursements(), ers.allReimbursements());
	}
	
	@Test
	public void testAllvsPastReimbursements() {
		System.out.println("testing allReimbursements and pastReimbursements return different values...");
		assertNotEquals(ers.pastReimbursements(), ers.allReimbursements());
	}
	
}
