<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix = "fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
    

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="../css/layout2.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/formCheck.js">
</script>
</head>
<body>

<%@ include file="../header.jsp" %>

<%@ include file="../menu.jsp" %>



<div class="content" style="width:100%; padding:50px;">
   <div class="main">
	<div class="card">
<%-- 		<c:set value="${member}" var="dto"/> --%>
		<form name="memReg" method="post" action="/member/modify" onsubmit="return memberFormCheck()">
		 <fieldset><legend align="center">마이페이지 수정</legend>
			<input type="hidden" name="id" value="${member.id }">
			<label>아이디</label>
			${member.id}
			<br>
			
			<input type="password" name="pw" maxlength="15" placeholder="비밀번호">
			<p>* 최소 5자리~15자리</p><p id="pw_error" style="color:red"></p>
			
			<input type="text" name="name" placeholder="이름" value="${member.name}"required><br>
					
			<input type="text" name="eid" placeholder="이메일ID" value="${fn:substring(member.email,0,fn:indexOf(member.email,'@')) }"><a id="eid_error"></a>@
			<input type="text" name="domain" placeholder="이메일" value="${fn:substring(member.email,fn:indexOf(member.email,'@')+1,fn:length(member.email)) }">
			<select name="sel_domain" onchange="inputDomain()">
			
				<option value="">직접 입력</option>
				<option value="gmail.com">gmail.com</option>
  				<option value="naver.com">naver.com</option>
  				<option value="hanmail.net">hanmail.net</option>
			</select><br>
			
			<fieldset style="width:200px">
			<legend align="center">취미</legend>
				<c:forEach var="h" items="${hobby}">
					<input type="checkbox" name="hobby" value="${h.codeVal}"
					
					<c:forEach var="myhobby" items="${member.hobby}">
						<c:if test="${myhobby == h.codeVal }"> checked</c:if>
						
					</c:forEach>
					>${h.codeName}					
				</c:forEach>
		
			</fieldset><br>
			<fieldset style="width:140px">
			<legend align="center">성별</legend>
				<input type="radio" name="gender" value="M" 
				<c:if test="${member.gender == 'M'}"> checked </c:if> >남자
				
				<input type="radio" name="gender" value="F" 
				<c:if test="${member.gender == 'F'}"> checked </c:if> >여자
				
			</fieldset><br>
						
			<script src="/js/search_address.js"></script>
			<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
			<input type="text" name="post" size="5" id="postcode"  value="${member.post}" readonly>
			<input type="button" onclick="search_address()" value="우편번호 찾기"><br>
			<input type="text" name="addr" value="${member.addr1}" placeholder="주소" id="address" readonly><br>
			<input type="text" name="addr2" value="${member.addr2}" placeholder="상세주소입력" id="detailAddress">
			<input type="text" name="addr3" value="${member.addr3}" placeholder="참고항목" id="extraAddress" readonly>
			<br>
			
			<textarea name="intro" placeholder="자기소개(100글자이내)">
			${member.intro }
			</textarea><br>
			
			<input type="submit" value="수정" >
			<input type="button" value="회원탈퇴" style="float:right" onclick="location.href='/member/memDel'">
		</fieldset>
		<%@ include file="../footer.jsp" %>
		</form>
		
		
	</div>
	</div>
	
</div>

</body>
</html>








    