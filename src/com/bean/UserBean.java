package com.bean;

public class UserBean {
	private String username;//用户名，学生为学号
	private String password;//密码
	private int popedom;//权限，0为学生，1为管理员

	public UserBean(String username, String password, int popedom) {
		this.username = username;
		this.password = password;
		this.popedom = popedom;
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

	public int getPopedom() {
		return popedom;
	}

	public void setPopedom(int popedom) {
		this.popedom = popedom;
	}

}
