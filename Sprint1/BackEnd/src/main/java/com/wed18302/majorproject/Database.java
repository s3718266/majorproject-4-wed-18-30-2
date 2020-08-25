package com.wed18302.majorproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public String database = "database";
	public String username = "root";
	public String password = "sdVw&cK2";
	
	Connection conn;
	
	public Database() {

        try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			
	        //Creating the connection with HSQLDB
			conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/database", username, password);
	        if (conn != null){
	           System.out.println("Major project has successfully connected to the JDB.");
	        } else {
	        	throw new ConnectionError();
	        }
		} catch (ConnectionError e) {
			System.err.println("Could not connect to the JDB. Make sure the JDB is running.");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("serial")
	public class ConnectionError extends Exception {
		public ConnectionError() {
			super("Major project has successfully connected to the JDB.");
		}
	}

	
}