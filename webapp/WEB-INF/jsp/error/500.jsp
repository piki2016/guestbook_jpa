<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>exception</title>
</head>
<body>
<h1>500!!!</h1>

아마도 로그인 쿠키 값이 사라지지 않아서 오류가 발생한듯 하다. 로그아웃을 하는 것 어떨까?<br>
<%
	exception.printStackTrace();
%>
<a href="/j_spring_security_logout">로그아웃</a>
</body>
</html>