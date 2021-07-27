package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
//	public static void main(String[] args) {
//	//here we're just testing whether our connection (from the ConnectionUtil Class) is successful
//	//remember - the getConnection() method will return a Connection object if you successfully reach the database
//	try(Connection conn = ConnectionUtil.getConnection()) {
//		System.out.println("connection successful");
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
//}


public static Connection getConnection() throws SQLException{
	
	try {
		Class.forName("org.postgresql.Driver");
	}catch (ClassNotFoundException e) {
		System.out.println("Class wasn't found");
		e.printStackTrace(); 
	}
	
	
	String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=p1";
	String username = "postgres";
	String password = "jcluforever6";
	
	return DriverManager.getConnection(url, username, password);
	 
}

}
