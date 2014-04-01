package com.example;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Enumeration enums = req.getParameterNames();
		HashMap<String, String> map = new HashMap<String, String>();
		while(enums.hasMoreElements()){
			String paramName = (String)enums.nextElement();
			String paramValue = req.getParameter(paramName);
			map.put(paramName, paramValue);
		}
		DBProvider dbprovider = new DBProvider();
		
	}
}
