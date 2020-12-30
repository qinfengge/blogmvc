package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.User;

public interface UserDao {
	public List<User> selAllUser() throws SQLException;
	public int addUser(User u) throws SQLException;
	public int updUser(User u) throws SQLException;
	public int delUser(User u) throws SQLException;
	//ɾ�����û�����������
	public int delUserAllComments(int commentId) throws SQLException;
	//��½
	public User userLogin(String userName,String passwd) throws SQLException;
	//ע��
	public int userRegiister(User u) throws SQLException;
	//�ж��û����Ƿ��ظ�
	public User userRg(String userName) throws SQLException;
	//�����û�ID��ѯ�û���
	public List<User> selUserName(int userId) throws SQLException;
}
