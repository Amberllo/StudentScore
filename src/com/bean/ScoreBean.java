package com.bean;

public class ScoreBean {
	private String sid;//ѧ��
	private String course;//�γ�
	private String score;//�ɼ�
	private String time;//ʱ��

	public ScoreBean(String sid, String course, String score, String time) {
		super();
		this.sid = sid;
		this.course = course;
		this.score = score;
		this.time = time;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String id) {
		this.sid = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
