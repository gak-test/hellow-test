<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topnav">
   <a href="home.me">Home</a>
   <a href="noticelist.no">공지사항</a>
   <a href="memberList.me">회원관리</a>
   <a href="delmemberlist.me">탈퇴회원관리</a>
	<c:set value="${sess_name}" var="sessName" />
	<c:if test="${sessName != NULL}">	
		<span style="color:#fff;">${sessName}님 반갑습니다.</span>
		<span id="logOut">			
			<a href="logout.ame" style="background-color: #4caf50">LogOut</a>
		</span>
	</c:if>	
	 
	<c:if test="${sessName == NULL}"> 
		<span id="login" onclick="document.getElementById('modal').style.display = 'block'">
			<a href="#" style="background-color: #4caf50">LogIn</a>
		</span>   
	</c:if>
   


   
</div>    