<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<!DOCTYPE html>
<html lang="ko">
<head>
<title>글쓰기 폼</title>
</head>
<body>

<form:form action="write" method="post" enctype="multipart/form-data" modelAttribute="guestbook" >
이름 : ${authUserName}<br><br>
<form:textarea path="content" cols="60" rows="6"/><br>
<form:errors path="content"/><br>
<!-- name을 같게 하였다. 배열로 전송된다. -->

<form:input type="file" path="images"/><br>
<form:input type="file" path="images"/><br>
<form:input type="file" path="images"/><br>


<input type="submit" value="확인"><br>
</form:form>
</body>
</html>