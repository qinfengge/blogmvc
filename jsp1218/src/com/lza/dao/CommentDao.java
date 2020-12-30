package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.Comment;

public interface CommentDao {
	public List<Comment> selAllComment() throws SQLException;
	public int addComment(Comment c) throws SQLException;
	public int updComment(Comment c) throws SQLException;
	public int delComment(Comment c) throws SQLException;
	//��������������
	public List<Comment> selByCommentName(String CommentName) throws SQLException;
	//�����û�ɾ������
	public int delCommentByUserId(int userId) throws SQLException;
	//��������ɾ������
	public int delCommentByArticleId(int articleId) throws SQLException;
	//�������²�ѯ����
	public List<Comment> selByArt(int articleId) throws SQLException;
}
