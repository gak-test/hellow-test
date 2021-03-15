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
<script src="<%=ROOT %>/js/exam.js"></script>
<script src="<%=ROOT %>/js/formCheck.js">
</script>
</head>
<body>

<%@ include file="/header.jsp" %>

<%@ include file="/menu.jsp" %>

<div class="content" style="width:100%; padding:50px;">
   <div class="main">
	<div class="card">
		<h2>■ 동영상 등록</h2>
		<hr>
		<div style="margin-top:20px">
			<form name="board" method="post" enctype="multipart/form-data" action="videoRegProc.jsp">
				<input type="text" name="title3" placeholder="제목" maxlength="255" style="width:500px" required>
				<textarea name="content3" placeholder="내용"></textarea>
				<input type="file" name="filename1" >
				<input type="submit" value="등록">
			
			</form>
		</div>
	</div>
	</div>

</div>
<%@ include file="/footer.jsp" %>
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

<div class="modal" id="modal">
 <div class="pop_layer"  id="pop_layer">
	<div class="pop_header">
		<div class="pop_title">login</div>
		<div class="pop_close" onclick="pop_layer_close()">
		<a href="#">close</a></div>
	</div>
 	
 	<div class="pop_content">
 		<form name="login" method="post" action="<%=ROOT %>/member/loginProc.jsp" onsubmit="return loginFormCheck()">
 		 
 		 <input type="text" name="id" placeholder="아이디" class="logpw" size="13" maxlength="15" style="color:green"
 		  		value="<%if( request.getParameter("id")!= null)
 		  		out.print(request.getParameter("id"));%>" required><br>
 		 <input type="password" name="pw" placeholder="비밀번호" class="logpw" size="13" maxlength="15" required><br><br>
 		 <p class="msg" id="msg"></p>
 		 <input type="submit" value="로그인" class="login_btn">
 		
 		</form>
 	<div>
 		<p> <a href="<%=ROOT %>/member/memRegForm.jsp">[회원가입]</a></p> <p>[ID/PW찾기]</p>
 	
 	</div>
 	</div>
 </div>
</div>


</body>
</html>








    