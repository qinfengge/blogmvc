package com.lza.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lza.dao.CommentDao;
import com.lza.util.Database;
import com.lza.vo.Comment;
import com.lza.vo.User;

public class CommentDaoImp implements CommentDao {

	Database db =new Database();
	Connection con = db.getcon();
	ResultSet rs=null;
	@Override
	public List<Comment> selAllComment() throws SQLException {
		String sql = "select * from comment";
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		PreparedStatement ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			Comment c = new Comment();
			c.setArticleId(rs.getInt("commentId"));
			c.setComment(rs.getString("comment"));
			c.setCommentDate(rs.getDate("commentDate"));
			c.setUserId(rs.getInt("userId"));
			c.setArticleId(rs.getInt("articleId"));
			commentList.add(c);
		}
		return commentList;
	}

	@Override
	public int addComment(Comment c) throws SQLException {
		int i=0;
		String sql="insert into comment values (?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, c.getCommentId());
			ps.setString(2, c.getComment());
			ps.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
			ps.setInt(4, c.getUserId());
			ps.setInt(5,c.getArticleId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int updComment(Comment c) throws SQLException {
		int i=0;
		String sql="update comment set comment = ?,userId = ?,articleId = ? where commentId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, c.getComment());
			ps.setInt(2, c.getUserId());
			ps.setInt(3, c.getArticleId());
			ps.setInt(4, c.getCommentId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delComment(Comment c) throws SQLException {
		int i=0;
		String sql="delete from comment where commentId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, c.getCommentId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Comment> selByCommentName(String CommentName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delCommentByUserId(int userId) throws SQLException {
		int i=0;
		String sql="delete from comment where userId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, userId);
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delCommentByArticleId(int articleId) throws SQLException {
		int i=0;
		String sql="delete from comment where articleId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, articleId);
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Comment> selByArt(int articleId) throws SQLException {
		String sql = "select * from comment where articleId = ?";
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, articleId);
		rs=ps.executeQuery();
		while(rs.next()){
			Comment c = new Comment();
			c.setArticleId(rs.getInt("commentId"));
			c.setComment(rs.getString("comment"));
			c.setCommentDate(rs.getDate("commentDate"));
			c.setUserId(rs.getInt("userId"));
			c.setArticleId(rs.getInt("articleId"));
			commentList.add(c);
		}
		return commentList;
	}

}
