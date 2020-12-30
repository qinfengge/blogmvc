<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("utf-8");
	String tgid = request.getParameter("tgid");
	String tgname = request.getParameter("tgname");
%>
<body>
<form action="../tag?opr=uptag&uptagid=<%=tgid %>" method="post">
标签名称：<input type="text" value="<%=tgname %>"><br>
请输入修改后标签名称:<input type="text" name="uptagname">
<input type="submit" value="提交">
</form>
</body>
</html>