package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String loginSessionID = req.getParameter("accessToken");
		String output = "";
		System.out.println("I'm being used! By:" + userId);
		try{
			output = handleLogin(userId, loginSessionID);
		} catch (Exception e){
			output = "{'status':'ERROR', 'data':'generalfailure'}";
			// THIS IS BAD.
		}
		PrintWriter outWriter = resp.getWriter();
		resp.setContentType("text/html");
		outWriter.println(output);
		outWriter.close();
		dbprovider.closeConn();
	}	
	
	private String handleLogin(String userId, String token){
		
		
		return "";
	}
	
	private boolean addNewFacebookUser(String userId){
		
		return true;
	}
}
