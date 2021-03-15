<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/mysql_conn.jsp" %>
<%@ page import="java.io.*" %>
<%
request.setCharacterEncoding("UTF-8");
String sno = request.getParameter("sno");

String sql="SELECT * FROM videofileUp WHERE sno = ? ";
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1, sno);
ResultSet rs =stmt.executeQuery();

rs.next();

ServletContext ctx = getServletContext();
String fileUploadPath = ctx.getRealPath("/upload");

File file = new File(fileUploadPath + File.separator + rs.getString("savefilename"));


if(file.exists()){
	//파일삭제
	if(file.delete()){
		//db에서 파일 삭제
		sql = "DELETE FROM videofileUp WHERE sno = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, sno);
		stmt.executeUpdate();
		out.print("1");
	} else {
		out.print("0");
	}
}
stmt.close();
conn.close();
%>