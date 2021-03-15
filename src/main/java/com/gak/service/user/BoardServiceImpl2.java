package com.gak.service.user;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;

import com.gak.dao.user.*;
import com.gak.dto.user.*;
public class BoardServiceImpl2 implements BoardService2 {

	private BoardDao2 dao = new BoardDao2();
	
	@Override
	public ArrayList<Board2> getBoardList2() {
		
		return dao.getBoardList2();
	}

	@Override
	public int insertBoard2(HttpServletRequest request, HttpServletResponse response) {
		String CHARSET = "utf-8";

		Board2 b = new Board2();
		b.setId((String)request.getSession().getAttribute("sess_id"));
		UploadFile f = new UploadFile();
		
		ServletContext ctx = request.getSession().getServletContext();
		String fileUploadPath = ctx.getRealPath("/upload");
		
		
		
		try {
			
			DiskFileUpload upload = new DiskFileUpload();		
			List<FileItem> items = upload.parseRequest(request);
			for(FileItem item : items) {
				//FileItem Item = (FileItem)params.next();          //타입이 일치하지않아서 강제 타입변환
				if(item.isFormField()){       //파일 업로드만 제외하고 true 값이 나온다
					System.out.printf("필드이름 : %s, 필드 값 : %s\n", item.getFieldName(), item.getString(CHARSET));
					
					if(item.getFieldName().equals("title2")){ 
						b.setTitle2(item.getString(CHARSET));
						}
					if(item.getFieldName().equals("content2")){ 
						b.setContent2(item.getString(CHARSET)); 
						}
					
					
				}else{
					//파일업로드
					long fileSize = item.getSize();
					System.out.println("업로드 파일 사이즈 : " + fileSize);
					
					if(fileSize > 0){
				    	String fileName = item.getName();
						System.out.println("업로드 파일이름 : " + fileName);
						
						//중복된 파일을 업로드 하지 않기 위해 uid값 생성
						UUID uid = UUID.randomUUID();
						
						int idx = fileName.lastIndexOf("."); //찾고자하는 문자가 마지막에 나타나는거
						String split_fileName = fileName.substring(0, idx);
						String split_extension = fileName.substring(idx+1);
							
					    String saveFileName = split_fileName + "_" + uid + "." +split_extension;
						
						System.out.printf("저장될 파일명: %s, idx값: %d ", saveFileName,idx);
						
						File file = new File(fileUploadPath + File.separator + saveFileName);
						
						
						item.write(file); 
					
					
						f.setFileName(fileName);
						f.setSaveFileName(saveFileName);
						f.setFileSize(Long.toString(fileSize));
						f.setFileType(item.getContentType());
						f.setFilePath(fileUploadPath);

					}
				} //isFormFiled() if 문
				
			}//for 문
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//hasNext() : 읽어올 요소가 남아있는 지 확인하는 메소드 있으면 true 없으면 false;
		//next(): 다음 데이터를 반환
		//remove(): next()로 읽어온 요소를 삭제
		//while(params.hasNext()){
				
		
		return dao.insertBoard2(b,f);
	}

	

	@Override
	public void updateBoard2(Board2 board2) {
		
		dao.updateBoard2(board2);
	}

	@Override
	public int deleteBoard2(int bno2) {
		
		return dao.deleteBoard2(bno2);
	}

	@Override
	public Board2 getBoard2(int bno2) {
		
		return dao.getBoard2(bno2);
	}

	@Override
	public Board[] searchBoard(String criteria, String keyword) {
		
		return null;
	}

	

}
