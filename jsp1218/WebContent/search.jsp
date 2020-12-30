<%@page import="com.lza.vo.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
* {
	margin: 0px;
	list-style: none;
}

.search {
	width: 1850px;
	height: 900px;
	background-image: url("images/iloli.gif");
	background-repeat: no-repeat;
	background-position: bottom right;
}

.sear-bar input {
	width: 600px;
	height: 62px;
	font-size: 24px;
	font-size: 1.5rem;
	background: #fff;
	padding: 12px 24px 12px 14px;
	outline: 0;
	border-radius: 50px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	position: relative;
	top: 200px;
	position: relative;
	top: 200px;
	left: 700px;
	top: 200px;
	position: relative;
	top: 200px;
}

.artic-ser {
	width: 650px;
	height: 360px;
	/* border: 1px solid black; */
	position: relative;
	top: 250px;
	left: 650px;
}

.artic-ser h5 {
	font-size: 14px;
}

.artic-ser p {
	font-size: 12px;
}

.artic-ser ul li {
	width: 600px;
	height: 60px;
	/* border: 1px solid black; */
	margin: 10px;
	text-align: center;
}

.artic-ser ul li:hover {
	background-color: #9A9A9A;
}
</style>
<body>
	<div class="search">
		<div class="sear-bar">
			<form action="article?opr=ser"  method="post">
			<input  class="text-input" type="text"
				name="artser" placeholder="想要找点什么呢？" required="">
				<input type="submit" value="搜索">
			</form>
		</div>
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
				<p>文章内容：${artic.articleContent }</p>
				<p>文章标签：${artic.tagId }</p>
				<p>作者：${artic.author }</p>
				<%-- <p>创建时间：${artic.articleDate }</p> --%>
				<p>评论：${artic.commentsId }</p>
			</c:forEach>
		</div>
	</div>
</body>
</html>