package com.gak.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.gak.dto.user.*;
import com.gak.service.user.*;
import java.util.*;


@WebServlet(urlPatterns = {"*.bo2"})
public class BoardController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BoardService2 board = new BoardServiceImpl2();
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}


	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setContentType("text/html; charset=utf-8");		
		request.setCharacterEncoding("utf-8");		
		
		
		String uri = request.getRequestURI();
		String cmd = uri.substring(uri.lastIndexOf("/")+1);
				
		System.out.println("URI : " + cmd);
		
		
		//게시판 리스트 출력
		if(cmd.equals("boardList2.bo2")) {
			ArrayList<Board2> b = board.getBoardList2();
			request.setAttribute("board2", b);
			goView(request,response,"/board2/boardList2.jsp");
		} else if (cmd.equals("boardRegForm.bo2")) {
			goView(request,response,"/board2/boardRegForm2.jsp");		
		} else if (cmd.equals("boardRegProc.bo2")) {
					
			int bno2 =board.insertBoard2(request,response);
			System.out.println(bno2);
			Board2 b2 = board.getBoard2(bno2);
			
			request.setAttribute("board2", b2);
			goView(request,response,"/board2/boardDetailView2.jsp");	
			
		} else if(cmd.equals("boardDetailView.bo2")) {
			int bno = Integer.parseInt(request.getParameter("bno2"));
			Board2 b2 = board.getBoard2(bno);
			request.setAttribute("board2", b2);
			goView(request,response,"/board2/boardDetailView2.jsp");
		} else if(cmd.equals("boardModifyForm.bo2")) {
			
			Board2 b = setBoard2(request);
			request.setAttribute("board2", b);
			goView(request,response,"/board2/boardModifyForm2.jsp");
		} else if(cmd.equals("updateBoard.bo2")) {
			
			Board2 b = setBoard2(request);
			board.updateBoard2(b);
			request.setAttribute("board2", b);
			goView(request,response,"/board2/boardDetailView2.jsp");			
		} else if(cmd.equals("boardDel.bo2")) {
		    int rs = board.deleteBoard2(Integer.parseInt(request.getParameter("bno2")));
		    
		    request.setAttribute("delstat", rs);
		    System.out.println("삭제여부 " + rs);
		    goView(request, response, "/board2/boardList2.bo2");
			
		}
				
	}
	
	private Board2 setBoard2(HttpServletRequest request) {
		Board2 b = new Board2();
		b.setBno2(Integer.parseInt(request.getParameter("bno2")));
		b.setTitle2(request.getParameter("title2"));
		b.setContent2(request.getParameter("content2"));
		b.setCount2(Integer.parseInt(request.getParameter("count2")));
		b.setWdate2(request.getParameter("wdate2"));
		b.setId(request.getParameter("writer"));
		request.setAttribute("board2", b);
		return b;
	}


	private void goView(HttpServletRequest request, HttpServletResponse response, String viewPage) throws ServletException, IOException {		
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	
	
}
