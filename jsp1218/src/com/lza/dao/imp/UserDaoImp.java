package com.lza.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lza.dao.UserDao;
import com.lza.util.Database;
import com.lza.vo.User;

public class UserDaoImp implements UserDao {

	Database db =new Database();
	Connection con = db.getcon();
	ResultSet rs=null;
	@Override
	public List<User> selAllUser() throws SQLException {
		String sql = "select * from user";
		ArrayList<User> userList = new ArrayList<User>();
		PreparedStatement ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setUserName(rs.getString("userName"));
			u.setPasswd(rs.getString("passwd"));
			u.setIsAdmin(rs.getInt("isAdmin"));
			userList.add(u);
		}
		return userList;
	}

	@Override
	public int addUser(User u) throws SQLException {
		int i=0;
		String sql="insert into user values (?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getPasswd());
			ps.setInt(3, u.getIsAdmin());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int updUser(User u) throws SQLException {
		int i=0;
		String sql="update user set userName = ?,passwd = ?,isAdmin = ? where userId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, u.getUserName());
			ps.setString(2, u.getPasswd());
			ps.setInt(3, u.getIsAdmin());
			ps.setInt(4, u.getUserId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delUser(User u) throws SQLException {
		int i=0;
		String sql="delete from user where userId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delUserAllComments(int commentId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User userLogin(String userName, String passwd) throws SQLException {
		String sql = "select * from user where userName = ? and passwd = ?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userName);
		ps.setString(2, passwd);
		rs=ps.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setUserName(rs.getString("userName"));
			u.setPasswd(rs.getString("passwd"));
			u.setIsAdmin(rs.getInt("isAdmin"));
			return u;
		}
		return null;
	}

	@Override
	public int userRegiister(User u) throws SQLException {
		int i=0;
		String sql="insert into user values (?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, u.getUserId());
			ps.setString(2, u.getUserName());
			ps.setString(3, u.getPasswd());
			ps.setInt(4, u.getIsAdmin());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public User userRg(String userName) throws SQLException {
		String sql = "select * from user where userName = ?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userName);
		rs=ps.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setUserName(rs.getString("userName"));
			u.setPasswd(rs.getString("passwd"));
			u.setIsAdmin(rs.getInt("isAdmin"));
			return u;
		}
		return null;
	}

	@Override
	public List<User> selUserName(int userId) throws SQLException {
		String sql = "select * from user where userId = ?";
		ArrayList<User> userList = new ArrayList<User>();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, userId);
		rs=ps.executeQuery();
		while(rs.next()){
			User u = new User();
			u.setUserId(rs.getInt("userId"));
			u.setUserName(rs.getString("userName"));
			u.setPasswd(rs.getString("passwd"));
			u.setIsAdmin(rs.getInt("isAdmin"));
			userList.add(u);
		}
		return userList;
	}

	


}
