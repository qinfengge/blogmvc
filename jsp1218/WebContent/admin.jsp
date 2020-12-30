<%@page import="com.lza.vo.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	User u = (User)session.getAttribute("user");
	if(u==null){
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
%>
<h1>欢迎您，亲爱的管理员</h1>
<%=u.getUserName() %>
<br>
<a href="article?opr=toaddart">添加文章</a>
<a href="tag/addtag.jsp">添加标签</a>
<a href="user?opr=toadduser">添加用户</a>
<a href="comment?opr=toaddcom">添加评论</a>
<hr>
<a href="article?opr=listart">查看文章</a>
<a href="tag?opr=listtag">查看标签</a>
<a href="user?opr=toadduser">查看用户</a>
<a href="comment?opr=toaddcom">查看评论</a>
</body>
</html>