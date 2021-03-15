<%-- <%@ page import="com.mysql.cj.jdbc.PreparedStatementWrapper"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/layout2.css">
<meta charset="UTF-8">
<title>게시판</title>
<script>
	function checkYesNo() {
		if(confirm("정말로 삭제 하시겠습니까?")){
			return true;
		}else{
			return false;
		}
		
	}
</script>
</head>
<body>

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>



<div class="content" style="width:100%; padding:50px;">
   <div class="content center">
		<h2>■ 공지사항 세부내용</h2>
		<hr>
		<div style="margin-top:20px">
			
			<c:set value="${notice}" var="dto"/>
			<label>제목:${dto.title }</label>		
			<hr><br>		
			<label style="float:right">작성일자 :${dto.odate}</label>
			<hr><br>
			<label>내용 <br>${dto.content}</label>
			<hr><br>
			<form name="noticeModifybtn" method="post" action="noticeModifyForm.ano">
				<input type="hidden" name="notice_no" value="${dto.notice_no}">
				<input type="hidden" name="title" value="${dto.title}">
				<input type="hidden" name="content" value="${dto.content}">
				<input type="hidden" name="wdate" value="${dto.odate}">
				<input type="submit" class="button" value="수정">
			</form>
			
			<form name="noticeDelbtn" method="post" action="noticeDel.ano" onsubmit="return checkYesNo()">
				<input type="hidden" name="bno" value="${dto.notice_no}">
				<input type="submit" style="float:right" value="삭제">
			</form>
		</div>						

</div>
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>








    