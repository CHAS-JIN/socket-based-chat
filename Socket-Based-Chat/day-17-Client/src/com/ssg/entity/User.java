package com.ssg.entity;

import java.io.Serializable;

public class User implements Serializable{

	private String username;
	private String password;
	private String nikename;
	private String sex;
	private String phone;
	
	public User(){}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public User(String username, String password, String nikename, String sex,
			String phone) {
		super();
		this.username = username;
		this.password = password;
		this.nikename = nikename;
		this.sex = sex;
		this.phone = phone;
	}
	public String getNikename() {
		return nikename;
	}
	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	
}
