package com.app.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostresqlConnection {

	private static Connection conn; 
	
	private PostresqlConnection() {
		// TODO Auto-generated constructor stub
	}
	
	public static Connection getConnection () throws ClassNotFoundException, SQLException
	{
		Class.forName("org.postgresql.Driver");
		
		
		//step 2 - Create Connection
		String name = "postgres";
		String pass = "1234";
		String url = "jdbc:postgresql://localhost:5432/postgres";
		//connection = DriverManager.getConnection(url, name, pass);
		conn = DriverManager.getConnection(url, name, pass);
		
		//System.out.println("Connection Done.");
		
		return conn;
		
	}
	

}
