package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CounterServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		while(SessionCounter.sessionExists(request.getSession())){
			writer.write("data: " + SessionCounter.getActiveSessionNumber() + "\n\n");
			try{
				Thread.sleep(1000);
			} catch (Exception e){
				e.printStackTrace();
				break;
			}
		}
		writer.close();
	}
}
