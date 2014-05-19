package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        System.out.println(path);
        engine = new VelocityEngine();
        engine.addProperty("file.resource.loader.path", path);
        engine.init();
		return engine;
	}
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
		System.out.println("D1 is : " + map.get("D1"));
		DBProvider dbprovider = new DBProvider();
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO generalInfo (name, location, location_specific, price, mainoptions) VALUES (");
		sb.append("'"+map.get("name")+"', ");
		sb.append("'"+Integer.parseInt(map.get("D1"))+"', ");
		sb.append("'"+Integer.parseInt(map.get("D2"))+"', ");
		sb.append("'"+Integer.parseInt(map.get("price"))+"', ");
		int mainopts = 0;
		if(map.containsKey("demented") && map.get("demented").equals("1"))
			mainopts+=1;
		if(map.containsKey("wheelchair") && map.get("wheelchair").equals("1"))
			mainopts+=2;
		if(map.containsKey("nursing") && map.get("nursing").equals("1"))
			mainopts+=4;
		if(map.containsKey("paid services") && map.get("paid services").equals("1"))
			mainopts+=8;
		sb.append("'"+mainopts+"');");
		try {
			dbprovider.executeAdd(sb.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext();
		System.out.println(req.getParameter("price"));
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
		PrintWriter write = resp.getWriter();
		write.println(sw);
	}
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        engine = createVelocityEngine(config.getServletContext());
    }
}