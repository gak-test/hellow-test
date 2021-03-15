package com.gak.controller.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gak.controller.admin.AdHomeController;
import com.gak.dto.user.*;
import com.gak.service.user.*;

import java.util.*;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping(value="/board/")
public class BoardController {
	       
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private FileService fileService;	
	
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
 
	
	@GetMapping("list")
	public void list(Model model) {
		model.addAttribute("board", boardService.getBoardList());
	}
	
	
	@GetMapping("regForm")
	public void regForm() {		
	}
	
	@PostMapping("register")
	public String register(Board board, MultipartFile files, HttpSession sess, RedirectAttributes rttr) {
//		log.info("제목:" + board.getTitle());
//		log.info("내용:" + board.getTitle());
//		
//		log.info("첨부파일 이름 : " + files.getOriginalFilename());
//		log.info("첨부파일 크기 : " + files.getSize());
//		log.info("첨부파일 타입 : " + files.getContentType());
		board.setId((String)sess.getAttribute("sess_id"));
		
		int bno = boardService.insertBoard(board);
		fileService.fileUpload(files, sess, bno);
		
		rttr.addFlashAttribute("bno", bno);
		
		return "redirect:/board/view";
	}
	
	
	@GetMapping("view")
	public void view(@ModelAttribute("bno") int bno, Model model) {
		//log.info("bno :" + bno);		
		model.addAttribute("board", boardService.getBoard(bno));

		//model.addAttribute("board", boardService.getBoard2(bno));
	}
	
	@PostMapping("modifyForm")
	public void modifyForm(Board board, UploadFile file, Model model) {
		
		model.addAttribute("board", board);
		model.addAttribute("uploadFile", file);
	}
		
	@PostMapping("modify")
	public String modify(Board board, MultipartFile files, HttpSession sess, RedirectAttributes rttr) {
		
		boardService.updateBoard(board);
		
		int bno = board.getBno();
		log.info("보드bno {}", bno);
		log.info("보드bno {}", bno);
		
		if(files != null && files.getSize() > 0) {
			fileService.fileUpload(files, sess, bno);
		}
		
		rttr.addFlashAttribute("bno", bno);
		return "redirect:/board/view";
	}
	
	@PostMapping("delete")
	public String delete(int bno, String saveFileName, HttpSession sess) {
		
		if(saveFileName != null) {
			//파일삭제
			String uploadPath = sess.getServletContext().getRealPath("resources/upload/");
			fileService.fileDel(uploadPath, saveFileName);
			
			 //파일 레코드삭제
			fileService.fileDelete(bno);
		}
		
		//보드삭제
		boardService.deleteBoard(bno);

		return "redirect:/board/list";
	}

	
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
//		if(cmd.equals("boardList.bo")) {
//			ArrayList<Board> b = board.getBoardList();
//			request.setAttribute("board", b);
//			goView(request,response,"/board/boardList.jsp");
//		} else if (cmd.equals("boardRegForm.bo")) {
//			goView(request,response,"/board/boardRegForm.jsp");		
//		} else if (cmd.equals("boardRegProc.bo")) {
//			
//			
//			int bno =board.insertBoard(request,response);
//			System.out.println(bno);
//			Board b2 = board.getBoard(bno);
//			
//			request.setAttribute("board", b2);
//			goView(request,response,"/board/boardDetailView.jsp");	
//			
//		} else if(cmd.equals("boardDetailView.bo")) {
//			int bno = Integer.parseInt(request.getParameter("bno"));
//			Board b2 = board.getBoard(bno);
//			request.setAttribute("board", b2);
//			goView(request,response,"/board/boardDetailView.jsp");
//		} else if(cmd.equals("boardModifyForm.bo")) {
//			
//			Board b = setBoard(request);
//			request.setAttribute("board", b);
//			goView(request,response,"/board/boardModifyForm.jsp");
//		} else if(cmd.equals("updateBoard.bo")) {
//			
//			Board b = setBoard(request);
//			board.updateBoard(b);
//			request.setAttribute("board", b);
//			goView(request,response,"/board/boardDetailView.jsp");			
//		} else if(cmd.equals("boardDel.bo")) {
//		    int rs = board.deleteBoard(Integer.parseInt(request.getParameter("bno")));
//		    
//		    request.setAttribute("delstat", rs);
//		    System.out.println("삭제여부 " + rs);
//		    goView(request, response, "/board/boardList.bo");
//			
//		}
//				
//	}

	
	
}
