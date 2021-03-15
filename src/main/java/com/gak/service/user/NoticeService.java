package com.gak.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gak.dto.user.*;

public interface NoticeService {
	
		//공지사항 리스트
		ArrayList<Notice> getNoticeList();
		
		//공지사항 내용 확인
		Notice getNotice(int notice_no);
		
		//공지사항 조회
		Notice[] searchNotice(String criteria, String keyword);
				
}
