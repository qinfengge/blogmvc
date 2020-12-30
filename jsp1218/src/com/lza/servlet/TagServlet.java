package com.lza.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lza.dao.imp.ArticleDaoImp;
import com.lza.dao.imp.TagDaoImp;
import com.lza.vo.Tag;

@WebServlet("/TagServlet")
public class TagServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String opr = request.getParameter("opr");
		TagDaoImp tdi = new TagDaoImp();
		//添加标签
		if("addtag".equals(opr)){
			String tagname = request.getParameter("tagname");
			try {
				Tag t = new Tag();
				t.setTagId(0);
				t.setTagName(tagname);
				int count = tdi.addTag(t);
				if(count>0) {
					request.getRequestDispatcher("succes.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//显示标签，返回到页面
		if("listtag".equals(opr)) {
			try {
				List<Tag> listtag = (List<Tag>)tdi.selAllTags();
				if(listtag!=null) {
					request.setAttribute("listtag", listtag);
					request.getRequestDispatcher("tag/listtag.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//删除标签，同样删除标签下文章
		if("deltag".equals(opr)) {
			try {
				String deltagid = request.getParameter("deltagid");
				Tag t = new Tag();
				t.setTagId(Integer.parseInt(deltagid));
				int c1 = tdi.delTag(t);
				ArticleDaoImp adi = new ArticleDaoImp();
				int c2 = adi.delBytagId(Integer.parseInt(deltagid));
				if(c1>0) {
					response.sendRedirect("tag/listtag.jsp");
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//修改标签
		if("uptag".equals(opr)) {
			try {
			String uptagid = request.getParameter("uptagid");
			String uptagname = request.getParameter("uptagname");
			Tag t = new Tag();
			t.setTagId(Integer.parseInt(uptagid));
			t.setTagName(uptagname);
			int count = tdi.updTag(t);
			if(count>0) {
				response.sendRedirect("tag/listtag.jsp");
			}else {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
