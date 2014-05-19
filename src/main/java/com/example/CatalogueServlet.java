package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
@WebServlet(name = "CatalogueServlet", urlPatterns = {"/catalogue"})
public class CatalogueServlet extends HttpServlet {
	
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
		Template temp = null;
		try{
			temp = engine.getTemplate("catalogue.html", "UTF-8");
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
		List<CatalogueItem> loll=mapSelections();
		CatalogueItem[] items = new CatalogueItem[loll.size()];
		int i=0;
		for(CatalogueItem item:loll){
			items[i]=item;
			i++;
		}
		resp.setContentType("text/html");
		resp.setCharacterEncoding("charset=UTF-8");
		context.put("results", items);
		temp.merge(context,resp.getWriter());

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
		System.out.println(br.readLine());
		
	}
	
	private List<CatalogueItem> mapSelections() {
		List<CatalogueItem> retrievedItems = new ArrayList<CatalogueItem>();
		DBProvider dbengine = new DBProvider();
		ResultSet rs;
		try {
			rs = dbengine.execute("SELECT * FROM locationStatistics");
			while(rs.next()) {
				retrievedItems.add(new CatalogueItem(rs.getInt(1), rs.getInt(2)));
			}
			rs.close();
			dbengine.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedItems;
	}
}
