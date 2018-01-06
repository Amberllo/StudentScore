package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.bean.ScoreBean;

public class ScoreDao {


	public boolean addSocre(ScoreBean score){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("insert into t_score(sid,course,score,time) values(?,?,?,?)");
			prepStmt.setString(1, score.getSid());
			prepStmt.setString(2, score.getCourse());
			prepStmt.setString(3, score.getScore());
			prepStmt.setString(4, score.getTime());
			int flag = prepStmt.executeUpdate();
			if (flag > 0) {
				return true;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return false;
	}
	public boolean deleteScore(ScoreBean score){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("delete from t_score where (sid=? and course=? and time=?)");
			prepStmt.setString(1, score.getSid());
			prepStmt.setString(2, score.getCourse());
			prepStmt.setString(3, score.getTime());
			int flag = prepStmt.executeUpdate();
			if (flag > 0) {
				return true;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return false;
	}

	public boolean updateScore(ScoreBean score){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("update t_score set course=?,score=?,time=? where sid=?");
			prepStmt.setString(1, score.getCourse());
			prepStmt.setString(2, score.getScore());
			prepStmt.setString(3, score.getTime());
			prepStmt.setString(4, score.getSid());
			int flag = prepStmt.executeUpdate();
			if (flag > 0) {
				return true;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return false;
	}
	public ArrayList<ScoreBean> getScoreList(){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<ScoreBean> infoList = new ArrayList<ScoreBean>();
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("select * from t_score");
			rs = prepStmt.executeQuery();
			while(rs.next()){
				String sid = rs.getString(1);
				String course = rs.getString(2);
				String score = rs.getString(3);
				String time = rs.getString(4);
				infoList.add(new ScoreBean(sid, course, score, time) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return infoList;
	}

	public ArrayList<ScoreBean> getScoreListBySid(String sid){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<ScoreBean> infoList = new ArrayList<ScoreBean>();
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("select * from t_score where sid=?");
			prepStmt.setString(1, sid);
			rs = prepStmt.executeQuery();
			while(rs.next()){
				//String sid = rs.getString(1);
				String course = rs.getString(2);
				String score = rs.getString(3);
				String time = rs.getString(4);
				infoList.add(new ScoreBean(sid, course, score, time) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return infoList;
	}
	//根据课程名查找
	public ArrayList<ScoreBean> getScoreListByCourse(String course){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<ScoreBean> infoList = new ArrayList<ScoreBean>();
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("select * from t_score where course=?");
			prepStmt.setString(1, course);
			rs = prepStmt.executeQuery();
			while(rs.next()){
				String sid = rs.getString(1);
				//String course = rs.getString(2);
				String score = rs.getString(3);
				String time = rs.getString(4);
				infoList.add(new ScoreBean(sid, course, score, time) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return infoList;
	}


	public ArrayList<ScoreBean> getScoreListBySidAndCourse(String sid, String course){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<ScoreBean> infoList = new ArrayList<ScoreBean>();
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("select * from t_score where (sid=? and course=?)");
			prepStmt.setString(1, sid);
			prepStmt.setString(2, course);
			rs = prepStmt.executeQuery();
			while(rs.next()){
				//String sid = rs.getString(1);
				//String course = rs.getString(2);
				String score = rs.getString(3);
				String time = rs.getString(4);
				infoList.add(new ScoreBean(sid, course, score, time) );
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return infoList;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
