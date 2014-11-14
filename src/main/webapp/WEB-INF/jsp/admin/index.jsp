<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자 메인 페이지</title>
</head>
<body>
<h1>관리자 메인</h1>
<hr>
<c:if test="${isAuthenticated}">
관리자 ${authUserName} 님 환영합니다.<br>
</c:if>

<a href="/guestbook/list">방명록으로 이동.</a>
</body>
</html>