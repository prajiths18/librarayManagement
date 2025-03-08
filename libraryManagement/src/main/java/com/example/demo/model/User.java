package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
   
	@Id
	String username;
	
	public User() {};
	
	
	String password; 
	String Role;

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
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		Role = role;
	}
	
}
