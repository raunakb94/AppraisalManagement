package com.adapt.apptrack;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;
/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ResultSet rs2 = null ;
	String json = "";
    public Login() {
        super();
    }

	/**
	 * Servlet Init Configuration
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * Get Post Handles Username and Password and Calls a Validate Function
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Post Request");	
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			String name = request.getParameter("userName");
		String passKey = request.getParameter("Password");
		boolean test =validateUser(name,passKey);
		if(test == true){
			System.out.println("Working Good Till now");
			//Employee emp = new Employee();
			//emp.initialiseObject(json);
			//request.setAttribute("json",json);
			Employee emp = new Employee();
			try {
				emp.setFirstName(rs2.getString("firstName"));
				emp.setLastName(rs2.getString("lastName"));
				emp.seteMail(rs2.getString("eMail"));
				emp.setEmpId(Integer.parseInt(rs2.getString("empId")));
				Gson gson = new Gson();
				json = gson.toJson(emp);
				System.out.println(json);
			} catch (SQLException e) {
				System.out.println("Errrroosdadjabsd");
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");//Redirects To Home.jsp
			rd.forward(request, response);
		}
		else{
			out.println("Login Failure Try Again");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}
		}
		finally {
			out.close();
		}
	}
	
	/**
	 * Get Post Handles Username and Password and Calls a Validate Function
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hello");
		PrintWriter out = response.getWriter();
		json = "{\"rows\" : ["+json+"]}";
		System.out.println(json);
		out.write(json);
		//RequestDispatcher rd = request.getRequestDispatcher("home.jsp");//Redirects To Home.jsp
		//rd.forward(request, response);
		}
	/*
	 * validateUser validates user on creating connection with a Database Specified
	 * in the "connect" class
	 */
	protected boolean validateUser(String userName,String passKey)
	{
		connect userAuthenticationConnection = new connect();
		boolean test = userAuthenticationConnection.doConnection();
		ResultSet rs = null;
		if(test == true)
		{
			Connection con = userAuthenticationConnection.getConnect();
			PreparedStatement stmt= null;
			String sql= "select * from personal_details where eMail = ?";
			try {
				
				stmt = con.prepareStatement(sql);
				stmt.setString(1,userName);
				rs = stmt.executeQuery();
				rs2 = rs;
				if(!rs.first())
				{
					return false;
				}
			}
			
			catch (SQLException e1) {
				e1.printStackTrace();
			}			
			String passWord=null;
			try {
					
					passWord = rs.getString("password");				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(passWord.equals(passKey))
			{
				
				//AuthenticationConnection.closeConnection();
				return true;
			}
		}
		return false;
		}
}
