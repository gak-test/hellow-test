<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="topnav2">

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
	<c:set value="${sess_name}" var="sessName" />
	<c:if test="${sessName != NULL}">	
		<span style="color:#fff;">${sessName}님 반갑습니다.</span>
		<a href="/member/myPage" style="background-color: #4caf50">마이페이지</a>		
		<span id="logOut">			
			<a href="/member/logout" style="background-color: #4caf50">LogOut</a>
		</span>
	</c:if>	
	 
	<c:if test="${sessName == NULL}"> 
		<span id="login" onclick="document.getElementById('modal').style.display = 'block'">
			<a href="#" style="background-color: #4caf50">LogIn</a>
		</span>   
	</c:if>

</div>

<div class="topnav">
   <a href="/">홈</a>
   <a href="/notice/nlist">공지사항</a>
   <a href="/board/list">국내축구</a>
   <a href="/board2/boardList2.bo2">해외축구</a>
   <a href="#">경기 결과/일정</a>
   <a href="#">경기 분석</a>
   <a href="video/video.bo">동영상</a>
   <a href="#">실시간 경기</a>
	
   
</div>    