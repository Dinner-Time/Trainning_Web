package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("secondServlet!");
		
		PrintWriter out = resp.getWriter();
		String html = "<html>"
					+ "<head>"
					+ "<title>Second Servlet</title>"
					+ "</head>"
					+ "<body>"
					+ "<h1>Hello Servlet</h1>"
					+ "</body>"
					+ "</html>";
		out.print(html);
		out.close();
	}
	
	
}
