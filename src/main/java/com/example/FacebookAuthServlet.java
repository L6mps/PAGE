package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "FacebookAuth", urlPatterns = {"/facebook"})
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
		System.out.println("I'm being used! By:" + userId);
		try{
			output = handleLogin(userId);
		} catch (Exception e){
			output = "{'status':'error', 'data':'generalfailure'}";
			// THIS IS BAD.
		}
		PrintWriter outWriter = resp.getWriter();
		resp.setContentType("application/json");
		outWriter.println(output);
		outWriter.close();
		dbprovider.closeConn();
	}	
	
	private String handleLogin(String userId){
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
		String data = "{'status':'failure', 'data':'verifiyfailure'}";
		if(dataGet==null){
			data = addNewFacebookUser(userId);
		} else if(dataGet == userId){
			data = doLogin(userId);
			
		}
		return data;
	}
	
	private String addNewFacebookUser(String userId){
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
	
	private String doLogin(String user){
		String sessionID = createSessionId();
		System.out.println(sessionID);
		String data = verifyUserLogin(user, sessionID);
		if(data==null){
			return "{ 'status':'error', 'data':'' }";
		} else {
			System.out.println("wtf");
			return "{ 'status':'success', 'data':'"+sessionID+"' }";
		}
	}
}
