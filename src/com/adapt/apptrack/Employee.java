package com.adapt.apptrack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Employee {
	private int empId;
	private String firstName;
	private String lastName;
	private Date dateJoin;
	private String Post;
	private int managerID;
	List<Appraisal> pendingAppraisal = new ArrayList<Appraisal>();
	List<Appraisal> completedAppraisal= new ArrayList<Appraisal>();
	Employee()
	{
		setFirstName("");
		setLastName("");
		setEmpId(0);
		setManagerID(0);
		setPost("");
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	/**
	 * @return the managerID
	 */
	public int getManagerID() {
		return managerID;
	}
	/**
	 * @param managerID the managerID to set
	 */
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	
	public String getPost() {
		return Post;
	}
	public void setPost(String post) {
		Post = post;
	}
	
	public Date getDateJoin() {
		return dateJoin;
	}
	public void setDateJoin(Date dateJoin) {
		this.dateJoin = dateJoin;
	}
	
	//Updates The table employeeappraisal to the list of new appraisals appraisal
	protected void updateTable()
	{
		System.out.println("updating");
		connect temp = new connect();
		if(temp.doConnection())
		{
		Connection con = temp.getConnect();
		String query2 = "select appId from appraisal where datediff(startDate,?)>180";
		String query = "select * from employeeappraisal where appid = ? and empId = ?";
		String query3 ="insert into employeeappraisal values(?,?,default)";
		try {
			PreparedStatement stmt = con.prepareStatement(query2);
			PreparedStatement stmt2= con.prepareStatement(query);
			PreparedStatement stmt3 = con.prepareStatement(query3);
			stmt.setDate(1, this.dateJoin);
			System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				stmt2.setInt(1,rs.getInt(1));
				stmt2.setInt(2,this.empId);
				System.out.println(stmt2.toString());
				ResultSet rs2 = stmt2.executeQuery();
				if(!rs2.next())
				{
					stmt3.setInt(2,rs.getInt(1));
					stmt3.setInt(1,this.getEmpId());
					System.out.println(stmt3.toString());
					stmt3.executeUpdate();
				}
				else
					System.out.println("Emplty");
			}
		}
		catch (SQLException e) {
			e.printStackTrace(); }
		}
	}
		
		//Loads The Data of Pending appraisal and Completed appraisal into The List of appraisal
		protected void loadDataEmployee()
		{
			System.out.println("loadEmployee");
			connect temp2 = new connect();
			temp2.doConnection();
			Connection con = temp2.getConnect();
			String query = "Select * from employeeappraisal natural join appraisal where empId = ? ";
			try {
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, this.getEmpId());
				System.out.println(stmt.toString());
				ResultSet rs = stmt.executeQuery();
				while(rs.next())
				{
					Appraisal temp = new Appraisal();
					temp.setAppraisalId(rs.getInt("appId"));
					temp.setStartDate(rs.getDate("startDate"));
					temp.loadDataAppraisal(this.getPost());
					if(temp.getScore()>0)
					{
						this.completedAppraisal.add(temp);
						System.out.println("Completed Appraisal");
					}
					else
						{this.pendingAppraisal.add(temp);
						System.out.println("Pending appraisal");
						}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
}
