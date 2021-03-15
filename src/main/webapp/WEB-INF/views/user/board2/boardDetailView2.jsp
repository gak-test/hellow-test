<%-- <%@ page import="com.mysql.cj.jdbc.PreparedStatementWrapper"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
    


<!DOCTYPE html>
<html>
<head>
<script src="/js/exam.js">
</script>
<script src="/js/formCheck.js">
</script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/CSS/layout2.css">
<meta charset="UTF-8">
<title>해외축구</title>
<script src="/js/search_address.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
   <div class="main">
	<div class="card">
		<h2>■ 게시판 세부내용</h2>
		<hr>
		<div style="margin-top:20px">
			<c:set value="${board2}" var="dto"/>
			<label>제목:${dto.title2}</label>
			<label style="float:right">조회수:${dto.count2}</label>		
			<hr><br>		
			<label>작성자:${dto.id}</label>
			<label style="float:right">작성일자 :${dto.wdate2}</label>
			<hr><br>
				
			<label>내용 <br>${dto.content2}</label>
			<hr><br>
			<c:if test="${dto.upfile.fileName != null}">
			<c:set value="${dto.upfile.fileType}" var="ftype"/>
			<c:set value="${fn:substring(ftype,0,fn:indexOf(ftype,'/'))}" var="type"/>
			<label>첨부파일:
			
			<form name="filedown" method="post" action="fileDown.do">
				<input type="hidden" name="filename2" value="${dto.upfile.fileName}">
				<input type="hidden" name="savefilename2" value="${dto.upfile.saveFileName}">
				<input type="hidden" name="filesize2" value="${dto.upfile.fileSize}">
				<input type="hidden" name="filepath2" value="${dto.upfile.filePath}">
				<input type="hidden" name="filetype2" value="${dto.upfile.fileType}">
				
				<c:if test="${type eq 'image'}">
					<img width="100px" height="100px" src="/upload/${dto.upfile.saveFileName}">
				</c:if>
				${dto.upfile.fileName} (사이즈: ${dto.upfile.fileSize})
			</form>
			</c:if>	
			<c:if test="${dto.id == sess_id }">
			</label>
			
			
			<form name="boardModifybtn" method="post" action="boardModifyForm.bo2">
				<input type="hidden" name="bno2" value="${dto.bno2}">
				<input type="hidden" name="title2" value="${dto.title2}">
				<input type="hidden" name="content2" value="${dto.content2}">
				<input type="hidden" name="count2" value="${dto.count2}">
				<input type="hidden" name="wdate2" value="${dto.wdate2}">
				<input type="hidden" name="writer2" value="${dto.id}">
				<input type="submit" class="button2" value="수정">
			</form>
			
			<form name="boardDelbtn" method="post" action="boardDel.bo2" onsubmit="return checkYesNo()">
				<input type="hidden" name="bno2" value="${dto.bno2}">
				<input type="submit" style="float:right" value="삭제">
			</form>
			</c:if>
		</div>						
	</div>
	</div>

</div>
<%@ include file="../footer.jsp" %>
</body>
</html>








    