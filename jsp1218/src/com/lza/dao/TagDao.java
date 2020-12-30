package com.lza.dao;

import java.sql.SQLException;
import java.util.List;

import com.lza.vo.Tag;

public interface TagDao {
	public List<Tag> selAllTags() throws SQLException;
	public int addTag(Tag t) throws SQLException;
	public int updTag(Tag t) throws SQLException;
	public int delTag(Tag t) throws SQLException;
	
	public List<Tag> selByTagName(String tagName) throws SQLException;
	//根据tagId查询标签
	public List<Tag> selByTagId(int tagId) throws SQLException;
}
