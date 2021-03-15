<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %> 


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/layout2.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/exam.js"></script>
<script src="js/formCheck.js">
</script>
</head>
<body>

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>

<div class="content" style="width:100%; padding:50px;">
   <div class="content center">
		<h2>■ 공지사항 수정</h2>
		<hr>
		<div style="margin-top:20px">
			<c:set value="${notice}" var="dto"/>
			<form name="notice" method="post" action="updatenotice.ano">
				<input type="hidden" name="notice_no" value="${dto.notice_no}">
				<input type="hidden" name="odate" value="${dto.odate}">
				<input type="text" name="title" placeholder="제목" maxlength="255" style="width:500px" required
				value="${dto.title}">
				<textarea name="content" placeholder="내용">${dto.content}</textarea>
				
				
				<input type="submit" value="수정">
							
			</form>
		</div>
	</div>

</div>
<%@ include file="../footer.jsp" %>



</body>
</html>








    