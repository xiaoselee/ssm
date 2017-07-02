<%@page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>
<body>
	<div>子域</div>
	<div id="test"></div>
	<pre id="output" class="alert alert-warning"></pre>
</body>
<script type="text/javascript" src="../static/js/jquery.min.js"></script>
<script type="text/javascript" src="../static/js/ms.js"></script>
<script type="text/javascript">
	var messenger = new Messenger('iframe', 'MessengerDemo');
	messenger.listen(function(msg) {
		var newline = '\n';
		var text = document.createTextNode(msg + newline);
		document.getElementById('output').appendChild(text);
	});

	$(function() {
		messenger.send("来自子域：加载完毕");
		console.log("finish");
	});

	messenger.addTarget(window.parent, 'parent');
</script>
</html>