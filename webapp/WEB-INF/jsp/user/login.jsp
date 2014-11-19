<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Guestbook Loginform</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 -->
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/css/common.css" rel="stylesheet" media="screen">
<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- include summernote css/js-->
<link
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css"
	type="text/css" rel="stylesheet" />
<link href="/css/summernote.css" rel="stylesheet">
<link href="/css/summernote-bs3.css" rel="stylesheet">
<script src="/js/summernote.min.js"></script>
<script src="/js/ejs.js"></script>
<style type="text/css">
.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  font-size: 16px;
  height: auto;
  padding: 10px;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-left-radius: 0;
  border-bottom-right-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
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
				<c:if test="${!isAuthenticated}">
					<ul class="nav navbar-nav navbar-right">

						<li><a href=/user/join>회원가입</a></li>
					</ul>
				</c:if>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<c:if test="${isAuthenticated}">
		<div class="container">
			<div class="jumbotron">
				<h3>이미 로그인된 상태입니다.</h3>
				<p>
					<a class="btn btn-lg btn-success" href="/">방명록 목록보기</a>
				</p>
			</div>
		</div>
	</c:if>
	<c:if test="${!isAuthenticated}">
		<div class="container">
			<form class="form-signin" action="/j_spring_security_check" method="post">
				<h2 class="form-signin-heading">로그인을 해주세요.</h2>
				<h4>${message}</h4>
				<input type="text"  name="j_username" class="form-control" autofocus>
				<input type="password" name="j_password" class="form-control" placeholder="Password">
				<label class="checkbox"> <input type="checkbox"
							name="_spring_security_remember_me" checked /> 로그인 기억
				</label>
				<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
			</form>
		</div>
	</c:if>

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
