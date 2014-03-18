package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBProvider {
	
	
		Connection connection = null;
	
		public DBProvider(){
			try {
				connection = getConnection();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		String host = "ec2-107-20-191-205.compute-1.amazonaws.com";
		String dbname = "d146m591db0oro";
		String user = "wtqukymzogogqu";
		int port = 5432;
		String pword = "X7CFIewBmXmSeC2GIPuTzB0ytF";
	
		private Connection getConnection() throws URISyntaxException, SQLException {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		    URI dbUri = new URI(host);

		    String username = user;
		    String password = pword;
		    String url = "jdbc:postgresql://ec2-107-20-191-205.compute-1.amazonaws.com:5432/d146m591db0oro?user=wtqukymzogogqu&password=X7CFIewBmXmSeC2GIPuTzB0ytF&ssl=true";
		    Connection conn = DriverManager.getConnection(url);
		    
		    return conn;
		}
		
		public ResultSet execute(String query) throws SQLException{
			ResultSet rs = null;
			PreparedStatement preparedStatement = null;
			//Statement statement = connection.createStatement();
			try{
				preparedStatement = connection.prepareStatement(query);				
			} catch (SQLException e){
				e.printStackTrace();
			}
			rs = preparedStatement.executeQuery();
			return rs;
		}
		
		public void closeConn(){
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					//Connection already closed or broken. Ignore
				}
			}
		}
		
		
	    
	    
}
