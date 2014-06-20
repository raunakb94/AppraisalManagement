package com.adapt.apptrack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class AppraisalSubmit
 */
@WebServlet("/AppraisalSubmit")
public class AppraisalSubmit extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppraisalSubmit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();        
        out.print("Hello");
        //Getting appid and empid from the cookie sessions
        int appId = 0;
        int empId =0;
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if( cookies != null ){
            for (int i = 0; i < cookies.length; i++){
               cookie = cookies[i];
               if("appid".equals(cookie.getName()))
            	   appId = Integer.parseInt(cookie.getValue());
               if("empid".equals(cookie.getName()))
            	   empId = Integer.parseInt(cookie.getValue());
               
            }
        }else{
            out.println("<h2>No cookies founds</h2>");
        }
        
        //Updating the transact table
        String query = "insert into transact values(?,?,?,?,default,0,'emp');";
        PreparedStatement stmt=null;
        connect transaction = new connect();
        transaction.doConnection();
        Connection con = transaction.getConnect();        
        Enumeration en = request.getParameterNames();
        try {
			stmt = con.prepareStatement(query);
			stmt.setInt(1,empId);
			stmt.setInt(2, appId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        while (en.hasMoreElements()) {
            
            String paramName = (String) en.nextElement();
            try {
				stmt.setInt(3, Integer.parseInt(paramName));
				stmt.setString(4, request.getParameter(paramName));
				stmt.executeUpdate();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
            
            out.println("Successsssss");
            
        }
        transaction.closeConnection();
	}

}
