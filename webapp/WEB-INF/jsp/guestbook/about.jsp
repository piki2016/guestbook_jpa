<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="about" />
<meta name="Description" content="about" />
<title>list</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 -->
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/css/common.css" rel="stylesheet" media="screen">
<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
</head>
<body>


	<!-- Static navbar -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/">방명록</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/guestbook/about">About</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${!isAuthenticated}">
						<li>
						<li><a href="/user/login">로그인</a></li>
						<li><a href=/user/join>회원가입</a></li>
					</c:if>
					<c:if test="${isAuthenticated}">
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-expanded="false">${authUserName}
								<span class="caret"></span>
						</a>
							<ul class="dropdown-menu" role="menu">
								<li><a href="/j_spring_security_logout">로그아웃</a></li>
							</ul></li>
					</c:if>

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<!--  설명. -->
	<div class="container">
		<div class="jumbotron">
			<h1>방명록 프로젝트</h1>
			<p class="lead">간단해 보이는 방명록. 누구나 사용해 보았을 방명록을 다양한 기술로 구현하여 수업에서 사용할 목적으로 만들어봤습니다. 
			<a href="https://github.com/urstory/guestbook_jpa">여기</a> 에서 최신 버전을 구할 수 있습니다.<br>
			아래의 목록은 해당 방명록을 만들기 위한 기술 요소들 입니다.
			<br>
			</p>
			<p>
				<a class="btn btn-lg btn-success" href="/">방명록 목록으로 가기</a>
			</p>
		</div>
	</div>

	<!-- 프로젝트 기술요소 -->
	<div class="container">
		<div class="row marketing">
			<div class="col-lg-6">
				<h4>개발 환경</h4>
				<p>windows 7, oracle 10g, Eclipse luna EE, Maven, Oracle 10g xe</p>

				<h4>사용한 기술/언어</h4>
				<p>Java, JavaScript, JQuery, Servlet/JSP, Spring MVC, Spring, Spring-Data-JPA, QueryDSL, ejs.js, bootstrap, summernote editor, ajax, json</p>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-xs-offset-2 col-sm-4 col-sm-offset-5">
				<footer>
					<p>&copy; Sunnyvale co., ltd. 2014</p>
				</footer>
			</div>
		</div>
	</div>

</body>
</html>



