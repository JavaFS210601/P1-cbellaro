package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.ERSReimbursement;
import com.revature.utils.ConnectionUtil;

public class ERSReimbursementDAO implements ERSReimbursementInterface{

	@Override
	public void addReimbursement(int reimb_type_id_fk, String reimb_description, double reimb_amount) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			//private int reimb_id;
			//private double reimb_amount;
			//private String reimb_submitted;
			//private String reimb_resolved;
			//private String reimb_description;
			//private int reimb_author_fk;
			//private int reimb_resolver_fk;
			//private int reimb_status_id_fk;
			//private int reimb_type_id_fk;
			
			String sql = "INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author_fk, reimb_resolver_fk, reimb_status_id_fk, reimb_type_id_fk) values (?, current_timestamp, null, ?, 1, null, 0, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, reimb_amount);
			ps.setString(2, reimb_description);
			ps.setInt(3, reimb_type_id_fk);
			ps.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	@Override
	public List<ERSReimbursement> pendingReimbursements() {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id_fk = 0;";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();	
			List<ERSReimbursement> list = new ArrayList<>();
			
			//private int reimb_id;
			//private double reimb_amount;
			//private String reimb_submitted;
			//private String reimb_resolved;
			//private String reimb_description;
			//private int reimb_author_fk;
			//private int reimb_resolver_fk;
			//private int reimb_status_id_fk;
			//private int reimb_type_id_fk;
			
			while(rs.next()) {
				ERSReimbursement eR = new ERSReimbursement (
					rs.getInt("reimb_id"), 
					rs.getDouble("reimb_amount"),
					rs.getString("reimb_submitted"),
					rs.getString("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("reimb_author_fk"),
					rs.getInt("reimb_resolver_fk"),
					rs.getInt("reimb_status_id_fk"),
					rs.getInt("reimb_type_id_fk")								
					);
						
				list.add(eR);
			}
			
			return list;
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ERSReimbursement> pastReimbursements() {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;	
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id_fk != 0;"; //doesn't show reimbursements with status 0 in dbeaver, but still does on my webpage?	
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();	
			List<ERSReimbursement> list = new ArrayList<>();
			
			while(rs.next()) {
				ERSReimbursement eR = new ERSReimbursement (
					rs.getInt("reimb_id"), 
					rs.getDouble("reimb_amount"),
					rs.getString("reimb_submitted"),
					rs.getString("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("reimb_author_fk"),
					rs.getInt("reimb_resolver_fk"),
					rs.getInt("reimb_status_id_fk"),
					rs.getInt("reimb_type_id_fk")								
					);	
				list.add(eR);
			}
			return list;
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void resolveReimbursement(int reimb_id, int reimb_status_id_fk) {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "UPDATE ers_reimbursement SET reimb_status_id_fk = ?, reimb_resolver_fk = 3, reimb_resolved = current_timestamp WHERE reimb_id = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimb_status_id_fk);
			ps.setInt(2, reimb_id);
			ps.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public List<ERSReimbursement> allReimbursements() {
		// TODO Auto-generated method stub
		try (Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT * FROM ers_reimbursement;";
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<ERSReimbursement> list = new ArrayList<>();
			
			while(rs.next()) {
				ERSReimbursement r = new ERSReimbursement (
					rs.getInt("reimb_id"), 
					rs.getDouble("reimb_amount"),
					rs.getString("reimb_submitted"),
					rs.getString("reimb_resolved"),
					rs.getString("reimb_description"),
					rs.getInt("reimb_author_fk"),
					rs.getInt("reimb_resolver_fk"),
					rs.getInt("reimb_status_id_fk"),
					rs.getInt("reimb_type_id_fk")								
					);		
				list.add(r);
			}
			
			return list;
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
