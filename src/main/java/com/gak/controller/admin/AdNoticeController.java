package com.gak.controller.admin;
//
//import java.io.IOException;
//
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.gak.dto.admin.*;
//import com.gak.service.admin.*;
//import java.util.*;
//
//
//@WebServlet(urlPatterns = {"*.ano"})
//public class AdNoticeController extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	
//	private AdNoticeService notice = new AdNoticeServiceImpl();
//       
// 
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doAction(request, response);		
//	}
//
//	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doAction(request, response);
//	}
//
//
//	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		
//		response.setContentType("text/html; charset=utf-8");		
//		request.setCharacterEncoding("utf-8");		
//		
//		
//		String uri = request.getRequestURI();
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);
//				
//		System.out.println("URI : " + cmd);
//		
//		
//		//게시판 리스트 출력
//		if(cmd.equals("noticelist.ano")) {
//			ArrayList<AdNotice> n = notice.getNoticeList();
//			request.setAttribute("notice", n);
//			goView(request,response,"/notice/notice.jsp");
//		} else if (cmd.equals("noticeRegForm.ano")) {
//			goView(request,response,"/notice/noticeRegForm.jsp");		
//		} else if (cmd.equals("noticeRegProc.ano")) {
//			
//			AdNotice n = setNotice(request);
//			int notice_no =notice.insertNotice(n);
//			AdNotice n2 = notice.getNotice(notice_no);
//			
//			request.setAttribute("notice", n2);
//			goView(request,response,"/notice/noticeDetailView.jsp");	
//			
//		} else if(cmd.equals("noticeDetailView.ano")) {
//			int notice_no = Integer.parseInt(request.getParameter("notice_no"));
//			AdNotice n2 = notice.getNotice(notice_no);
//			request.setAttribute("notice", n2);
//			goView(request,response,"/notice/noticeDetailView.jsp");
//		} else if(cmd.equals("noticeModifyForm.ano")) {
//			
//			AdNotice n = setNotice(request);
//			request.setAttribute("notice", n);
//			goView(request,response,"/notice/noticeModifyForm.jsp");
//		} else if(cmd.equals("updatenotice.ano")) {
//			
//			AdNotice n = setNotice(request);
//			notice.updateNotice(n);
//			request.setAttribute("notice", n);
//			goView(request,response,"/notice/noticeDetailView.jsp");			
//		} else if(cmd.equals("noticeDel.ano")) {
//		    int rs = notice.deleteNotice(Integer.parseInt(request.getParameter("notice_no")));
//		    
//		    request.setAttribute("delstat", rs);
//		    System.out.println("삭제여부 " + rs);
//		    goView(request, response, "/notice/noticeList.ano");
//			
//		}
//				
//	}
//	
//	private AdNotice setNotice(HttpServletRequest request) {
//		AdNotice n = new AdNotice();
//		n.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
//		n.setTitle(request.getParameter("title"));
//		n.setContent(request.getParameter("content"));
//		n.setOdate(request.getParameter("odate"));
//		n.setWdate(request.getParameter("wdate"));
//		request.setAttribute("notice", n);
//		return n;
//	}
//
//
//	private void goView(HttpServletRequest request, HttpServletResponse response, String viewPage) throws ServletException, IOException {		
//		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
//		rd.forward(request, response);
//	}
//	
//	
//}
