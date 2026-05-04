package com.paula.checkmc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtils {
	public static Connection getConnection() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		        return  DriverManager.
		        				getConnection("jdbc:mysql://localhost:3306/checkmycar", 
												"root", "abc123.");
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  null;
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection c) {
		// TODO Auto-generated method stub
		
	}
   
;


}
