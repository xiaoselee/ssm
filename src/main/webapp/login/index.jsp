<%@page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
<title>MOBAN</title>
<link href="../static/css/style-login.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="App Loction Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements"./>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
</script>
<!--webfonts-->
<link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
<!--//webfonts-->
</head>
<body>
	<h1>App Location Form</h1>
		<div class="app-location">
			<h2>Welcome to Locaticus</h2>
			<div class="line"><span></span></div>
			<div class="location"><img src="../static/images/location.png" class="img-responsive" alt="" /></div>
			<form name="loginform" action="../security/login" method="get" >
				<input type="text" class="text" name="name" value="用户名" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '用户名';}" >
				<input type="password" name="password" value="" onFocus="" onBlur="">
				<input type="text" id="captcha" name="captcha" maxlength="5" />
                <img id="captchaimg" src="captcha" onclick="this.src='captcha?d='+new Date().getTime()"/>
				<div class="sublogin"><input type="button" id="sublogin" value="登陆" ></div>
				<div class="clear"></div>
				<div class="new">
					<h3><a href="#">Forgot password ?</a></h3>
					<h4><a href="#">New here ? Sign Up</a></h4>
					<div class="clear"></div>
				</div>
			</form>
		</div>
	<!--start-copyright-->
   		<div class="copy-right">
				<p>Copyright &copy; 2015.Company name All rights reserved.More Templates <a href="http://www.cssmoban.com/" target="_blank" title="模板之家">模板之家</a> - Collect from <a href="http://www.cssmoban.com/" title="网页模板" target="_blank">网页模板</a></p>
		</div>
	<!--//end-copyright-->
</body>
<script type="text/javascript" src="../static/js/jquery.min.js" ></script>
<script type="text/javascript">
		$(function(){
			$('#sublogin').click(function(){
				var username = $('input[name=name]').val().trim();
				var password = $('input[name=password]').val().trim();
				var captcha = $('input[name=captcha]').val().trim();
				var data = {
					name:username,
					password:password,
					captcha:captcha
				}
				$.post("../security/login",data,function(data){
					if(data.status && data.status == true){
						loginform.submit();
					}else{
						$('#captchaimg').click();
						alert(data.errMsg);
					}
				})
			});
		});
	</script>
</html>