package com.bean;

public class UserBean {
	private String username;//�û�����ѧ��Ϊѧ��
	private String password;//����
	private int popedom;//Ȩ�ޣ�0Ϊѧ����1Ϊ����Ա

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
