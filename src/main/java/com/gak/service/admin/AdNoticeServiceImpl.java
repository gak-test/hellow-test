package com.gak.service.admin;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gak.dao.admin.*;
import com.gak.dto.admin.*;
@Service
public class AdNoticeServiceImpl implements AdNoticeService {
	
	private static final Logger log = LoggerFactory.getLogger(AdNoticeServiceImpl.class);
	
	@Autowired
	private AdNoticeDao ndao;
	
	@Override
	public ArrayList<AdNotice> getNoticeList() {
		
		return ndao.getNoticeList();
	}

	@Override
	public int insertNotice(AdNotice adNotice) {	

		return ndao.insertNotice(adNotice);
		}


	@Override
	public void updateNotice(AdNotice adNotice) {		
		ndao.updateNotice(adNotice);
	}

	@Override
	public int deleteNotice(int notice_no) {
		
		return ndao.deleteNotice(notice_no);
	}

	@Override
	public AdNotice getNotice(int notice_no) {
		
		return ndao.getNotice(notice_no);
	}

	@Override
	public AdNotice[] searchNotice(String criteria, String keyword) {
		
		return null;
	}

	
	

}
