<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 로그인</title>
<link rel="stylesheet" href="/admin/CSS/layout2.css">
<script src="/admin/js/exam.js"></script>
<script src="/admin/js/formCheck.js"></script>
</head>
<body>

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
<div>
	<input type="hidden" name="delresult" value="${loginStat}" id="delresult"/>
</div>

<c:out value="${isMemRegSuccess}"/>

<input type="hidden" id="loginRes" value="<c:out value="${loginStat}"/>"> 

<div class="modal" id="modal">
 <div class="pop_layer"  id="pop_layer">
	<div class="pop_header">
		<div class="pop_title"><p>관리자 시스템</p></div>
	</div>
 	
 	<div class="pop_content">
 		<form name="login" method="post" action="/admin/admember/login" onsubmit="return loginFormCheck()">
 		 
 		 <input type="text" name="id" placeholder="아이디" class="logpw" size="13" maxlength="15" style="color:green"
 		  		value="<%if( request.getParameter("id")!= null)
 		  		out.print(request.getParameter("id"));%>" required><br>
 		 <input type="password" name="pw" placeholder="비밀번호" class="logpw" size="13" maxlength="15" required><br><br>
 		 <p class="msg" id="msg" style="color:red"></p>
 		 <input type="submit" value="로그인" class="login_btn">
 		</form>
 	</div>
 </div>
</div>

</body>
</html>