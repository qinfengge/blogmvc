package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.Comment;

public interface CommentDao {
	public List<Comment> selAllComment() throws SQLException;
	public int addComment(Comment c) throws SQLException;
	public int updComment(Comment c) throws SQLException;
	public int delComment(Comment c) throws SQLException;
	//根据评论名查找
	public List<Comment> selByCommentName(String CommentName) throws SQLException;
	//根据用户删除评论
	public int delCommentByUserId(int userId) throws SQLException;
	//根据文章删除评论
	public int delCommentByArticleId(int articleId) throws SQLException;
	//根据文章查询评论
	public List<Comment> selByArt(int articleId) throws SQLException;
}
