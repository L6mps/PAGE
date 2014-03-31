package com.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.servlet.VelocityServlet;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;

@SuppressWarnings("deprecation")
public class AuthenticateServlet extends VelocityViewServlet {
	public Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context context) {
		
		return null;

	}
}
