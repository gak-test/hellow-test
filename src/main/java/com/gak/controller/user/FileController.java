package com.gak.controller.user;


import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.*;
import org.springframework.http.MediaType;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.gak.service.user.FileService;



@Controller
public class FileController {
private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileService fileService;
	
	
	@PostMapping("/deleteFile")
	@ResponseBody
	public ResponseEntity<String> deleteFile(int bno, String saveFilename, HttpSession sess){
		
		//파일 테이블 레코드 삭제
		int rs = fileService.fileDelete(bno);
		
		String out = null;
		
		if(rs > 0) {
			String fileUploadPath = sess.getServletContext().getRealPath("/resources/upload/");
			
			fileService.fileDel(fileUploadPath, saveFilename);
			out = "1";
		} else {
			out = "0";
		}
		
		return new ResponseEntity<String>(out, HttpStatus.OK);
	}
	
	
      
	@PostMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String savefilename, String filepath){
		
		log.info("savefilename : " + filepath + savefilename);
				
		Resource resource = new FileSystemResource(filepath + savefilename);		
		log.info("resource : " + resource);
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
		
		String resourceName = resource.getFilename();		
		log.info("resourceName : " + resourceName);
		//uuid제거
		resourceName = resourceName.substring(resourceName.indexOf("_")+1);
		
		HttpHeaders headers  = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachement; filename="+ new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

}
