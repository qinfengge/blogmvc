<%@page import="com.lza.vo.Comment"%>
<%@page import="com.lza.vo.User"%>
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
* {
	margin: 0px;
	list-style: none;
}
.top-meue {
	width: 100%;
	height: 75px;
	position: fixed;
    background: rgba(255,255,255,.95);
    box-shadow: 0 1px 40px -8px rgba(0,0,0,.15);
    left: 0;
    top: 0;
}

.top-dic {
	width: 100%;
	height: 400px;
	border: 1px solid black;
	background-image: url("images/articles/a1.jpg");
	background-repeat: no-repeat;
	background-size: 100%;
}
.top-dic-wd{
position: relative;
top: 240px;
left: 500px;
width: 900px;
color: white;
}
.top-dic-wd h2{
font-size: 32px;
font-weight: 400;
width:500px;
position: relative;
left: 40px;
}
.top-dic-wd-me{
width: 780px;
height: 49px;
padding:40px;
font-size: 16px;
}
.top-dic-wd-me img{
width: 45px;
height: 45px;
border-radius:50%;
}
.top-dic-wd-me span{
margin:5px;
position: relative;
top: -5px;
}
.article-wd{
width: 780px;
height: 100%;
margin: 15px auto;
font-size: 17px;
font-family:cursive;
font-weight: 600;
}
.article-wd p{
margin: 20px;
}
.top-meue li{
float: left;
position: relative;
left: 90%;
top: 10px;
margin: 15px;
}
.top-meue li div svg{
width: 30px;
height: 30px;
}
.top-meue li div svg:hover{
width: 40px;
height: 40px;
}
</style>
<script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
<script>
	//处理服务端返回的处理添加新闻的信息
	/*
		type:1.图片类型不合法
	     	 2.添加新闻成功
	     	 3.添加新闻失败
	     	 4.图片过大
	*/
	var type= "${type}";
	if(type==1){
		alert("评论添加成功，刷新查看");
	}else if(type==2){
		alert("评论添加失败");
	}
</script>
<c:forEach items="${listart }" var="artic">
<%
	User u = (User)session.getAttribute("user");
	if(u==null){
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	List<Article> listart = (List<Article>)request.getAttribute("listart");
	int aid =0;
	for(Article a : listart){
		aid = a.getArticleId();
	}
	List<Comment> clist = (List<Comment>)request.getAttribute("clist");
	if(clist==null){
		request.getRequestDispatcher("../comment?opr=coinit&aid="+aid+"").forward(request, response);
	}
%>

<body>
	<div class="top-dic">
		<div class="top-dic-wd">
			<h2>${artic.articleName }</h2>
			<div class="top-dic-wd-me">
			<img alt="" src="${artic.imgSrc }">
			
			<span><span>${artic.author }</span><span>·</span><span>22天前</span><span>·</span><span>10次阅读</span><span>·</span><span>EDIT</span></span>
			
			</div>
		</div>
	</div>
	<div class="article-wd">
	${artic.articleContent }
	</div>
	<hr>
	<form action="${cpath }/comment?opr=addcom&aid=${artic.articleId }&uid=<%=u.getUserId() %>" method="post">
	<h3>用户---<%=u.getUserName() %></h3>
	评论内容：<input type="text" name="ucom"><br>
	<input type="submit" value="提交">
	</form>
	</c:forEach>
	<hr>
	<c:forEach items="${clist }" var="com">
	<c:forEach items="${ulist }" var="user">
	<c:if test="${com.userId==user.userId }">
        			<p>评论人： ${user.userName }</p>
        		</c:if>
	<c:if test="${com.userId==user.userId }">
        			<p>评论内容： ${com.comment }</p>
        		</c:if>
	</c:forEach>
				<%-- <c:if test="${com.userId!=user.userId }">
        			<p></p>
        		</c:if>
        		<c:if test="${com.userId!=user.userId }">
        			<p></p>
        		</c:if> --%>
	</c:forEach>
</body>
</html>