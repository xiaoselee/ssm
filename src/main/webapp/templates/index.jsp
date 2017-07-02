<%@page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>测试WebSocket</title>
	</head>
	<body>
		<div> this is port is ${port}</div>
		<button id="openWs" type="button" onclick="openWs();">open</button><br />
		<button id="close" type="button" onclick="closeWs();">close</button>
		<div id="messageWin" style="border: 1px black solid;width: 800px;height: 400px;overflow: auto;">
		</div>

		<textarea id="info" style="width: 600px;height: 50px;border: black 1px solid;"></textarea>
		<div>
			<button id="send" type="button" onclick="send();">send</button>
		</div>
	</body>

	<script type="text/javascript" src="../static/js/jquery.min.js"></script>
	<script type="text/javascript" src="../static/js/sockjs.min.js"></script>
	<script type="text/javascript">
		var socket = null;
		var url = 'ws://192.168.56.101/ssm/marco'; //html5原生支持
		var urlSocketJS = 'marco'; //sockjs.min.js
		function openWs() {
			if(socket){
				return true;
			}
			// 创建一个Socket实例
			if('WebSocket' in window) {
				socket = new WebSocket(url);
				
				// 打开Socket 
				socket.onopen = function(event) {
						$('#massagr').append('<div style="color: red;">打开连接~</div>');
					}
					// 监听消息
				socket.onmessage = addInfo;
				// 监听Socket的关闭
				socket.onclose = function(event) {
					console.log('Client notified socket has closed', event);
					socket = null;
				}
			} else {
				socket = new SockJS(urlSocketJS);
			}
		}

		function send() {
			// 发送一个初始化消息
			
			openWs();
			var ss = $('#info').val();
			if(ss.trim() != '') {
				socket.send(ss);
				$('#info').val(null);
			}
		}

		function closeWs() {
			// 关闭Socket.... 
			socket.close()
			
		}
		function addInfo(event) {
			console.log(event);
			var object = JSON.parse(event.data)
			if(object.type == "message"){
				$('#messageWin').append('<div>' + object.value+ '</div>');
				$('#messageWin').scrollTop( $('#messageWin')[0].scrollHeight);
			}
			
		}
	</script>

</html>