<%@page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<p>没有权限浏览改网页</p>
		<div id="togo"></div>
		
		
</body>
<script type="text/javascript" src="../static/js/jquery.min.js" ></script>
<script type="text/javascript">
	var noauth = ${noauth};
	var _html = "";
	if(noauth){
		_html = '<a href="#" onclick="history.back()">点击返回</a>';
	}else{
		_html = '<a href="../security/login">点击登入</a>';
	}
	$('#togo').html(_html);
</script>
</html>