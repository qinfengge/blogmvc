<%@page import="com.lza.vo.Article"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="artic-ser">
			<%
			List<Article> list = (List<Article>) request.getAttribute("alist");
			  /* if(list == null){
				request.getRequestDispatcher("article?opr=ser&artser=v").forward(request, response);
			}   */
			%>
			<c:forEach items="${alist }" var="artic">
				<p>ID：${artic.articleId }</p>
				<p>文章名：${artic.articleName }</p>
				<p>文章图片：${artic.imgSrc }</p>
				<p>文章内容：${artic.articleSrc }</p>
				<p>文章标签：${artic.tagId }</p>
				<p>作者：${artic.author }</p>
				<%-- <p>创建时间：${artic.articleDate }</p> --%>
				<p>评论：${artic.commentsId }</p>
			</c:forEach>
		</div>
</body>
</html>