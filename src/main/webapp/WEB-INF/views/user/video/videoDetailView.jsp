<%-- <%@ page import="com.mysql.cj.jdbc.PreparedStatementWrapper"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="java.util.Date" %>
<%@ include file="/mysql_conn.jsp" %>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>  

<%
String id = (String)session.getAttribute("sess_id");

String seqno3 = request.getParameter("seqno3");

//조회수 증가
String sql = "UPDATE video SET count3 = IFNULL(count3,0) + 1 WHERE seqno3 = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, seqno3);
stmt.executeUpdate();

sql="SELECT * FROM video WHERE seqno3 = ?";
stmt = conn.prepareStatement(sql);
stmt.setString(1, seqno3);
ResultSet rs =stmt.executeQuery();
rs.next();

SimpleDateFormat sdf = new SimpleDateFormat("yy년MM월dd일 hh시mm분");
Date date = rs.getTimestamp("wdate3");

sql="SELECT * FROM videofileUp WHERE v_sno = ? ";
stmt = conn.prepareStatement(sql);
stmt.setString(1, seqno3);
ResultSet f_rs =stmt.executeQuery();
%>
    
<%@ include file="/common_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=ROOT %>/CSS/layout2.css">
<meta charset="UTF-8">
<title>게시판</title>
<script src="<%=ROOT %>/js/search_address.js"></script>
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

<%@ include file="/header.jsp" %>

<%@ include file="/menu.jsp" %>



<div class="content" style="width:100%; padding:50px;">
   <div class="main">
	<div class="card">
		<h2>■ 동영상 세부내용</h2>
		<hr>
		<div style="margin-top:20px">
			
			<label>제목:<%= rs.getString("title3") %></label>
			<label style="float:right">조회수:<%= rs.getString("count3") %></label>		
			<hr><br>		
			<label>작성자:<%= rs.getString("id3") %></label>
			<label style="float:right"><%=sdf.format(date) %></label>
			<hr><br>
				
			<label>내용 <br><%= rs.getString("content3") %></label>
			<hr><br>
			<label>첨부파일:
			<% 
			if(f_rs.next()){
					out.print(f_rs.getString("filename"));
				
				if(f_rs.getString("filetype").indexOf("image") != -1){
					out.print("<img width='100px' height='100px' src='" );
					out.print(f_rs.getString("filepath"));
					out.print(f_rs.getString("savefilename"));
					out.print("'>");
				} else{
					out.print("파일");
				}
			}				
				%>
				
			<%if(rs.getString("id3").equals(id)) {%>
			</label>
			<form name="videoModifybtn" method="post" action="videoModifyForm.jsp">
				<input type="hidden" name="seqno3" value="<%=rs.getString("seqno3")%>">
				<input type="submit" class="button" value="수정">
			</form>
			
			<form name="videoDelbtn" method="post" action="videoDel.jsp" onsubmit="return checkYesNo()">
				<input type="hidden" name="seqno3" value="<%=rs.getString("seqno3")%>">
				<input type="submit" style="float:right" value="삭제">
			</form>
			<%} %>
		</div>						
	</div>
	</div>

</div>

<%@ include file="/footer.jsp" %>
</body>
</html>








    