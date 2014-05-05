package com.example;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;

//TODO : This servlet will use velocity templating engine to display any given Item and it's contents + generate a google map window for it.
public class ItemServlet extends HttpServlet {

	private VelocityEngine engine;
	private DBProvider dbprovider;

	private VelocityEngine createVelocityEngine(ServletContext cont) {
		String path = cont.getRealPath("/");
		System.out.println(path);
		engine = new VelocityEngine();
		engine.addProperty("file.resource.loader.path", path);
		engine.init();
		return engine;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		engine = createVelocityEngine(config.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		dbprovider = new DBProvider();
		String reqId = req.getParameter("id");
		int id = Integer.parseInt(reqId);

		// TODO: Ask for item based on ID.
		/*
		 * https://www.google.com/maps/embed/v1/view
		 * ?key=AIzaSyAfJ2X30a1oZj9Ivcnx9Q9J_R7RxZtP9z4 &center=coordshere
		 * &zoom=18 &maptype=satellite
		 */
		String query = "select * from generalinfo where id = '"+id+"';"; // MAKE QUERY HERE.
		try {

			ResultSet rs = dbprovider.execute(query);
			rs.next();
			int loc1 = rs.getInt(3);
			int loc2 = rs.getInt(4);
			int price = rs.getInt(5);
			String summary = rs.getString(6);
			String coords = rs.getString(11);
			String name = rs.getString(2);
			String options = rs.getString(10);
			String optionals = rs.getString(9);
			String fulltext = rs.getString(7);
			String picAddress = rs.getString(8);
		} catch (SQLException e) {
			// Return failure message. 
			e.printStackTrace();
		}
		
		
		
	}
}
