<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.fileupload.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>  
<%@ include file="/mysql_conn.jsp" %>
<%
request.setCharacterEncoding("UTF-8");

// 	String fileUploadPath = "d:\\Gak\\workspace\\myweb\\WebContent\\upload";
	ServletContext ctx = getServletContext();
	String fileUploadPath = ctx.getRealPath("/upload");
	
	DiskFileUpload upload = new DiskFileUpload();
	List items = upload.parseRequest(request);
	Iterator params = items.iterator();
	String CHARSET = "utf-8";

	String title3 = null;
	String content3 = null;
	String fileName = null;
	String saveFileName = null;
	long fileSize = 0;
	String contentType = null;
	String v_sno = null;

	//hasNext() : 읽어올 요소가 남아있는 지 확인하는 메소드 있으면 true 없으면 false;
	//next(): 다음 데이터를 반환
	//remove(): next()로 읽어온 요소를 삭제
	while(params.hasNext()){
		FileItem Item = (FileItem)params.next();          //타입이 일치하지않아서 강제 타입변환
		if(Item.isFormField()){       //파일 업로드만 제외하고 true 값이 나온다
			System.out.printf("필드이름 : %s, 필드 값 : %s\n", Item.getFieldName(), Item.getString(CHARSET));
		
			if(Item.getFieldName().equals("title3")){ title3 = Item.getString(CHARSET); }
			if(Item.getFieldName().equals("content3")){ content3 = Item.getString(CHARSET); }
			if(Item.getFieldName().equals("seqno3")){ v_sno = Item.getString(CHARSET); }
		
		}else{
		//파일업로드
			fileSize = Item.getSize();
			System.out.println("파일 사이즈 : " + fileSize);
			
		if(fileSize > 0){
	    	fileName = Item.getName();
			System.out.println("파일이름 : " + fileName);
			
			//중복된 파일을 업로드 하지 않기 위해 uid값 생성
			UUID uid = UUID.randomUUID();
			
			int idx = fileName.lastIndexOf("."); //찾고자하는 문자가 마지막에 나타나는거
			String split_fileName = fileName.substring(0, idx);
			String split_extension = fileName.substring(idx+1);
				
		    saveFileName = split_fileName + "_" + uid + "." +split_extension;
			
			System.out.printf("저장될 파일명: %s, idx값: %d ", saveFileName,idx);
			
			File file = new File(fileUploadPath + File.separator + saveFileName);
			
			//파일 중복 검사
			if(file.exists()){		//존재하면 true 존재하지않으면 false
				System.out.println("이미 업로드 된 파일 입니다.");
				
			}
			
			Item.write(file); 
		
			contentType = Item.getContentType();
			System.out.println("파일 컨텐츠 유형 : " + contentType);
		
		

		}
	} //isFormFiled() if 문
	
}//while 문
//게시물 저장
String id3 = (String)session.getAttribute("sess_id");

String sql = "UPDATE video  SET title3 =? , content3=? WHERE seqno3=?";

PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setString(1,title3);
stmt.setString(2,content3);
stmt.setString(3,v_sno);


System.out.println(stmt);

int rs = stmt.executeUpdate();


if(rs > 0){

	//file을 db에 저장
	if(fileSize>0){		
		sql = "INSERT INTO videofileup(filename,savefilename,filesize,filetype,filepath,v_sno)";
		sql += "VALUES (?,?,?,?,?,?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, fileName);
		stmt.setString(2, saveFileName);
		stmt.setLong(3, fileSize);
		stmt.setString(4, contentType);
		stmt.setString(5, "/myproject_admin/upload/");
		stmt.setString(6, v_sno);
		stmt.executeUpdate();
		
	}
	response.sendRedirect("videoDetailView.jsp?seqno=" + v_sno);	
	
}






stmt.close();
conn.close();



%>