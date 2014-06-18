package com.adapt.apptrack;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Employee emp=new Employee();
	
	
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
		//Employee emp = new Employee();
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try{
			String name = request.getParameter("userName");
		String passKey = request.getParameter("Password");
		int testId =validateUser(name,passKey);
		if(testId >0){
			System.out.println("Working Good Till now");
			try {
				
				connect employeeConnection = new connect();
				employeeConnection.doConnection();
				Connection con = employeeConnection.getConnect();
				String query = "select * from employeee where empId = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1,testId);
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
				emp.setEmpId(rs.getInt(1));
				emp.setFirstName(rs.getString(2));
				emp.setLastName(rs.getString(3));
				emp.setDateJoin(rs.getDate(4));
				emp.setPost(rs.getString(5));
				emp.setManagerID(rs.getInt(6));
				emp.updateTable();
				emp.loadDataEmployee();}
				} 
				catch (SQLException e) {
				System.out.println("Errrroosdadjabsd");
				e.printStackTrace();
			}
			Gson gson = new Gson();
			json = gson.toJson(emp);
			System.out.println(json);
			Cookie empId = new Cookie("empid",Integer.toString(emp.getEmpId()));//Adding a Cookie for the interface
			empId.setMaxAge(-1);
			response.addCookie(empId);
			request.setAttribute("json", json);
			//request.setAttribute("employee", emp);
			RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
			System.out.println("forwardeer");
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
		System.out.println("Get Request");
		String id = request.getParameter("id");
		String appId = request.getParameter("q");
		int appraisalId = Integer.parseInt(appId); 
		if(id.equals("details"))
		{
		PrintWriter out = response.getWriter();
		String str = "{\"rows\" : ["+json+"]}";
		System.out.println(str);
		out.write(str);
		}
		else if(id.equals("pending")&&(appraisalId>0))
		{
			Iterator<Appraisal> it = emp.pendingAppraisal.iterator();
			Appraisal temp=null;
			while(it.hasNext())
			{
				temp = it.next();
				if(temp.getAppraisalId()==appraisalId)
					break;
			}
			Gson gson = new Gson();
			String str = gson.toJson(temp.getListOfGoals());
			PrintWriter out = response.getWriter();
			System.out.println(str);
			out.write(str);
		}
		else if(id.equals("pending"))
		{
			System.out.println("In Pending");
			JqGridData<Appraisal> pendingAppraisal = new JqGridData<>(1, 1, emp.pendingAppraisal.size(), emp.pendingAppraisal);
			Gson gson = new Gson();
			String str = gson.toJson(pendingAppraisal);
			System.out.println(str);
			PrintWriter out = response.getWriter();
			out.write(str);
		}
		else if(id.equals("completed"))
		{
			System.out.println("In Completed");
			JqGridData<Appraisal> completedAppraisal = new JqGridData<>(1, 1, emp.completedAppraisal.size(), emp.completedAppraisal);
			Gson gson = new Gson();
			String str = gson.toJson(completedAppraisal);
			System.out.println(str);
			PrintWriter out = response.getWriter();
			out.write(str);
		}
		}
	/*
	 * validateUser validates user on creating connection with a Database Specified
	 * in the "connect" class
	 */
	protected int validateUser(String userName,String passKey)
	{	
		connect userAuthenticationConnection = new connect();
		boolean test = userAuthenticationConnection.doConnection();
		ResultSet rs = null;
		if(test == true)
		{
			Connection con = userAuthenticationConnection.getConnect();
			PreparedStatement stmt= null;
			String sql= "select * from securityInfo where eMail = ?";
			try {
				
				stmt = con.prepareStatement(sql);
				stmt.setString(1,userName);
				rs = stmt.executeQuery();
				rs2 = rs;
				if(!rs.first())
				{
					return 0;
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
				int id = 0;
				try {
					id = rs.getInt("empId");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				userAuthenticationConnection.closeConnection();
				userAuthenticationConnection = null;
				return id;
			}
		}
		return 0;
		}
}
