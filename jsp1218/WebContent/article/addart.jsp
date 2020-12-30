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
		alert("图片上传失败，文件类型只能是gif、jpg、jpeg");
		location.href="article?opr=toaddart";
	}else if(type==2){
		var rs = window.confirm("文章添加成功，继续添加其他新闻？");
		if(rs){
			location.href="article?opr=toaddart";
		}else {
			location.href="article?opr=listart";
		}
	}else if(type==3){
        alert("文章添加失败，请联系管理员！");
        location.href="article?opr=toaddart";
	}else if(type==4){
		alert("图片上传失败，文件的最大限制是：5MB");
		location.href="article?opr=toaddart";
	}
</script>
<body>
<form action="article?opr=addart" enctype="multipart/form-data" method="post">
<h1 id="opt_type"> 添加新闻： </h1>
	文章名：<input type="text" name="artname"><br>
	文章标签：<select name="atag">
        	<option value="0">请选择</option>
        	<c:forEach items="${ltags }" var="tag">
        		<option value='${tag.tagId }'> ${tag.tagName } </option>
        	</c:forEach>
        	</select><br>
    作者：<input type="text" name="author"><br>
	文章内容：<textarea name="acontent" cols="70" rows="10"></textarea><span class="errorMsg"></span><br>
	文章图片：<input name="file" type="file" class="opt_input" />(只能上传"gif", "jpg", "jpeg"格式图片)<br>
	<input type="submit" value="提交" class="opt_sub" />
	<input type="reset" value="重置" class="opt_sub" />
</form>
</body>
</html>