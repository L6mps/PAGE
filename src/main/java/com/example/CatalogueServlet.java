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

import org.json.JSONObject;

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
		JSONObject json = new JSONObject();
		json.append("status","success");
		JSONObject[] items;
		List<CatalogueItem> loll;
		if(req.getParameter("state")==null){
			loll=mapSelections();
			
		}
		else{
			loll=mapSelections(req.getParameter("state"));
		}
		items = new JSONObject[loll.size()];
		int i=0;
		for(CatalogueItem item:loll){
			items[i]=new JSONObject();
			items[i].append("count", item.getCount());
			items[i].append("location", item.getLocation());
			i++;
		}
		resp.setContentType("application/json");
		json.append("result",items);
		PrintWriter out = resp.getWriter();
		out.print(json);
		out.close();

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
	
	private int stateStringToInt(String state){
		if(state.equals("Muu"))
			return 0;
		else if(state.equals("Harjumaa"))
			return 1;
		else if(state.equals("Hiiumaa"))
			return 2;
		else if(state.equals("Ida-Virumaa"))
			return 3;
		else if(state.equals("J6gevamaa"))
			return 4;
		else if(state.equals("J2rvamaa"))
			return 5;
		else if(state.equals("L22nemaa"))
			return 6;
		else if(state.equals("L22ne-Virumaa"))
			return 7;
		else if(state.equals("Põlvamaa"))
			return 8;
		else if(state.equals("P2rnumaa"))
			return 9;
		else if(state.equals("Raplamaa"))
			return 10;
		else if(state.equals("Saaremaa"))
			return 11;
		else if(state.equals("Tartumaa"))
			return 12;
		else if(state.equals("Valgamaa"))
			return 13;
		else if(state.equals("Viljandimaa"))
			return 14;
		else if(state.equals("V6rumaa"))
			return 15;
		return -1;
	}
	
	private List<CatalogueItem> mapSelections(String state) {
		List<CatalogueItem> retrievedItems = new ArrayList<CatalogueItem>();
		DBProvider dbengine = new DBProvider();
		ResultSet rs;
		int s = stateStringToInt(state);
		try {
			rs = dbengine.execute("SELECT location_specific, count(*) FROM generalinfo WHERE location = "+s+" GROUP BY location_specific");
			while(rs.next()) {
				retrievedItems.add(new CatalogueItem(rs.getInt(1), rs.getInt(2), s));
			}
			rs.close();
			dbengine.closeConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retrievedItems;
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
