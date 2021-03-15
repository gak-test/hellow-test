<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../common_header.jsp" %>
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

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>

<div class="content" style="width:100%; padding:50px;">
   <div class="main">
	<div class="card">
		<form name="memReg" method="post" action="<%=ROOT %>/member/memRegProc.jsp" onsubmit="return memberFormCheck()">
		 <fieldset><legend align="center">회원가입</legend>
			<input type="text" name="id" maxlength="15" placeholder="아이디" onchange="idCheck('<%=ROOT %>')" id="id_input">
<%-- 			<button onclick="idCheck('<%=ROOT %>')">아이디 중복</button> --%>
			<p id="idCheckMsg" style="color:red"></p>
			<input type="hidden" name="isIdCheck" id="isIdCheck">
			<br>
			
			<input type="password" name="pw" maxlength="15" placeholder="비밀번호">
			<p>* 최소 5자리~15자리</p><p id="pw_error" style="color:red"></p>
			<input type="text" name="nick_name" maxlength="15" placeholder="별명">
			<button onclick="">별명 중복</button><br>
			<p>* 최소 2자리 이상</p><p id="nick_name_error" style="color:red"></p>
			<input type="text" name="name" placeholder="이름" required><br>
			
			<input type="text" name="eid" placeholder="이메일ID"><a id="eid_error"></a>@
			<input type="text" name="domain" placeholder="이메일">
			<select name="sel_domain" onchange="inputDomain()">
				<option value="">직접 입력</option>
				<option value="gmail.com">gmail.com</option>
  				<option value="naver.com">naver.com</option>
  				<option value="hanmail.net">hanmail.net</option>
			</select><br>
			
			<fieldset style="width:140px">
			<legend align="center">성별</legend>
				<input type="radio" name="gender" value="M">남자
				<input type="radio" name="gender" value="F">여자
			</fieldset><br>
			
			<fieldset style="width:200px">
			<legend align="center">취미</legend>
				<input type="checkbox" name="hobby" value="1">tennis
				<input type="checkbox" name="hobby" value="2">game
				<input type="checkbox" name="hobby" value="3">football
				<p id="hobby_Check" style="color:red"></p>
			</fieldset><br>
			
			<script src="<%=ROOT %>/js/search_address.js"></script>
			<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<input type="text" name="postcode" size="5" id="postcode" readonly>
			<input type="button" onclick="search_address()" value="우편번호 찾기"><br>
			<input type="text" name="addr1" placeholder="주소" id="address" readonly><br>
			<input type="text" name="addr2" placeholder="상세주소입력" id="detailAddress">
			<input type="text" name="addr3" placeholder="참고항목" id="extraAddress" readonly>
			<br>
			
			<textarea name="intro" placeholder="자기소개(100글자이내)"></textarea><br>
			
			<input type="submit" value="등록" >
			<input type="reset" value="초기화">
		</fieldset>
		
		</form>
		
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








    