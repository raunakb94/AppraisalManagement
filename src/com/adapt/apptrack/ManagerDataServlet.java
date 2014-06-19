package com.adapt.apptrack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

/**
 * Servlet implementation class ManagerDataServlet
 */
@WebServlet("/ManagerDataServlet")
public class ManagerDataServlet extends HttpServlet {
       
    /**
	 * 
	 */
	private static final long serialVersionUID = -1735059934913062361L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ManagerDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String criteria = request.getParameter("criteria");
		int empid = Integer.parseInt(request.getParameter("empid"));
		if(criteria.equals("details"))
		{
			String query = "Select * from employeee where managerid = ?";
			connect managerConnection = new connect();
			managerConnection.doConnection();
			Connection con = managerConnection.getConnect();
			try {
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, empid);
				ResultSet rs = stmt.executeQuery();
				String json = convert(rs);
				System.out.println("In managerial Data");
				System.out.println(json);
				PrintWriter out = response.getWriter();
				String str = "{\"rows\" : "+json+"}";
				System.out.println(str);
				managerConnection.closeConnection();
				out.write(str);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(criteria.equals("pending")){
			String query = "select distinct empid,appid from transact where empid in(select empid from employeee where managerid= ?) and lastupdated='emp'";
			connect managerConnection = new connect();
			managerConnection.doConnection();
			Connection con = managerConnection.getConnect();
			try {
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, empid);
				ResultSet rs = stmt.executeQuery();
				String json = convert(rs);
				System.out.println("In managerial Pending Data");
				System.out.println(json);
				PrintWriter out = response.getWriter();
				String str = "{\"rows\" : "+json+"}";
				System.out.println(str);
				managerConnection.closeConnection();
				out.write(str);
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	//Function to convert any resultset to corresponding jsonstring
	
	public static String convert( ResultSet rs )
		    throws SQLException
		  {
		    JSONArray json = new JSONArray();
		    ResultSetMetaData rsmd = rs.getMetaData();

		    while(rs.next()) {
		      int numColumns = rsmd.getColumnCount();
		      JSONObject obj = new JSONObject();

		      for (int i=1; i<numColumns+1; i++) {
		        String column_name = rsmd.getColumnName(i);

		        if(rsmd.getColumnType(i)==java.sql.Types.ARRAY){
		         obj.put(column_name, rs.getArray(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BIGINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BOOLEAN){
		         obj.put(column_name, rs.getBoolean(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.BLOB){
		         obj.put(column_name, rs.getBlob(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.DOUBLE){
		         obj.put(column_name, rs.getDouble(column_name)); 
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.FLOAT){
		         obj.put(column_name, rs.getFloat(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.INTEGER){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.NVARCHAR){
		         obj.put(column_name, rs.getNString(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.VARCHAR){
		         obj.put(column_name, rs.getString(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.TINYINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.SMALLINT){
		         obj.put(column_name, rs.getInt(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.DATE){
		         obj.put(column_name, rs.getDate(column_name));
		        }
		        else if(rsmd.getColumnType(i)==java.sql.Types.TIMESTAMP){
		        obj.put(column_name, rs.getTimestamp(column_name));   
		        }
		        else{
		         obj.put(column_name, rs.getObject(column_name));
		        }
		      }

		      json.add(obj);
		    }

		    Gson gson = new Gson();
		    String str = gson.toJson(json);
		    return str;
		  }
}
