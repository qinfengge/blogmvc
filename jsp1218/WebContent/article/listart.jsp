<%@page import="com.lza.vo.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String contextPath = request.getContextPath();//获取项目根目录，如/news_chapter03
	request.setAttribute("cpath", contextPath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
img{
width: 150px;
height: 150px;
}
</style>
<%
	List<Article> listart = (List<Article>)request.getAttribute("listart");
	if(listart==null){
		request.getRequestDispatcher("../article?opr=listart").forward(request, response);
	}
%>
<body>
	<table border="1">
		<tr>
			<td>文章图片</td>
			<td>文章ID</td>
			<td>文章名</td>
			<td>文章内容</td>
			<td>文章标签</td>
			<td>作者</td>
			<td>创建时间</td>
			<td>操作</td>
		</tr>
		<c:forEach items="${listart }" var="artic">
		<tr>
			<td><img alt="" src="${artic.imgSrc }"></td>
			<td>${artic.articleId }</td>
			<td>${artic.articleName }</td>
			<td>${artic.articleContent }</td>
			<td>${artic.tagId }</td>
			<td>${artic.author }</td>
			<td>创建时间</td>
			<td>
			<p><a href="../article?opr=delart&artlid=${artic.articleId }">删除</a></p>
			<p><a href="${cpath }/article?opr=toupdart&artlid=${artic.articleId }">修改</a></p>
			</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>