<%-- <%@ page import="com.mysql.cj.jdbc.PreparedStatementWrapper"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
 
   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/layout2.css">
<meta charset="UTF-8">
<title>공지사항</title>
</head>
<body>

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>



<div class="content">

		<div style="margin-top:20px; width:100%">
		
			<label>제목:${notice.title }</label>		
			<hr><br>		
			<label style="float:right">작성일자 :${notice.odate}</label>
			<hr><br>
			<label>내용 <br>${notice.content}</label>
			<hr><br>
			<form name="noticeModifybtn" method="post" action="noticeModifyForm.no">
				<input type="hidden" name="notice_no" value="${notice.notice_no}">
				<input type="hidden" name="title" value="${notice.title}">
				<input type="hidden" name="content" value="${notice.content}">
				<input type="hidden" name="wdate" value="${notice.odate}">
			</form>
			
		</div>						
</div>
<%@ include file="../footer.jsp" %>
</body>
</html>








    