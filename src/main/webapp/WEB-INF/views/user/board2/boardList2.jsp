<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/CSS/layout2.css">
<meta charset="UTF-8">
<title>해외축구</title>
<script src="/js/exam.js"></script>
<script src="/js/formCheck.js">
</script>
</head>
<body>

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>

<div class="content" style="width:100%; padding:50px;">
	<div class="main">
		<div class="card">
		<h2>■ 해외축구</h2>
		<hr>
			<div style="margin-top:20px">
				<c:if test="${sess_name != null}">
			  <button class="button" onclick="location.href='boardRegForm.bo2'" style="float:right">등록</button>
				</c:if>
			<table>
			
				<thead>
					<tr>
						<th>순번</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성일</th>
						<th>조회수</th>
						<th>작성자</th>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${board2}" var="dto">
					<tr onclick="location.href='boardDetailView.bo2?bno2=${dto.bno2}'">
						<td>${dto.bno2}</td>
						<td style="text-align: lefts">${dto.title2}</td>
						<td>${dto.content2}</td>
						<td>${dto.wdate2}</td>
						<td>${dto.count2}</td>
						<td>${dto.id}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
	</div>
</div>
<%@ include file="../footer.jsp" %>
<input type="hidden" name="resMsg" value="<%=request.getParameter("isMemRegSuccess")%>" id="resMsg">
<input type="hidden" id="loginRes" value="<%=request.getParameter("login")%>">


<script>
window.onload = function() {
	if(document.getElementById("resMsg").value == 1){
	document.getElementById("modal").style.display="block";
	}
	
	var msg;
	switch(document.getElementById("loginRes").value){
	case 0:
		msg = "아이디가 없습니다.";
		break;
	case 1:
		msg = "비밀번호가 불일치 합니다.";
		break;
	case 2:
		msg = "탈퇴요청한 회원입니다.";
		break;
		
	}
	
	if(document.getElementById("loginRes").value < 3){
		document.getElementById("modal").style.display="block";
		document.getElementById("msg").innerHTML = msg;
		document.forms["login"]["pw"].focus();
		}
}
</script>

<%@ include file="../modal.jsp" %>

<div>
	<input type="hidden" name="delresult" value="${delstat}" id="delresult"/>

</div>
<script>
	window.onload = function(){
		if(document.getElementById("delresult").value == 1){
			alert("데이터가 삭제되었습니다.");
		}
	}
</script>
</body>

</html>








    