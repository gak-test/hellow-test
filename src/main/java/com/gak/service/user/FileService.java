package com.gak.service.user;


import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.springframework.web.multipart.MultipartFile;

import com.gak.dto.user.*;



public interface FileService {
	
	public void fileUpload(MultipartFile files, HttpSession sess, int bno);	
	
	public void fileDel(String uploadPath, String saveFileName);
	
	public int fileDelete(int fno);
	
}
