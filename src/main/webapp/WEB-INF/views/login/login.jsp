<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./loginForm">
        用户名：
		<input name="username" key="login.name" id="username" style="width:120px" value="" placeholder="账号" />
        <br>
        密码：
		<input id="password" name="password" type="password" key="login.password"  style="width:120px" value="" placeholder="密码"/>	
		<br>
        <p style="background-color: red">${errorMsg}</p>
		<button type="sumit">提交</button>
			
	</form>
</body>
</html>
