package com.example.streamProj.web.model;

public class User {
	private String userName;
	private Integer userId;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, Integer userId) {
		super();
		this.userName = userName;
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
