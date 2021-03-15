<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.util.Date" %>
    
<%@ include file="/mysql_conn.jsp" %>

<%
	String sql ="SELECT * FROM video ORDER BY wdate3 DESC";
	PreparedStatement stmt = conn.prepareStatement(sql, 
												   ResultSet.TYPE_SCROLL_SENSITIVE,
												   ResultSet.CONCUR_READ_ONLY);
	
	ResultSet rs = stmt.executeQuery();
	 

%>

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
		<h2>■ 동영상</h2>
		<hr>
			<div style="margin-top:20px">
			<%if(isLogin) { %>
			  <button class="button" onclick="location.href='videoRegForm.jsp'" style="float:right">등록</button>
			<%} %>
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
				<%
				//커서를 마지막 레코드 이용
				rs.last();
				int num = rs.getRow();
				//커서를 매처음 위치로 이동
				rs.beforeFirst();
					
				
				while(rs.next()){	
					String title;
					int cnt = 0;
					if(rs.getString("title3").length()>10){
						title = rs.getString("title3").substring(0,8) + "...";
					} else {
						title = rs.getString("title3");
					}
					if(rs.getInt("count3") > 0){
						cnt = rs.getInt("count3");
					}
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					Date date = rs.getTimestamp("wdate3");
				%>
					<tr onclick="location.href='videoDetailView.jsp?seqno=<%=rs.getString("seqno3") %>'">
						<td><%=num-- %></td>
						<td style="text-align: lefts"><%=title %></td>
						<td><%=rs.getString("content3") %></td>
						<td><%=sdf.format(date) %></td>
						<td><%=cnt %></td>
						<td><%=rs.getString("id3") %></td>
					</tr>
				<%} %>	
				</tbody>
			</table>
				
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

<%@ include file="/modal.jsp" %>


</body>

</html>








    