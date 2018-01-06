package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bean.InfoBean;

public class InfoDao {



	public boolean addInfo(InfoBean info){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("insert into t_info(sid,name,sex,dept,major,clazz) values(?,?,?,?,?,?)");
			prepStmt.setString(1, info.getSid());
			prepStmt.setString(2, info.getName());
			prepStmt.setString(3, info.getSex());
			prepStmt.setString(4, info.getDept());
			prepStmt.setString(5, info.getMajor());
			prepStmt.setString(6, info.getClazz());
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
	public boolean deleteInfo(String sid){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("delete from t_info where sid=?");
			prepStmt.setString(1, sid);
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

	public boolean updateInfo(InfoBean info){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("update t_info set name=?,sex=?,dept=?,major=?,clazz=? where sid=?");
			prepStmt.setString(1, info.getName());
			prepStmt.setString(2, info.getSex());
			prepStmt.setString(3, info.getDept());
			prepStmt.setString(4, info.getMajor());
			prepStmt.setString(5, info.getClazz());
			prepStmt.setString(6, info.getSid());
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


	public ArrayList<InfoBean> getInfoList(){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		ArrayList<InfoBean> infoList = new ArrayList<InfoBean>();
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("select * from t_info");
			rs = prepStmt.executeQuery();
			while(rs.next()){
				String sid = rs.getString(1);
				String name = rs.getString(2);
				String sex = rs.getString(3);
				String dept = rs.getString(4);
				String major = rs.getString(5);
				String clazz = rs.getString(6);
				infoList.add(new InfoBean(sid, name, sex, dept, major, clazz));
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
		System.out.print(new InfoDao().addInfo(new InfoBean("201430110113", "小明", "男", "中兴通信工程", "通信工程", "3班")));
		System.out.print(new InfoDao().updateInfo(new InfoBean("201430110119", "小均", "男", "中兴通信工程", "通信工程", "3班")));
		System.out.print(new InfoDao().deleteInfo("201430110119"));
	}

}
