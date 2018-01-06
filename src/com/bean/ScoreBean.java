package com.bean;

public class ScoreBean {
	private String sid;//学号
	private String course;//课程
	private String score;//成绩
	private String time;//时间

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
