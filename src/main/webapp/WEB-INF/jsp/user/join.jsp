<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<html>
<head>
	<title>회원가입폼</title>
</head>
<body>

<form:form modelAttribute="user" method="post" action="join">
<table>
<tr>
	<td> 아이디</td>
	<td> <form:input  path="id" /> </td>
</tr>
<tr>
	<td colspan="2"><form:errors path="id"/></td>
</tr>
<tr>
	<td> 이름</td>
	<td><form:input  path="name"/></td>
</tr>
<tr>
	<td colspan="2"><form:errors path="name"/></td>
</tr>
<tr>
	<td> email</td>
	<td> <form:input  path="email"/> </td>
</tr>
<tr>
	<td colspan="2"><form:errors path="email"/></td>
</tr>
<tr>
	<td> 패스워드</td>
	<td> <form:input  path="passwd" type="password"/> </td>
</tr>
<tr>
	<td colspan="2"><form:errors path="passwd"/></td>
</tr>
<tr>
	<td colspan="2" align="center"><input type="submit" value="가입"></td>
</tr>
</table>
</form:form>

</body>
</html>
