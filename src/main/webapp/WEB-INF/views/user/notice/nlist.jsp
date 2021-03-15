<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.util.Date" %>     
  
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/layout2.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/exam.js">
</script>
<script src="/js/formCheck.js">
</script>
</head>
<body>
<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>

<div class="content">
   <div class="main">
	<div class="card">
	<input type="hidden" name="cnt" value="" style="text-align:right"/>${fn:length(notice)}건 
			<table>
			
				<thead>
					<tr>
						<th>순번</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성일자</th>

					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${notice}" var="dto">
					<tr onclick="location.href='/notice/nView?notice_no=${dto.notice_no}'">
						<td>${dto.notice_no}</td>
						<td style="text-align: lefts">${dto.title}</td>
						<td>${dto.content}</td>
						<td>${dto.odate}</td>
					</tr>
					</c:forEach>
					
				</tbody>
			</table>
	</div>
   </div>
    <div class="side">
	    <p>실시간 채팅 구현</p>
	    <div style="background-color:green; width:100%;height:400px; "></div>
	    <p>광고창</p>
	    <img src="/img/축구공.PNG">
    </div>
</div>

<%@ include file="../footer.jsp" %>

<c:out value="${isMemRegSuccess}"/>

<input type="hidden" name="resMsg" value="<%=request.getParameter("isMemRegSuccess") %>" id="resMsg">
<input type="hidden" id="loginRes" value="<c:out value="${loginStat}"/>">


<script>	
window.onload = function() {
	
	
	
	if(document.getElementById("resMsg").value == 1){
		alert("회원이 등록되었습니다");
		document.getElementById("modal").style.display="block";
	}
	

	if(document.getElementById("loginRes").value == "loginfail"){
		document.getElementById("modal").style.display = "block";
		document.getElementById("msg").innerHTML = "아이디가 없습니다.";
		document.forms["login"]["pw"].focus();
		
	}
}
</script> 
 <%@ include file="../modal.jsp" %>
</body>
</html>








    