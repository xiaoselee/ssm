<%@page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登入</title>
</head>
<body>
		<form name="loginform" action="../security/login" method="get">
			<div>用户名：<input name="userid" type="text"></div>
			<div>密码：<input name="password" type="password"></div>
		</form>
		<button type="button" id="sublogin">登入</button>

		<script type="text/javascript" src="../static/js/jquery.min.js" ></script>
</body>
	<script type="text/javascript">
		$(function(){
			$('#sublogin').click(function(){
				var userid = $('input[name=userid]').val().trim();
				var password = $('input[name=password]').val().trim();
				var data = {
					id:userid,
					password:password
				}
				$.post("../security/login",data,function(data){
					if(data.status && data.status == true){
						loginform.submit();
					}
				})
			});
		});
	</script>
</html>