<%@page import="com.lza.vo.Tag"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
List<Tag> lt = (List<Tag>) request.getAttribute("listtag");
if (lt == null) {
	request.getRequestDispatcher("../tag?opr=listtag").forward(request, response);
}
%>
<body>
	<table border="1">
		<tr>
			<td>标签ID</td>
			<td>标签名</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${listtag }" var="tag">
		<tr>
			<td>${tag.tagId }</td>
			<td>${tag.tagName }</td>
			<td>
			<p><a href="../tag?opr=deltag&deltagid=${tag.tagId }">删除</a></p>
			<p><a href="uptag.jsp?tgid=${tag.tagId }&tgname=${tag.tagName }">修改</a></p>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>