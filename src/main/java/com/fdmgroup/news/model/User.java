package com.fdmgroup.news.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "users")
public class User {
	
	@Id
	private int id;
	private String username;
	private String password;
	private String name;
	
	public User() {
		super();
	}

	public User(String username, String password, String name) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return name;
	}

	public void setFullName(String fullName) {
		this.name = fullName;
	}

	public int getId() {
		return id;
	}

	
	


	
	
	
	
	

}
