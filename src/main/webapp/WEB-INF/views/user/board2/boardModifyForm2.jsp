<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %> 


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
		<h2>■ 해외축구 게시판 수정</h2>
		<hr>
		<div style="margin-top:20px">
			<c:set value="${board2}" var="dto"/>
			<form name="board2" method="post" action="updateBoard.bo2">
				<input type="hidden" name="bno2" value="${dto.bno2}">
				<input type="hidden" name="count2" value="${dto.count2}">
				<input type="hidden" name="wdate2" value="${dto.wdate2}">
				<input type="hidden" name="writer" value="${dto.id}">
				<input type="text" name="title2" placeholder="제목" maxlength="255" style="width:500px" required
				value="${dto.title2}">
				<textarea name="content2" placeholder="내용">${dto.content2}</textarea>
				
				<div id="file">
			
				</div>
				<input type="submit" value="수정">
							
			</form>
		</div>
	</div>
	</div>

</div>
<%@ include file="../footer.jsp" %>
<input type="hidden" name="resMsg" value="<%=request.getParameter("isMemRegSuccess")%>" id="resMsg">
<input type="hidden" id="loginRes" value="<%=request.getParameter("login")%>">
<script>
function fileCheck(root_path,sno) {
	var x = new XMLHttpRequest();
	x.onreadystatechange = function(){
		if(x.readyState === 4){
			
			if(x.status === 200){
				
				var tag = document.getElementById("file");
				if(x.responseText.trim() === "0"){
					alert("파일 삭제 실패 하였습니다.");					
				} else {
					alert("파일 삭제 하였습니다.");
					tag.innerHTML = "<input type='file' name='filename1'>";
				} 
					
			} else {
				console.log('에러코드 :' + x.status);
			}
		}
	};
	x.open("POST", root_path + "/board/fileDel2.jsp",true);
	x.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	x.send("sno="+sno);
	
}
</script>

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









    