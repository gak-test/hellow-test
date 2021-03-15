<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="CSS/layout2.css">
<script src="js/exam.js"></script>
<script src="js/formCheck.js"></script>
<meta charset="UTF-8">

<title>관리자 시스템</title>
</head>
<body>
<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>

<div class="content">
	<div class="content center">
		<h2>■ 회원리스트</h2>
		<form action ="search.ame" name="search">
			<input type="radio" value = "0" name="msearch"<c:if test="${grade== 0}">checked</c:if>>일반회원
			<input type="radio" value = "2" name="msearch"<c:if test="${grade== 2}">checked</c:if>>운영자
			<input type="radio" value = "1" name="msearch"<c:if test="${grade== 1}">checked</c:if>>관리자
			<input type="text" placeholder="이름을 입력하세요" name="searchbar" value="${name}">
			<input type="submit" value="검색"></input>
			<input type="hidden" name="cnt" value="" style="text-align:right"/>${fn:length(member)}명
		</form>
		<table>
				<thead>
					<tr>
						<th>순번</th>
						<th>id</th>
						<th>이름</th>
						<th>성별</th>
						<th>등급</th>
						<th>가입일자</th>
						<th>탈퇴요청일자</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${member}" var="dto" varStatus="status">
						<td>${fn:length(member) - status.index}</td>
						<td>${dto.id}</td>
						<td>${dto.name}</td>
						<td>
						<c:choose>
						<c:when test="${dto.gender eq 'M' }">
						남자
						</c:when>
						<c:when test="${dto.gender eq 'F' }">
						여자
						</c:when>
						</c:choose></td>
						<td>
						<c:choose>
						<c:when test="${dto.grade eq 0 }">
						일반회원
						</c:when>
						<c:when test="${dto.grade eq 2 }">
						운영자
						</c:when>
						<c:when test="${dto.grade eq 1 }">
						관리자
						</c:when>
						</c:choose></td>
						
						<td><fmt:parseDate value="${dto.wdate}" var="wdate" pattern="yyyy-mm-dd"/>   
                    	<fmt:formatDate value="${wdate}" pattern="yyyy/mm/dd"/></td>
						<td><fmt:parseDate value="${dto.ddate}" var="ddate" pattern="yyyy-mm-dd"/>   
                    	<fmt:formatDate value="${ddate}" pattern="yyyy/mm/dd"/></td>
						
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
<hr>
</div>

<%@ include file="../footer.jsp" %>


</body>
</html>








    