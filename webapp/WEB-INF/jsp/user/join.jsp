<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>회원가입폼</title>
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
.form-join {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-join .form-join-heading, .form-join .checkbox {
	margin-bottom: 10px;
}

.form-join .checkbox {
	font-weight: normal;
}

.form-join .form-control {
	position: relative;
	font-size: 12px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-text-control {
	position: relative;
	font-size: 12px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
}

.form-join .form-control:focus {
	z-index: 2;
}

.form-join input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.form-join input[type="password"] {
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

						<li><a href="/user/login">로그인</a></li>
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
			<form:form modelAttribute="user" method="post" action="join" class="form-join" >
				<h2 class="form-join-heading">회원가입을 해주세요.</h2>
				<div class="row">
					<div class="col-sx-2 col-sm-2 form-text-control" >ID </div>
					<div class="col-sx-8 col-sm-8"><form:input path="userId" class="form-control"/></div>
				</div>
				<div class="row">
					<div class="col-sx-12 col-sm-12"><form:errors path="userId" /></div>
				</div>
				<div class="row">
					<div class="col-sx-2 col-sm-2 form-text-control">이름 </div>
					<div class="col-sx-8 col-sm-8"><form:input path="name" class="form-control"/></div>
				</div>
				<div class="row">
					<div class="col-sx-12 col-sm-12"><form:errors path="name" /></div>
				</div>
				<div class="row">
					<div class="col-sx-2 col-sm-2 form-text-control">email </div>
					<div class="col-sx-8 col-sm-8"><form:input path="email" class="form-control"/></div>
				</div>
				<div class="row">
					<div class="col-sx-12 col-sm-12"><form:errors path="email" /></div>
				</div>
				<div class="row">
					<div class="col-sx-2 col-sm-2 form-text-control">암호  </div>
					<div class="col-sx-8 col-sm-8"><form:input path="passwd" type="password" class="form-control"/></div>
				</div>
				<div class="row">
					<div class="col-sx-12 col-sm-12"><form:errors path="passwd" /></div>
				</div>
				<div class="row">
					<div class="col-sx-offset-5 col-sx-5 col-sm-offset-5 col-sm-5"><input type="submit" value="가입"></div>
				</div>
			</form:form>
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
