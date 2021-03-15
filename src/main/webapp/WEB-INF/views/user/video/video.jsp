<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=ROOT %>/CSS/layout2.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=ROOT %>/js/exam.js">
</script>
<script src="<%=ROOT %>/js/formCheck.js">
</script>
</head>
<body>
<%@ include file="/header.jsp" %>

<%@ include file="/menu.jsp" %>

<div class="content">
   <video src="../video/Soccer.mp4" width="500px" height="320px"
			controls autoplay loop>
</video>
</div>

<%@ include file="/footer.jsp" %>

<input type="hidden" name="resMsg" value="<%=request.getParameter("isMemRegSuccess")%>" id="resMsg">
 <input type="hidden" id="loginRes" value="<%=request.getParameter("login")%>"> 

<script>	
window.onload = function() {
	if(document.getElementById("resMsg").value == 1){
	document.getElementById("modal").style.display="block";
	}
	
	var login_msg;
	switch(document.getElementById("loginRes").value){
	case "0":
		msg = "아이디가 없습니다.";
		break;
	case "1":
		msg = "비밀번호가 불일치 합니다.";
		break;
	case "2":
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
 <%@ include file="/modal.jsp" %>
</body>
</html>








    
