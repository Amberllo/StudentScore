package com.bean;

public class InfoBean {
	private String sid;//ѧ��
	private String name;//����
	private String sex;//�Ա�
	private String dept;//ϵ��
	private String major;//רҵ
	private String clazz;//�༶
	
	
	public InfoBean(String sid, String name, String sex, String dept, String major, String clazz) {
		this.sid = sid;
		this.name = name;
		this.sex = sex;
		this.dept = dept;
		this.major = major;
		this.clazz = clazz;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
}
