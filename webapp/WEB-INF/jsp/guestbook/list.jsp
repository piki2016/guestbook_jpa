<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/header.jsp"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="Keywords" content="게시판 목록" />
<meta name="Description" content="게시판 목록" />
<title>list</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 부트스트랩 -->
<link href="/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="/css/common.css" rel="stylesheet" media="screen">
<script src="//code.jquery.com/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<!-- include summernote css/js-->
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link href="/css/summernote.css" rel="stylesheet">
<link href="/css/summernote-bs3.css" rel="stylesheet">
<script src="/js/summernote.min.js"></script>
<script>
	$(document).ready(function() {
		var scrollHeight = 0;
		$('#summernote').summernote({
            height: 200,                 // set editor height
            minHeight: false,             // set minimum height of editor
            maxHeight: false,             // set maximum height of editor
            focus: true,                 // set focus to editable area after initializing summernote
            oninit: function() {
            	
            	var ned = $('div.note-editor');
            	var ne = $('div.note-editable');
            	//ned.css("overflow","auto");
            	
            	//ned.css("height", 300);
            	//ne.css("height", 300);
                scrollHeight = $(this).prop("scrollHeight");
              },            
            onenter: function(e) {
            	console.log('enter : ', e.keyCode, $(this).prop("scrollHeight"));
            },
            onkeydown: function(e) {
            	if(e.keyCode == 13){
            		var ned = $('div.note-editor');
            		var scrollHeight2 =  ned.prop("scrollHeight");
            		if(scrollHeight < scrollHeight2){
            		
            			ned.scrollTop(ned.prop("scrollHeight") );
            			scrollHeight = scrollHeight2;
            		}
            		
            		
            	}
//              	  	$(this).animate({ scrollTop: $(this).prop("scrollHeight") }, 300);
              },
            onImageUpload: function(files, editor, $editable) {
            	 sendFile(files[0], editor, $editable);
              },
			toolbar: [
			    //[groupname, [button list]]
			     
			    ['style', ['bold', 'italic', 'underline', 'clear']],
			    ['font', ['strikethrough']],
			    ['fontsize', ['fontsize']],
			    ['color', ['color']],
			    ['para', ['ul', 'ol', 'paragraph']],
			    ['height', ['height']],
			    ['insert', ['picture','link','video']],
			  ]
			});

        function sendFile(file, editor, welEditable) {
            data = new FormData();
            data.append("file", file);
            $.ajax({
                data: data,
                type: "POST",
                url: "/guestbook/upload",
                cache: false,
                contentType: false,
                processData: false,
                success: function(uploadFileId) {
                	var lastIndex = uploadFileId.lastIndexOf("/");
                	var imageId = uploadFileId.substring(lastIndex+1);
                	console.log("imageId", imageId);
                    editor.insertImage(welEditable, uploadFileId);
                }
            });
        }
		
		// 삭제 버튼을 클릭할 경우.
		$(".deleteGuestbook").click(function() {
			// 현재 눌려진 버튼의 seq속성값 구함.
			var seq = $(this).attr("seq");

			$.ajax({
				type : 'POST', // post 타입 전송
				url : "/guestbook/delete/" + seq, // 전송 url
				// data: { name: "John", age: 50 }   // 전송 파라미터
				cache : false, // ajax로 페이지를 요청해서 보여줄 경우
				// cache가 있으면 새로운 내용이 업데이트 되지 않는다.
				async : true, // 비동기 통신,  false : 동기 통신
				success : function(msg) { // 콜백 성공 응답시 실행
					console.log(msg);
					if (msg) { // 삭제를 성공하였을 경우.
						$("#guestbook_" + seq).remove();
					}
				},
				error : function() { // Ajax 전송 에러 발생시 실행
				},
				complete : function() { //  success, error 실행 후 최종적으로 실행
				}
			});
		});
	});
</script>
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
				<a class="navbar-brand" href="#">방명록</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="#about">About</a></li>
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

	<!-- 글쓰기 창. -->
	<script>
	$(document).ready(function() {
		$("#writeBtn").click(function(){
			$("#guestbookListContainer").first().before("hello");
		});
	});
	</script>
	<div class="container">
		<form:form action="write" method="post" enctype="multipart/form-data" modelAttribute="guestbook" >
		 <textarea id="summernote"></textarea>
		 <div class="row">
		 	<div class="col-md-1 col-sm-1"><form:errors path="content"/></div>
		 </div>
		 <div class="row">
		 	<div class="col-sm-offset-10 col-sx-12">
		 		<button id="writeBtn" type="button" class="btn btn-primary btn-sm">저장하기</button>
		 	</div>
		 </div>
		</form:form>
	</div>
	<!-- /container -->

	<div class="container" id="guestbookListContainer">
		<c:forEach var="guestbook" items="${pageData.content }"
			varStatus="status">
			<div class="panel panel-primary" id="guestbook_${guestbook.id}">
				<div class="panel-heading">
					<div class="row">
						<div class="col-sm-11 col-sx-12">${guestbook.user.name }</div>
						<div class="col-sm-1 col-sx-6">
							<c:if test="${isAuthenticated}">
								<c:if test="${authUserId == guestbook.user.id }">
									<button class="btn btn-default btn-xs deleteGuestbook"
										seq="${guestbook.id }">삭제</button>
								</c:if>
							</c:if>
						</div>
					</div>
				</div>
				<div class="panel-body">${guestbook.content }</div>
				<div class="panel-footer">
					${guestbook.createdDate }<br>
					<%--
					<c:forEach var="image" items="${guestbook.images }"
						varStatus="status2">
						<img src="/guestbook/download/${image.id}" width="350"
							height="350">
						<br>
					</c:forEach>
					 --%>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-2 col-sm-offset-2">
				<c:url var="firstUrl" value="/guestbook/list/1" />
				<c:url var="lastUrl" value="/guestbook/list/${pageData.totalPages}" />
				<c:url var="prevUrl" value="/guestbook/list/${currentIndex - 1}" />
				<c:url var="nextUrl" value="/guestbook/list/${currentIndex + 1}" />
				<ul class="pagination">
					<c:choose>
						<c:when test="${currentIndex == 1}">
							<li class="disabled"><a href="#">&lt;&lt;</a></li>
							<li class="disabled"><a href="#">&lt;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${firstUrl}">&lt;&lt;</a></li>
							<li><a href="${prevUrl}">&lt;</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
						<c:url var="pageUrl" value="/guestbook/list/${i}" />
						<c:choose>
							<c:when test="${i == currentIndex}">
								<li class="active"><a href="${pageUrl}"><c:out
											value="${i}" /></a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${currentIndex == pageData.totalPages}">
							<li class="disabled"><a href="#">&gt;</a></li>
							<li class="disabled"><a href="#">&gt;&gt;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${nextUrl}">&gt;</a></li>
							<li><a href="${lastUrl}">&gt;&gt;</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-2 col-sm-offset-5">
				<footer>
					<p>&copy; Company 2014</p>
				</footer>
			</div>
		</div>
	</div>

</body>
</html>