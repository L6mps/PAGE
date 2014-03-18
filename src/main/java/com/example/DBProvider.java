package com.example;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
		    URI dbUri = new URI(host);

		    String username = user;
		    String password = pword;
		    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
		    
		    return DriverManager.getConnection(dbUrl, username, password);
		}
		
		public ResultSet execute(String query) throws SQLException{
			ResultSet rs = null;
			//Statement statement = connection.createStatement();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
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
