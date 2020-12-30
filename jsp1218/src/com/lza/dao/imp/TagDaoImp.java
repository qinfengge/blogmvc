package com.lza.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lza.dao.TagDao;
import com.lza.util.Database;
import com.lza.vo.Article;
import com.lza.vo.Tag;

public class TagDaoImp implements TagDao {
	
	Database db =new Database();
	Connection con = db.getcon();
	ResultSet rs=null;
	@Override
	public List<Tag> selAllTags() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from tag";
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		PreparedStatement ps=con.prepareStatement(sql);
		rs=ps.executeQuery();
		while(rs.next()){
			Tag t = new Tag();
			t.setTagId(rs.getInt("tagId"));
			t.setTagName(rs.getString("tagName"));
			tagList.add(t);
		}
		return tagList;
	}

	@Override
	public int addTag(Tag t) throws SQLException {
		int i=0;
		String sql="insert into tag values (?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, t.getTagId());
			ps.setString(2, t.getTagName());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int updTag(Tag t) throws SQLException {
		int i=0;
		String sql="update tag set tagName = ? where tagId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, t.getTagName());
			ps.setInt(2, t.getTagId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public int delTag(Tag t) throws SQLException {
		int i=0;
		String sql="delete from tag where tagId = ?";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, t.getTagId());
			i=ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return i;
	}

	@Override
	public List<Tag> selByTagName(String tagName) throws SQLException {
		String sql = "select * from tag  where tagName like '%?%'";
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, tagName);
		rs=ps.executeQuery();
		while(rs.next()){
			Tag t = new Tag();
			t.setTagId(rs.getInt("tagId"));
			t.setTagName(rs.getString("tagName"));
			tagList.add(t);
		}
		return tagList;
	}

	@Override
	public List<Tag> selByTagId(int tagId) throws SQLException {
		String sql = "select * from tag  where tagId = ?";
		ArrayList<Tag> tagList = new ArrayList<Tag>();
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, tagId);
		rs=ps.executeQuery();
		while(rs.next()){
			Tag t = new Tag();
			t.setTagId(rs.getInt("tagId"));
			t.setTagName(rs.getString("tagName"));
			tagList.add(t);
		}
		return tagList;
	}

}
