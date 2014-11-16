<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="게시판 목록" />
<meta name="Description" content="게시판 목록" />
<title>list</title>
</head>
<body>
<h1>방명록 목록보기</h1>
<hr>

<c:if test="${!isAuthenticated}">
<!-- 로그인을 해야만 보여지는 곳이다. -->
<a href="/user/login">로그인</a>&nbsp;&nbsp;&nbsp;<a href="/user/join">회원가입</a>
</c:if>
<c:if test="${isAuthenticated}">
${authUserName} 님 환영합니다.<br>
<a href=/j_spring_security_logout>로그아웃</a>
</c:if>
<br>
<hr>
<c:forEach var="guestbook" items="${list }" varStatus="status">
이름 : ${guestbook.name }<br>
내용 : 
<pre>
${guestbook.content }
</pre>	<br>
등록일 : ${guestbook.regdate }<br>
	<c:forEach var="image" items="${guestbook.images }" varStatus="status2">
		<img src="/guestbook/download/${image.id}" width="50%" height="50%"><br>
	</c:forEach>

<hr>
</c:forEach>

<a href="write">글쓰기</a>
</body>
</html>