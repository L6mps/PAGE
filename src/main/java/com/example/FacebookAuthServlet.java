package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class FacebookAuthServlet extends HttpServlet{
	//TODO: FBAuth peab:	
	// 1: uue kasutaja login - genereerib andmebaasi kirje
	// 2: olemasoleva kasutaja login - verifyb andmebaasi kirje
	// 3: Loob uue sessiooni serveris.
	
	DBProvider dbprovider;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dbprovider = new DBProvider();
		String userId = req.getParameter("userId");
		String output = "";
		JSONObject json=new JSONObject();
		System.out.println("I'm being used! By:" + userId);
		try{
			json= handleLogin(userId);
		} catch (Exception e){
			json.append("stats", "error");
			json.append("data", "generalfailure");
			output = "{'stats':'error', 'data':'generalfailure'}";
			// THIS IS BAD.
		}
		PrintWriter outWriter = resp.getWriter();
		resp.setContentType("application/json; charset=UTF-8");
		outWriter.println(json);
		outWriter.close();
		dbprovider.closeConn();
	}	
	
	private JSONObject handleLogin(String userId){
		String dataGet = null;
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM users WHERE fbid = '"+userId+"';");
		ResultSet rs = null;
		try {
			rs = dbprovider.execute(sb.toString());
			if(rs.next()){
				dataGet = rs.getString(4);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rs=null;
		}
		JSONObject json=new JSONObject();
		json.append("status", "error");
		json.append("data", "verifyfailure");
		if(dataGet==null){
			json = addNewFacebookUser(userId);
		} else if(dataGet == userId){
			json = doLogin(userId);
			
		}
		return json;
	}
	
	private JSONObject addNewFacebookUser(String userId){
		//TODO: Add facebook user to database.
		return doLogin(userId);
	}
	
	private static String createSessionId(){
		UUID uid = UUID.randomUUID();
		return uid.toString();
		
	}
	
	
	private String verifyUserLogin(String user, String sessionId){
		
			StringBuilder sb = new StringBuilder();
			sb.append("INSERT INTO activeSessions (sessionId, username, canedit) VALUES (");
			sb.append("'"+sessionId+"',");
			sb.append("'"+user+"',");
			sb.append("true)");
			try {
				dbprovider.executeAdd(sb.toString());
				//dbprovider.execute(sb.toString());
			} catch (SQLException e) {
				e.printStackTrace();
				return "loginfailure";
			}
			return "loginsuccess";
		}
	
	private JSONObject doLogin(String user){
		String sessionID = createSessionId();
		System.out.println(sessionID);
		String data = verifyUserLogin(user, sessionID);
		JSONObject json=new JSONObject();
		if(data==null){
			json.append("status", "error");
			json.append("data", "");
			return json;
		} else{
			System.out.println("wtf");
			json.append("status", "success");
			json.append("data", sessionID);
			return json;
		}
	}
}
