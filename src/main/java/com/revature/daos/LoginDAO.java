package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.utils.ConnectionUtil;

public class LoginDAO implements LoginDAOInterface {

	@Override
	public String getUsername(String ers_username) {
		try (Connection conn = ConnectionUtil.getConnection()){
			ResultSet rs = null;
			String sql = "SELECT ers_username FROM ers_users WHERE ers_username = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ers_username); //1 references question mark above
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String result = rs.getString("ers_username");
			return result;
			}	
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getPassword(String ers_password) {
		try (Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql = "SELECT ers_password FROM ers_users WHERE ers_password = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ers_password); //1 references question mark above
			rs = ps.executeQuery();

			if(rs.next()) {
				String result = rs.getString("ers_password");
			return result;
			}
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}	

}
