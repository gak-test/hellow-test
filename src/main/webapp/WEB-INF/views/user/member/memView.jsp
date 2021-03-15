<%-- <%@ page import="com.mysql.cj.jdbc.PreparedStatementWrapper"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
  

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/layout2.css">
<meta charset="UTF-8">
<title>회원정보 수정</title>
<script src="/js/search_address.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>


<div class="content" style="width:100%; padding:50px;">
   <div class="main">
	<div class="card">
		 <fieldset><legend align="center">회원수정</legend>
<%-- 			<c:set value="${member}" var="dto"/> --%>
			<label>아이디</label>
			${member.id}
			<br><br>
			<label>이름</label>
			${member.name} (
				<c:choose>
					<c:when test="${member.gender eq 'M' }">
					남자
					</c:when>
					<c:when test="${member.gender eq 'F' }">
					여자
					</c:when>
					
					<c:otherwise>
					사람이 아닙니다.
					</c:otherwise>
				
				</c:choose>
			
			)
			 
			 <br><br>
			<label>이메일</label>
			${member.email}
			<br><br>
			<label>취미</label>
			<c:forEach var="h" items="${hobby}">
					<c:forEach var="myhobby" items="${member.hobby}">
						<c:if test="${myhobby == h.codeVal }">${h.codeName}</c:if>
						
					</c:forEach>				
			</c:forEach>
						
			<br><br>
			<label>우편번호</label><br>
				${member.post}<br>
				${member.addr1} ${member.addr2} ${member.addr3}
		
			<br><br>
			<label>자기소개</label><br>
				${member.intro}
			
			</fieldset>
									
	</div>
	</div>

</div>

<%@ include file="../footer.jsp" %>
</body>
</html>








    