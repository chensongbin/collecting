package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javabean.User;
import tool.DbUtil;

public class UserDao {
	static public boolean isUserNameExist(User user) {
		Connection conn = DbUtil.getConn();
		String sql = "select * from user where userName=?";
		PreparedStatement ps;
		boolean flag = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	static public boolean isExist(User user) {
		Connection conn = DbUtil.getConn();
		String sql = "select * from user where userName=? and userPassword=?";
		PreparedStatement ps;
		boolean flag = false;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) flag = true;
			user.setUserId(rs.getInt(1));
			user.setUserEmail(rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	static public boolean addUser(User user) {
		Connection conn = DbUtil.getConn();
		String sql = "insert into user(userName, userPassword, userEmail) value(?, ?, ?)";
		PreparedStatement ps;
		int flag = 0;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserPassword());
			ps.setString(3, user.getUserEmail());
			flag = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==0) {
			return false;
		}else {
			return true;
		}
	}
}
