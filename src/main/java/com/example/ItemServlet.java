package com.example;

import java.io.IOException;

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
		
		String reqId = req.getParameter("id");
		int id = Integer.parseInt(reqId);
	}

}
