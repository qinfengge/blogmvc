package com.lza.vo;

import java.util.Date;

public class Article {
	private int articleId;
	private String articleName;
	private String imgSrc;
	private String articleContent;
	private int tagId;
	private String author;
	private Date articleDate;
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getArticleName() {
		return articleName;
	}
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public String getarticleContent() {
		return articleContent;
	}
	public void setarticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getArticeDate() {
		return articleDate;
	}
	public void setArticeDate(Date articeDate) {
		this.articleDate = articeDate;
	}
	
	
}
