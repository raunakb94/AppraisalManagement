package com.adapt.apptrack;

import com.google.gson.Gson;

public class Employee {
	
	private String firstName;
	private String lastName;
	private String eMail;
	private long empId;
	private int managerID;
	private int rating;
	
	Employee()
	{
		setFirstName("");
		setLastName("");
		seteMail("");
		setEmpId(0);
		setRating(0);
		setManagerID(0);
	}
	/*
	 * Initialise an object based on a Json String
	 */
	Employee initialiseObject(String json)
	{
		System.out.println("Wooooooo");
		Gson gson = new Gson();
		Employee temp;
		temp = gson.fromJson(json, Employee.class);
		return temp;
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
	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}
	/**
	 * @param eMail the eMail to set
	 */
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	/**
	 * @return the empId
	 */
	public long getEmpId() {
		return empId;
	}
	/**
	 * @param empId the empId to set
	 */
	public void setEmpId(long empId) {
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
	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}
	/**
	 * @param rating the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
