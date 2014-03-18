package com.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
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
		DBProvider dbtesting = new DBProvider();
		ResultSet rs = null;
        ServletOutputStream out = resp.getOutputStream();
        try {
			rs = dbtesting.execute("select * from Generalinfo");
			
		} catch (SQLException e) {
			out.write(("Database query failed. Message : " + e.getMessage()).getBytes());
			
		}
        if(rs!=null){
        	try {
        		while(rs.next()){
        			int id = rs.getInt(1);
        			String name = rs.getString(2);
        			int price = rs.getInt(5);
        			out.write(("ID: "+id+";Name: "+name+";Price: "+price+"\n").getBytes());
        		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } else {
        	out.write(("SQL Statement failed.").getBytes());
        }
        Enumeration enums = req.getAttributeNames();
        ServletInputStream inp = req.getInputStream();
        String tere = req.getParameter("tere");
//      byte[] arr = new byte[255];
//        int j = inp.read(arr, 0, 255);
//        out.write(("Bytes read: " + j + "; reading : " + arr.toString()).getBytes());
        //resp.sendRedirect("http://www.google.com");
        out.write("Hello PAGE\n".getBytes());
        out.write(("tere-param received :" + tere+"\n").getBytes());
        while(enums.hasMoreElements()){
        	out.write((enums.nextElement().toString()).getBytes());
        }
        out.flush();
        out.close();
	}
	
	
}
