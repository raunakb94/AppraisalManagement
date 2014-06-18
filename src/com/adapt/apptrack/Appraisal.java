package com.adapt.apptrack;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Appraisal {
	private int appraisalId;
	private java.sql.Date startDate;
	private int score;
	private List<Goal> listOfGoals = new ArrayList<Goal>();
	
	
	Appraisal()
	{
		score = 0;
	}
	
	public int getAppraisalId() {
		return appraisalId;
	}
	public void setAppraisalId(int appraisalId) {
		this.appraisalId = appraisalId;
	}
	public java.sql.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	public List<Goal> getListOfGoals() {
		return listOfGoals;
	}
	public void setListOfGoals(List<Goal> listOfGoals) {
		this.listOfGoals = listOfGoals;
	}
	
	public int getScore() {
		return score;
	}	
	
	protected void loadDataAppraisal(String post)
	{
		listOfGoals.clear();
		System.out.println("load appraisal");
		String query = "Select * from emp_goals where postCriteria = ? order by category";
		connect appraisalConnection = new connect();
		appraisalConnection.doConnection();
		
		try {
			Connection con = appraisalConnection.getConnect();
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1,post);
			System.out.println(stmt.toString()+"Stsms");
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Goal temp = new Goal();
				temp.setGoalId(rs.getInt(1));
				System.out.println(temp.getGoalId());
				temp.setType(rs.getString(2));
				System.out.println(temp.getType());
				temp.setForPost(rs.getString(3));
				System.out.println(temp.getForPost());
				temp.setDescription(rs.getString(4));
				System.out.println(temp.getDescription());
				//temp.loadDataGoal()
				this.listOfGoals.add(temp);
				System.out.println("Added");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	protected void loadScore(int empId)
	{
		String query = "Select score from transact where empid = ? and appid = ?";
		connect loadScoreConnection = new connect();
		loadScoreConnection.doConnection();
		Connection con = loadScoreConnection.getConnect();
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, empId);
			stmt.setInt(2, this.appraisalId);
			ResultSet rs = stmt.executeQuery();
			int tempScore = 0;
			while(rs.next())
			{
				int score = rs.getInt("score");
				tempScore +=score;
			}
			this.score = tempScore;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
