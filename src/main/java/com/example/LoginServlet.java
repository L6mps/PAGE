package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class LoginServlet extends HttpServlet {
	
	DBProvider dbprovider;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dbprovider = new DBProvider();
		String output = "";
		try{		
			String action = request.getParameter("action");
			String userName = request.getParameter("user");
			String password = request.getParameter("password");
			String loginSessionID = request.getParameter("sessionID");
			if(action==null){
				output = "{ 'status':'ERROR' }";
			} else {
				if(action=="login"){
					output = doLogin(userName, password);
				}
				else if(action=="verify"){
					output = doVerification(userName, loginSessionID);
				}
				else if(action=="logout"){
					output = doLogout(userName, loginSessionID);
				}
			}
		} catch (Exception e){
			output="{ 'status':'ERROR', 'data':'generalfailure' }";
			//Now what?
			e.printStackTrace();
		}
		PrintWriter outWriter = response.getWriter();
		response.setContentType("text/html");
		outWriter.println(output);
		outWriter.close();
		dbprovider.closeConn();
	}
	private String checkCachedSession(String checkableSessionId){
		try {
			ResultSet rs = dbprovider.execute("SELECT * FROM activeSessions where sessionid = '"+checkableSessionId+"'");
			rs.next();
			String cmpUser = rs.getString(2);
			rs.close();
			rs = dbprovider.execute("SELECT * FROM activesessions where username ='"+cmpUser+"';");
			rs.next();
			ArrayList<String> sessions = new ArrayList<String>();
			while(rs.next()){
				sessions.add(rs.getString(1));
				if(rs.getString(1)!=checkableSessionId){
					dbprovider.execute("DELETE FROM activesessions where sessionid ='"+rs.getString(1)+"';");
				}
			}
			for(String session : sessions){
				if(session==checkableSessionId){
					return "verifysuccess";
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "verifyfailure";
	}
	
	private String verifyUserLogin(String user, String password, String sessionId){
		//Hash against database match.
		boolean status = compareHashes(password, user);
		if(status){
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO activeSessions (sessionId, username, canedit) VALUES (");
			sb.append(sessionId+",");
			sb.append(user+",");
			sb.append("true)");
			try {
				dbprovider.execute(sb.toString());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "loginsuccess";
		} else {
			return "loginfailure";
		}
	}
	
	private String doLogin(String user, String pw){
		String sessionID = createSessionId();
		String data = verifyUserLogin(user, pw, sessionID);
		if(data==null){
			return "{ 'status':'ERROR', 'data':'' }";
		} else {
			return "{ 'status':'SUCCESS', 'data':'" + data +"', 'sessionID':'"+sessionID+"' }";
		}
	}
	
	private static String createSessionId(){
		UUID uid = UUID.randomUUID();
		return uid.toString();
		
	}
	public boolean compareHashes(String password, String username){
		String dataGet = "";
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM user_password WHERE username = '"+username+"';");
		ResultSet rs = null;	
		try {
			rs = dbprovider.execute(sb.toString());
			rs.next();
			dataGet = rs.getString(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs==null){
			return false;
		}
		String cmp = hashPassword(password, username);
		// Compare dataGet (hashed PW from database based on username) with the generated hash.
		if(dataGet==cmp){
			return true;
		}else {
			return false;
		}
	}
	
	public static String hashPassword( String password, String username )
	{
		return BCrypt.hashpw( password, username);
	}
	
	private String doVerification(String user, String sessionCheckID){
		String sessionID = createSessionId();
		String data = checkCachedSession(sessionCheckID);
		if(data==null){
			return "{ 'status':'ERROR', 'data':'' }";
		} else {
			return "{ 'status':'SUCCESS', 'data':'" + data +"', 'sessionID':'"+sessionID+"' }";
		}
	}
	
	private String doLogout(String user, String loginSessionId){
		if(executeLogout(user, loginSessionId)){
			try {
				dbprovider.execute("DELETE FROM activeSessions WHERE sessionid = '"+loginSessionId+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "{'status':'SUCCESS'}";
		} else {
			return "{'status':'ERROR'}";	
		}
		
	}
	
	private boolean executeLogout(String user, String loginSessionId){
		try {
			ResultSet rs = dbprovider.execute("SELECT * FROM activeSessions where username = '"+user+"'");
			rs.next();
			String cmpID = rs.getString(1);
			rs.close();
			rs = dbprovider.execute("SELECT * FROM activeSessions where sessionid = '"+loginSessionId+"'");
			rs.next();
			String cmpUser = rs.getString(1);
			if(user==cmpUser && loginSessionId==cmpID){
				return true;
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}