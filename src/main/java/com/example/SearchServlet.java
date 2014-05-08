package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
import org.json.JSONObject;

public class SearchServlet extends HttpServlet {
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
		SearchEngine searchEngine = new SearchEngine(req.getParameterMap(), req.getParameterNames());
		VelocityContext context = new VelocityContext();
		System.out.println(req.getParameter("price"));
		Template temp = null;
		try{
			temp = engine.getTemplate("search.html", "UTF-8");
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
		Item[] items = new Item[searchEngine.getItems().size()];
		System.out.println(searchEngine.getItems().size());
		int i=0;
		for(Item item:searchEngine.getItems()){
			items[i]=item;
			i++;
		}
		context.put("results", items);
		PrintWriter write = resp.getWriter();
		temp.merge(context, write);
		write.println(write);

	}
	@Override
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        engine = createVelocityEngine(config.getServletContext());
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("POST INVOKED");
		BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
		System.out.println(req.getParameter("search"));
		
	}
}
