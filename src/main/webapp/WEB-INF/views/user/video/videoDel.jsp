<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>    
<%@ include file="/mysql_conn.jsp" %>
<%
request.setCharacterEncoding("UTF-8");

String seqno3 = request.getParameter("seqno3");
String sql = "SELECT * FROM videofileup WHERE v_sno = ?";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, seqno3);
ResultSet rs = stmt.executeQuery();

//첨부파일 삭제
while(rs.next()){
	ServletContext ctx = getServletContext();
	String fileUploadPath = ctx.getRealPath("/upload");

	File file = new File(fileUploadPath + File.separator + rs.getString("savefilename"));

	System.out.println(file);
	if(file.exists()){
		//파일삭제
		if(file.delete()){
			//db에서 파일 삭제
			sql = "DELETE FROM videofileUp WHERE v_sno = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, seqno3);
			stmt.executeUpdate();		
		} 
	}
}
	

String sql2 = "DELETE FROM video WHERE seqno3 = ?";

PreparedStatement stmt5 = conn.prepareStatement(sql2);
stmt5.setString(1, seqno3);

System.out.println(stmt5);

int rs2 = stmt5.executeUpdate();

if(rs2 > 0){
	response.sendRedirect("videoList.jsp");	
}

stmt.close();
stmt5.close();
conn.close();
%>