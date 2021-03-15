package com.gak.service.admin;

import java.util.ArrayList;


import com.gak.dto.admin.*;

public interface AdNoticeService {
	
		//공지사항 리스트
		ArrayList<AdNotice> getNoticeList();
		
		//공지사항 등록
		int insertNotice(AdNotice adNotice);
		
		//공지사항 수정
		void updateNotice(AdNotice adNotice);
		
		//공지사항 삭제
		int deleteNotice(int notice_no);
		
		//공지사항 내용 확인
		AdNotice getNotice(int notice_no);
		
		//공지사항 조회
		AdNotice[] searchNotice(String criteria, String keyword);
				
}
