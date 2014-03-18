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
        String tere = req.getParameter("tere");
//      byte[] arr = new byte[255];
//        int j = inp.read(arr, 0, 255);
//        out.write(("Bytes read: " + j + "; reading : " + arr.toString()).getBytes());
        //resp.sendRedirect("http://www.google.com");
        out.write("Hello PAGE\n".getBytes());
        out.write(("tere-param received :" + tere).getBytes());
        out.flush();
        out.close();
	}
	
	
}
