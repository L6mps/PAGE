package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.Velocity;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class SearchServlet extends HttpServlet {
	private VelocityEngine engine = new VelocityEngine();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext();
		Template temp = null;		
		try{
			temp = Velocity.getTemplate("./src/main/webapp/html/template-velocity.html", "UTF-8");
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
		{}
		context.put("helloworld", 5);
		temp.merge(context, sw);
		PrintWriter write = resp.getWriter();
		write.println(sw);

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		engine.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	}
}
