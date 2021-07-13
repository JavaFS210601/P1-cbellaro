package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static void main(String[] args) {
			
			try(Connection conn = ConnectionUtil.getConnection()) {
				System.out.println("Connection established w/ DB");
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Couldn't connect to DB");
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=P1";
		String username = "postgres";
		String password = "password";

		return DriverManager.getConnection(url, username, password);
	}
}
