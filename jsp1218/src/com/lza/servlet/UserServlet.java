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

import com.lza.dao.imp.UserDaoImp;
import com.lza.vo.User;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String opr = request.getParameter("opr");
		if("login".equals(opr)){
			String userName = request.getParameter("userName");
			String passwd = request.getParameter("passwd");
			UserDaoImp udi = new UserDaoImp();
			try {
				User u = udi.userLogin(userName, passwd);
				if(u!=null&&u.getIsAdmin()==1) {
					session.setAttribute("user", u);
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}else if (u!=null&&u.getIsAdmin()==0) {
					session.setAttribute("user", u);
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if("register".equals(opr)){
			String userName = request.getParameter("reg-userName");
			String passwd = request.getParameter("reg-passwd");
			String passwdRg = request.getParameter("regYes-passwd");
			String errorMsg = null;
			try {
				UserDaoImp udi = new UserDaoImp();
				User u = new User();
				u.setUserName(userName);
				u.setPasswd(passwd);
				u.setUserId(0);
				u.setIsAdmin(0);
				User unameRg = udi.userRg(userName);
				if(passwd.equals(passwdRg)&&unameRg==null) {
					int i = udi.userRegiister(u);
					if(i>0) {
						request.getRequestDispatcher("succes.jsp").forward(request, response);
					}else {
						request.getRequestDispatcher("error.jsp").forward(request, response);
					}
				} 
				else if (!passwd.equals(passwdRg)) { 
					request.setAttribute("errorMsg", "密码不一致");
					request.getRequestDispatcher("register.jsp").forward(request, response); }
				else if (unameRg!=null) {
					request.setAttribute("errorMsg", "用户名已存在");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
				else {
					request.getRequestDispatcher("error.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
