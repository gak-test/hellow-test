package com.gak.controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gak.dto.user.*;
import com.gak.service.user.*;
import java.util.*;


@Controller
@RequestMapping(value="/notice/")
public class NoticeController{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private NoticeService noticeService;
       
	private static final Logger log = LoggerFactory.getLogger(NoticeController.class);
	
	@GetMapping("nlist")
	public void nlist(Model model) {
		model.addAttribute("notice", noticeService.getNoticeList());
	}	
		
	@GetMapping("nView")
	public void nView(int notice_no, Model model) {
		
		model.addAttribute("notice", noticeService.getNotice(notice_no));

	}
	
		
	
	
	
	
}
