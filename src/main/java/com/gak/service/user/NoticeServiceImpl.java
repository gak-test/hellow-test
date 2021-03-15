package com.gak.service.user;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gak.dao.user.*;
import com.gak.dto.user.*;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	private static final Logger log = LoggerFactory.getLogger(NoticeServiceImpl.class);
	
	@Autowired
	private NoticeDao ndao;
	
	@Override
	public ArrayList<Notice> getNoticeList() {
		
		return ndao.getNoticeList();
	}

	
	@Override
	public Notice getNotice(int notice_no) {
		
		return ndao.getNotice(notice_no);
	}

	@Override
	public Notice[] searchNotice(String criteria, String keyword) {
		
		return null;
	}


	




	
	

}
