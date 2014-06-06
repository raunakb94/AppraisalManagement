package com.adapt.apptrack;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 * Handles The ReGistration TModule On The main login page!!!!
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public register() {
        super();
    }

	/**
	 * Post Method Handles the Request
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			Employee empTemp = new Employee();
			empTemp.setFirstName(request.getParameter("firstName"));
			
		}
		finally{
			Security secureObject = new Security();
		}
				
		
	}

}
