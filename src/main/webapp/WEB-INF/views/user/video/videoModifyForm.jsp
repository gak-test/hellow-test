<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/mysql_conn.jsp" %>
<%@ include file="../common_header.jsp" %>
<%
	String seqno3 = request.getParameter("seqno3");
	
	String sql="SELECT * FROM video WHERE seqno3 = ?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, seqno3);
	ResultSet rs =stmt.executeQuery();
	rs.next();
	
	sql="SELECT * FROM videofileUp WHERE v_sno = ? ";
	stmt = conn.prepareStatement(sql);
	stmt.setString(1, seqno3);
	ResultSet f_rs =stmt.executeQuery();

%>

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
		<h2>■ 게시판 수정</h2>
		<hr>
		<div style="margin-top:20px">
			<form name="board" method="post" enctype="multipart/form-data" action="boardModifyProc.jsp">
				<input type="hidden" name="seqno3" value="<%=seqno3%>">
				<input type="text" name="title3" placeholder="제목" maxlength="255" style="width:500px" required
				value="<%=rs.getString("title3") %>">
				<textarea name="content3" placeholder="내용"><%=rs.getString("content3") %></textarea>
				
				<div id="file">
				<% if(f_rs.next()){ 
					String sno3 = f_rs.getString("sno3");
				%>					
					<%= f_rs.getString("filename")%>
					<input type="button" Value="삭제" onclick="fileCheck('<%=ROOT %>','<%=sno3 %>')">
				<%} else { %>
					<input type="file" name="filename1">
				<% } %>
				</div>
				<input type="submit" value="수정">
							
			</form>
		</div>
	</div>
	</div>

</div>
<%@ include file="/footer.jsp" %>
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








    