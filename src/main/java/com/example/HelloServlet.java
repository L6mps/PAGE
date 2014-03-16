package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class HelloServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {		
        ServletOutputStream out = resp.getOutputStream();
        ServletInputStream inp = req.getInputStream();
        resp.sendError(9001, "Too good to be displayed");
        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        resp.setHeader("Location", "http://www.google.com");
        //resp.sendRedirect("http://www.google.com");
        out.write("Hello PAGE\n".getBytes());
        out.flush();
        out.close();
	}
	
}
