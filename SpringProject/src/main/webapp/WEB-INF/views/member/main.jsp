<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/main.jsp</title>
</head>
<body>
<%
// String id=(String)session.getAttribute("id");
// 세션값이 없으면(세션값이 null이면) loginForm.jsp 이동
// if(id==null){
//  	response.sendRedirect("loginForm.me");
// }
%>
<c:if test="${empty sessionScope.id}">
	<c:redirect url="/member/login"></c:redirect>
</c:if>
${sessionScope.id }님 로그인 하셨습니다. 
<a href="${pageContext.request.contextPath}/member/logout">로그아웃</a><br>
<a href="${pageContext.request.contextPath}/member/info">회원정보조회</a><br>
<a href="${pageContext.request.contextPath}/member/update">회원정보수정</a><br>
<a href="${pageContext.request.contextPath}/member/delete">회원정보삭제</a><br>
<%
// if(id!=null){
// 	if(id.equals("admin")){
		%>
<%-- 		<a href="${pageContext.request.contextPath }/member/list">회원목록</a><br> --%>
		<%
// 	}
// }
%>
<c:if test="${ ! empty sessionScope.id}">
	<c:if test="${sessionScope.id eq 'admin'}">
	<a href="${pageContext.request.contextPath}/member/list">회원목록</a><br>
	</c:if>
</c:if>

<a href="${pageContext.request.contextPath}/board/write">글쓰기</a><br>
<a href="${pageContext.request.contextPath}/board/list">글목록</a><br>
<a href="${pageContext.request.contextPath}/board/fwrite">첨부파일 글쓰기</a><br>

</body>
</html>


