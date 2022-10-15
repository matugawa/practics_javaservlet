<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login menu</title>
</head>
<body>

<p>login</p>
<form action="/new/LoginServlet?action=login" method="post">
<p>LOGIN ID：<input type="text" name="loginId"></p>
<p>LOGIN PASSWORD:<input type="password" name="password"></p>
<br>
<input type="submit" value="login">
</form>



</body>
</html>