package com.lza.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lza.dao.imp.CommentDaoImp;
import com.lza.dao.imp.UserDaoImp;
import com.lza.vo.Comment;
import com.lza.vo.User;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String opr = request.getParameter("opr");
		CommentDaoImp cdi = new CommentDaoImp();
		if("addcom".equals(opr)) {
			try {
			String comcon = request.getParameter("ucom");
			String aidstr = request.getParameter("aid");
			String uidstr = request.getParameter("uid");
			int aid = Integer.parseInt(aidstr);
			int uid = Integer.parseInt(uidstr);
			Comment c = new Comment();
			c.setCommentId(0);
			c.setUserId(uid);
			c.setArticleId(aid);
			c.setComment(comcon);
			c.setCommentDate(new Date());
				int count = new CommentDaoImp().addComment(c);
				if(count>0) {
					request.setAttribute("type", 1);
				}else {
					request.setAttribute("type", 2);
				}
				request.getRequestDispatcher("/article/showart.jsp").forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//文章评论初始化
		if("coinit".equals(opr)) {
			try {
				String aidstr = request.getParameter("aid");
				int aid = Integer.parseInt(aidstr);
				UserDaoImp udi = new UserDaoImp();
				List<User> ulist = udi.selAllUser();
				List<Comment> clist = cdi.selByArt(aid);
				if(clist!=null) {
					request.setAttribute("clist", clist);
					request.setAttribute("ulist", ulist);
					request.getRequestDispatcher("/article/showart.jsp").forward(request, response);
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
