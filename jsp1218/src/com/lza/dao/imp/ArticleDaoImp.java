package com.lza.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lza.util.Database;
import com.lza.dao.ArticleDao;
import com.lza.vo.Article;

public class ArticleDaoImp implements ArticleDao {

	Database db =new Database();
	Connection con = db.getcon();
	ResultSet rs=null;
	@Override
	public List<Article> selAllarticle() throws SQLException {
		String sql="select * from article";
		ArrayList<Article> articleList = new ArrayList<Article>();
		PreparedStatement ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			Article a= new Article();
			a.setArticleId(rs.getInt("articleId"));
			a.setArticleName(rs.getString("articleName"));
			a.setImgSrc(rs.getString("imgSrc"));
			a.setarticleContent(rs.getString("articleContent"));
			a.setAuthor(rs.getString("author"));
			a.setTagId(rs.getInt("tagId"));
			a.setArticeDate(rs.getDate("articleDate"));
			articleList.add(a);
		}
		return articleList;
	}

	@Override
	public int addArticle(Article a) throws SQLException {
		int i=0;
		String sql="insert into article values (?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, a.getArticleId());
			ps.setString(2, a.getArticleName());
			ps.setString(3, a.getImgSrc());
			ps.setString(4, a.getarticleContent());
			ps.setInt(5, a.getTagId());
			ps.setString(6, a.getAuthor());
			ps.setTimestamp(7, new java.sql.Timestamp(System.currentTimeMillis()));
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int updArticle(Article a) throws SQLException {
		int i=0;
		String sql="update article set articleName=?,imgSrc=?,articleContent=?,tagId=?,author=? where articleId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, a.getArticleName());
			ps.setString(2, a.getImgSrc());
			ps.setString(3, a.getarticleContent());
			ps.setInt(4, a.getTagId());
			ps.setString(5, a.getAuthor());
			ps.setInt(6, a.getArticleId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delArticle(Article a) throws SQLException {
		int i=0;
		String sql="delete from article where articleId =?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, a.getArticleId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delBytagId(int tagid) throws SQLException {
		int i=0;
		String sql="delete from article where tagId =?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, tagid);
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Article> selByArticleName(String ArticleName) throws SQLException {
		String sql="select * from article where articleName like \"%\"?\"%\"";
		ArrayList<Article> articleList = new ArrayList<Article>();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, ArticleName);
		rs=ps.executeQuery();
		while(rs.next()){
			Article a= new Article();
			a.setArticleId(rs.getInt("articleId"));
			a.setArticleName(rs.getString("articleName"));
			a.setImgSrc(rs.getString("imgSrc"));
			a.setarticleContent(rs.getString("articleContent"));
			a.setAuthor(rs.getString("author"));
			a.setTagId(rs.getInt("tagId"));
			a.setArticeDate(rs.getDate("articleDate"));
			articleList.add(a);
		}
		return articleList;
	}

	@Override
	public List<Article> selByArtId(int articleId) throws SQLException {
		String sql="select * from article where articleId = ?";
		ArrayList<Article> articleList = new ArrayList<Article>();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, articleId);
		rs=ps.executeQuery();
		while(rs.next()){
			Article a= new Article();
			a.setArticleId(rs.getInt("articleId"));
			a.setArticleName(rs.getString("articleName"));
			a.setImgSrc(rs.getString("imgSrc"));
			a.setarticleContent(rs.getString("articleContent"));
			a.setAuthor(rs.getString("author"));
			a.setTagId(rs.getInt("tagId"));
			a.setArticeDate(rs.getDate("articleDate"));
			articleList.add(a);
		}
		return articleList;
	}

	@Override
	public Article selByArtIdaa(int articleId) throws SQLException {
		String sql="select * from article where articleId = ?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, articleId);
		rs=ps.executeQuery();
		while(rs.next()){
			Article a= new Article();
			a.setArticleId(rs.getInt("articleId"));
			a.setArticleName(rs.getString("articleName"));
			a.setImgSrc(rs.getString("imgSrc"));
			a.setarticleContent(rs.getString("articleContent"));
			a.setAuthor(rs.getString("author"));
			a.setTagId(rs.getInt("tagId"));
			a.setArticeDate(rs.getDate("articleDate"));
			return a;
		}
		return null;
	}

}
