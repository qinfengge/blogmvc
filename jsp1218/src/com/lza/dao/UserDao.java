package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.User;

public interface UserDao {
	public List<User> selAllUser() throws SQLException;
	public int addUser(User u) throws SQLException;
	public int updUser(User u) throws SQLException;
	public int delUser(User u) throws SQLException;
	//删除该用户下所有评论
	public int delUserAllComments(int commentId) throws SQLException;
	//登陆
	public User userLogin(String userName,String passwd) throws SQLException;
	//注册
	public int userRegiister(User u) throws SQLException;
	//判断用户名是否重复
	public User userRg(String userName) throws SQLException;
	//根据用户ID查询用户名
	public List<User> selUserName(int userId) throws SQLException;
}
