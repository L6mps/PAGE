package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "CounterServlet", urlPatterns = {"/counter"})
public class CounterServlet extends HttpServlet{

	//This servlet keeps count of active sessions and expires after one minute - if it has not been refreshed, we can 
	// assume that the session is over and remove it from the activeSession list. If the session is already activated (example : multiple tabs on same browser)
	// it will not be added or deleted - the servlet will only transmit information.
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		SessionCounter.registerSessionServlet(request.getSession(), this);
		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		for(int i=0; i<30;i++){
			System.out.println("Sessions active:" + SessionCounter.getActiveSessionNumber());
			writer.write("data: " + SessionCounter.getActiveSessionNumber() + "\n\n");
			writer.flush();
			try{
				Thread.sleep(2000);
			} catch (Exception e){
				break;
			}
		}	
		SessionCounter.unregisterSessionServlet(request.getSession(), this);
		writer.close();
	}

}
