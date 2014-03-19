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

import org.json.JSONArray;
import org.json.JSONObject;

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
			out.write(("Database query failed. Message : " + e.getMessage())
					.getBytes());

		}
		JSONObject jo;
		JSONArray ja = new JSONArray();
		if (rs != null) {
			try {
				while (rs.next()) {
					jo = new JSONObject();
					int id = rs.getInt(1);
					String name = rs.getString(2);
					int price = rs.getInt(5);
					jo.put("id", id);
					jo.put("name", name);
					jo.put("price", price);
					out.write(("ID: " + id + ";Name: " + name + ";Price: "
							+ price + "\n").getBytes());
					ja.put(jo);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject returnable = new JSONObject();
			returnable.put("rethome", ja);
		} else {
			out.write(("SQL Statement failed.").getBytes());
		}
		String tere = req.getParameter("tere");
		out.write("Hello PAGE\n".getBytes());
		out.write(("tere-param received :" + tere + "\n").getBytes());
		out.flush();
		out.close();
	}

}
