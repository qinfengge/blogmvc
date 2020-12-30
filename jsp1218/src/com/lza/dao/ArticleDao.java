package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.Article;

public interface ArticleDao {
	//��
	public List<Article> selAllarticle() throws SQLException;
	
	public int addArticle(Article a) throws SQLException;
	
	public int updArticle(Article a) throws SQLException;
	
	public int delArticle(Article a) throws SQLException;
	//���ݱ�ǩɾ����
	public int delBytagId(int tagid) throws SQLException;
	//������������ѯ����
	public List<Article> selByArticleName(String ArticleName) throws SQLException;
	//����ID��ѯ����
	public List<Article> selByArtId(int articleId) throws SQLException;
	//����ID��ѯ����2
	public Article selByArtIdaa(int articleId) throws SQLException;
}
