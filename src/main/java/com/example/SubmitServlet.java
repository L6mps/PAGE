package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
@WebServlet(name = "SubmitServlet", urlPatterns = {"/submit"})
public class SubmitServlet extends HttpServlet{
	private VelocityEngine engine;
	private VelocityEngine createVelocityEngine(ServletContext cont){
		String path = cont.getRealPath("/");
        engine = new VelocityEngine();
        engine.addProperty("file.resource.loader.path", path);
        engine.init();
		return engine;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		boolean permission = permissionCheck(cookies);
		if(permission){
		Enumeration enums = req.getParameterNames();
		HashMap<String, String> map = new HashMap<String, String>();
		while(enums.hasMoreElements()){
			String paramName = (String)enums.nextElement();
			String paramValue = req.getParameter(paramName);
			map.put(paramName, paramValue);
		}
		DBProvider dbprovider = new DBProvider();
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO generalInfo (name, location, location_specific, price, mainoptions) VALUES (");
		sb.append("'"+map.get("name")+"', ");
		sb.append("'"+Integer.parseInt(map.get("D1"))+"', ");
		sb.append("'"+Integer.parseInt(map.get("D2"))+"', ");
		sb.append("'"+Integer.parseInt(map.get("price"))+"', ");
		int mainopts = 0;
		boolean[] services = new boolean[4];
		Arrays.fill(services, false);
		if(map.containsKey("demented") && map.get("demented").equals("1")){
			mainopts+=1;
			services[0] = true;
		}
		if(map.containsKey("wheelchair") && map.get("wheelchair").equals("1")){
			mainopts+=2;
			services[1] = true;
		}
		if(map.containsKey("nursing") && map.get("nursing").equals("1")){
			mainopts+=4;
			services[2] = true;
		}
		if(map.containsKey("paid services") && map.get("paid services").equals("1")){
			mainopts+=8;
			services[3] = true;
		}
		sb.append("'"+mainopts+"');");
		ResultSet rs = null;
		int newId;
		try {
			dbprovider.executeAdd(sb.toString());
			rs = dbprovider.execute("SELECT * FROM generalInfo where name='"+map.get("name")+"';");
			rs.next();
			newId = rs.getInt(1);
			rs.close();
			for(int i=0; i<4; i++){
				if(services[i]){
					dbprovider.executeAdd("INSERT INTO service"+(i+1)+" (id) VALUES ("+newId+ ");");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext();
		Template temp = null;
		try{
			temp = engine.getTemplate("html/added.html", "UTF-8");
		}
		catch( ResourceNotFoundException rnfe )
		{
			rnfe.printStackTrace();
		   // couldn't find the template
		}
		catch( ParseErrorException pee )
		{
			pee.printStackTrace();
		  // syntax error: problem parsing the template
		}
		catch( MethodInvocationException mie )
		{
			mie.printStackTrace();
		  // something invoked in the template
		  // threw an exception
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		temp.merge(context, sw);
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter write = resp.getWriter();
		write.println(sw);
		} else {
			resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have the required permission for this action!");
		}
	}
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        engine = createVelocityEngine(config.getServletContext());
    }
	
	private boolean permissionCheck(Cookie[] cookies){
		Cookie sessionCookie = null;
		for(Cookie cookie : cookies){
			if(cookie.getName().equalsIgnoreCase("session")){
				sessionCookie = cookie;
			}
		} if(sessionCookie==null){
			System.out.println("PERMISSIONS DENIED");
			return false;
		} else {
			String val = sessionCookie.getValue();
			DBProvider dbprovider = new DBProvider();
			val.replace(";","");
			try {
				ResultSet rs = dbprovider.execute("SELECT * FROM activeSessions where sessionid = '"+val+"'");
				rs.next();
				String user = rs.getString(2);
				boolean permission = rs.getBoolean(3);
				rs.close();
				dbprovider.closeConn();
				System.out.println("PERMISSIONS GRANTED!");
				return permission;
			} catch (SQLException e) {
				System.out.println("PERMISSIONS DENIED");
				//If the query fails, do not grant permission to edit!
				return false;
			}			
		}
	}
}