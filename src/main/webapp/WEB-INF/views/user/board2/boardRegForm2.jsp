<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/CSS/layout2.css">
<meta charset="UTF-8">
<title>Insert title here</title>
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
		<h2>■ 게시판 등록</h2>
		<hr>
		<div style="margin-top:20px">
			<form name="board2" method="post" enctype="multipart/form-data" action="boardRegProc.bo2">
				<input type="text" name="title2" placeholder="제목" maxlength="255" style="width:500px" required>
				<textarea name="content2" placeholder="내용"></textarea>
				<input type="file" name="filename1" >
				<input type="submit" value="등록">
			
			</form>
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


</body>
</html>








    