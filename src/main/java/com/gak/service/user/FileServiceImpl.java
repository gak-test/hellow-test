package com.gak.service.user;


import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.web.multipart.MultipartFile;

import com.gak.dao.user.*;
import com.gak.dto.user.*;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	FileDao fileDao;
	
	private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Override
	public void fileUpload(MultipartFile files, HttpSession sess, int bno) {
		
		UploadFile uploadFile = new UploadFile();	

		if(files != null){				
			
			long fileSize = files.getSize();
			
			String uploadPath = sess.getServletContext().getRealPath("resources/upload/");							
			log.info("파일 업로드 경로: " + uploadPath);
									
			String fileName = files.getOriginalFilename();
			
			UUID uid = UUID.randomUUID();
			
			String saveFileName = uid.toString() + "_" + fileName;
			
			File saveFile = new File(uploadPath, saveFileName);
			
			try {
				files.transferTo(saveFile);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
						
			
			uploadFile.setFileName(fileName);
			uploadFile.setSaveFileName(saveFileName);
			uploadFile.setFileSize(Long.toString(fileSize));
			uploadFile.setFileType(files.getContentType());
			uploadFile.setFilePath(uploadPath);
			uploadFile.setBoardNo(Integer.toString(bno));
			
			fileDao.insertFile(uploadFile);												
			
		}						

	}

		

	@Override
	public void fileDel(String uploadPath, String saveFileName) {		
		
		System.out.println("업로드경로:" + uploadPath);
		System.out.println("파일명:" + saveFileName);
		File file = new File(uploadPath + saveFileName);
				 
		file.delete();
	}


	@Override
	public int fileDelete(int bno) {
		
		return  fileDao.deletFile(bno);
	}

	
}
