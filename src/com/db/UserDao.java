package com.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.bean.UserBean;



public class UserDao {

	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public int login(String username, String password){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		int popedom = -1;//权限标志，-1表示没有该用户或系统出错，0代表学生，1代表管理员
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("select popedom from t_user where username=? and password=?");
			prepStmt.setString(1, username);
			prepStmt.setString(2, password);
			rs = prepStmt.executeQuery();
			if (rs.next()) {
				popedom = rs.getInt(1);//返回权限，0代表学生，1代表管理员
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return popedom;
	}

	public boolean addUser(UserBean user){
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBconnection();
			prepStmt = con.prepareStatement("insert into t_user(username,password,popedom) values(?,?,?)");
			prepStmt.setString(1, user.getUsername());
			prepStmt.setString(2, user.getPassword());
			prepStmt.setInt(3, user.getPopedom());
			int flag = prepStmt.executeUpdate();
			if (flag > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeDB(con, prepStmt, rs);
		}
		return false;
	}

	public static void main(String[] args) {
		//System.out.print(new UserDao().login("admin2017", "19931020")+"");
		System.out.print(new UserDao().addUser(new UserBean("201430110118", "19931020", 0))+"");
	}

}
