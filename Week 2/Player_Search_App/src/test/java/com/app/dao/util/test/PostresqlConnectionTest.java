package com.app.dao.util.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.app.dao.util.PostresqlConnection;

class PostresqlConnectionTest {
	
	private Connection connection;
	PostresqlConnection pos;
	
	@BeforeAll
	public static void createConnectionObj() {
		
	}

	@Test
	void test() {
		try {
			
			assertNotNull(PostresqlConnection.getConnection());
			System.out.println(connection);
			System.out.println(PostresqlConnection.getConnection());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
