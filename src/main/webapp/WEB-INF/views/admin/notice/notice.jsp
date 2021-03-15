<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/layout2.css">
<script src="js/exam.js"></script>
<script src="js/formCheck.js"></script>
<meta charset="UTF-8">
<c:out value="${isMemRegSuccess}"/>

<input type="hidden" name="resMsg" value="<%=request.getParameter("isMemRegSuccess") %>" id="resMsg">
<input type="hidden" id="loginRes" value="<c:out value="${loginStat}"/>">

<title>관리자 시스템</title>
</head>
<body>
<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>

<div class="content">
	<div class="content center">
	<h2>▣ 공지사항</h2>	
		<hr>			
			<div style="margin-top:20px">
			<input type="hidden" name="cnt" value="" style="text-align:right"/>${fn:length(notice)}건 
				<c:if test="${sess_name != null}">
			  <button class="button" onclick="location.href='noticeRegForm.ano'" style="float:right">등록</button>
				</c:if>
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
					<tr onclick="location.href='noticeDetailView.ano?notice_no=${dto.notice_no}'">
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
<hr>
</div>

<%@ include file="../footer.jsp" %>
<div>
	<input type="hidden" name="delresult" value="${delstat}" id="delresult"/>

</div>
<script>
	window.onload = function(){
		if(document.getElementById("delresult").value == 1){
			alert("데이터가 삭제되었습니다.");
		}
	}
</script>

</body>
</html>








    