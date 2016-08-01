<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Web Socket</title>
		<script>
			var websocket = new WebSocket("ws://localhost:8080/websocket/serverEndpoint");
			websocket.onmessage = function(message) {
				var jsonData = JSON.parse(message.data);
				if (jsonData){
					messages.value += (jsonData.username + ": " + jsonData.message + "\n");
				}
				
			}
			function sendMessage() {
				websocket.send(messageText.value);
				messageText.value = '';
			}
		</script>
	</head>
	
	<body>
		<mark>User name: ${username}</mark>
		<br/>
		<textarea id='messages' readonly='true' rows='10' cols='50'></textarea>
		<br/>
		<input type='text' id='messageText' size='50'/>
		<br/>
		<input type='button' value='Send' onclick='sendMessage();'/>
	
		<br/>
		<a href="default.html">Home</a>
	</body>
</html>