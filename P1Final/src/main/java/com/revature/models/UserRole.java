package com.revature.models;

public class UserRole {
	
	private int userRoleId;
	private String user_role;
	
	
	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserRole(int userRoleId, String user_role) {
		super();
		this.userRoleId = userRoleId;
		this.user_role = user_role;
	}
	
	
	public int getuserRoleId() {
		return userRoleId;
	}
	
	
	public void setuserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	
	public String getuser_role() {
		return user_role;
	}
	
	
	public void setuser_role(String user_role) {
		this.user_role = user_role;
	}

}
