<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/admin/CSS/layout2.css">
<script src="/admin/js/exam.js"></script>
<script src="/admin/js/formCheck.js"></script>
<meta charset="UTF-8">
<c:out value="${isMemRegSuccess}"/>

<input type="hidden" name="resMsg" value="<%=request.getParameter("isMemRegSuccess") %>" id="resMsg">
<input type="hidden" id="loginRes" value="<c:out value="${loginStat}"/>">

<script>
	window.onload = function(){
		if(document.getElementById("delresult").value == 1){
			alert("일치하는 회원 정보가 없습니다.");
		}
		if(document.getElementById("loginRes").value == "loginfail"){
			document.getElementById("modal").style.display = "block";
			document.getElementById("msg").innerHTML = "아이디가 없습니다.";
			document.forms["login"]["pw"].focus();
			
		}
	}
</script>
<title>관리자 시스템</title>
</head>
<body>
<%@ include file="header.jsp" %>

<%@ include file="menu.jsp" %>

<div class="content">
	<div class="content center">
		회원등록 건수 ${cnt}명
		<br>	
		회원탈퇴요청 건수 ${dcnt}명 
	</div>
<hr>
</div>

<%@ include file="footer.jsp" %>


</body>
</html>








    