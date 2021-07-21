package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.models.ERSUsers;
import com.revature.utils.ConnectionUtil;

public class ERSUsersDAO implements ERSUsersInterface {

	public ERSUsers getUser(String username) {
		return getUserByUsername(username);
	}	
	
	@Override
	public ERSUsers getUserByUsername(String username) {
		try (Connection conn = ConnectionUtil.getConnection()){		
			ResultSet rs = null;		
			String sql = "SELECT * FROM ers_users WHERE ers_username = ?;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ERSUsers eu = new ERSUsers (
					rs.getInt("ers_users_id"),
					rs.getString("ers_username"),
					rs.getString("ers_password"),
					rs.getString("user_first_name"),
					rs.getString("user_last_name"),
					rs.getString("user_email"),
					rs.getInt("user_role_fk")
					);
				
				return eu;
			}
					
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
