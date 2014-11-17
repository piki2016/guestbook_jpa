<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- 아래부분은 인증되었을 경우에만 실행 -->
<sec:authorize access="isAuthenticated()" var="isAuthenticated">
	<!-- jstl로 변수를 설정한다. --> 
    <c:set value="true" var="isAuthenticated" scope="request"/> 
    <sec:authentication var="authUserId" property="principal.id" scope="request"/> 
    <sec:authentication var="authUserName" property="principal.name" scope="request"/> 
<script>
    window.__a__={ "is":true, "id":"\${authUserId }", "profilePic":"\${authUserProfilePic}" }; 
</script>
</sec:authorize>
