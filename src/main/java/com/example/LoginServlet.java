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

import org.json.JSONObject;

public class LoginServlet extends HttpServlet {
	
	DBProvider dbprovider;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		dbprovider = new DBProvider();
		JSONObject json = new JSONObject();
		try{		
			String action = request.getParameter("action");
			String userName = request.getParameter("user");
			String password = request.getParameter("password");
			String loginSessionID = request.getParameter("sessionID");
			if(action==null){
				json.append("status","error");
			} else {
				System.out.println("asd");
				if(action=="login"){
					json=login(userName,password);
				}
				else if(action.equals("verify")){
					System.out.println("asd");
					json=verify(loginSessionID);
				}
				else if(action=="logout"){
					json=logout(userName,loginSessionID);
				}
			}
		} catch (Exception e){
			json.append("status","error");
			json.append("data","generalfailure");
			//Now what?
			e.printStackTrace();
		}
		PrintWriter outWriter = response.getWriter();
		response.setContentType("application/json");
		outWriter.println(json);
		outWriter.close();
		dbprovider.closeConn();
	}
	private String checkCachedSession(String checkableSessionId){
		try {
			System.out.println(checkableSessionId);
			ResultSet rs = dbprovider.execute("SELECT * FROM activeSessions where sessionid = '"+checkableSessionId+"'");
			boolean canEdit=false;
			if(rs.next())
				canEdit = rs.getBoolean(3);
			System.out.println("asd"+canEdit);
			rs.close();
			if(canEdit){
				return "verifysuccess;true";		
			} else {
				return "verifysuccess;false";
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
	
	private JSONObject login(String user, String pw){
		String sessionID = createSessionId();
		String data = verifyUserLogin(user, pw, sessionID);
		JSONObject json = new JSONObject();
		if(data==null){
			json.append("status", "error");
			json.append("data","");
			return json;
		} else {
			json.append("status", "success");
			json.append("data", data);
			json.append("sessionID",sessionID);
			return json;
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
	
	private JSONObject verify(String sessionCheckID){
		String data = checkCachedSession(sessionCheckID);
		String[] split = data.split(";");
		data=split[1];
		JSONObject json = new JSONObject();
		if(data==null){
			System.out.println("madeithere");
			json.append("status", "error");
			json.append("data","");
			return json;
		} else {
			System.out.println("madeit");
			json.append("status", "success");
			json.append("data", data);
			return json;
		}
	}
	
	private JSONObject logout(String user, String loginSessionId){
		JSONObject json = new JSONObject();
		if(executeLogout(user, loginSessionId)){
			try {
				dbprovider.execute("DELETE FROM activeSessions WHERE sessionid = '"+loginSessionId+"'");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			json.append("status","success");
			return json;
		} else {
			json.append("status","error");
			return json;	
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