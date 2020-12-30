package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.Article;

public interface ArticleDao {
	//查
	public List<Article> selAllarticle() throws SQLException;
	
	public int addArticle(Article a) throws SQLException;
	
	public int updArticle(Article a) throws SQLException;
	
	public int delArticle(Article a) throws SQLException;
	//根据标签删文章
	public int delBytagId(int tagid) throws SQLException;
	//根据文章名查询文章
	public List<Article> selByArticleName(String ArticleName) throws SQLException;
	//根据ID查询文章
	public List<Article> selByArtId(int articleId) throws SQLException;
	//根据ID查询文章2
	public Article selByArtIdaa(int articleId) throws SQLException;
}
