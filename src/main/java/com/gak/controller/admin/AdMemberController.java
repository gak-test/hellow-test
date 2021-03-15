package com.gak.controller.admin;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gak.dto.admin.*;
import com.gak.service.admin.*;


@Controller
//@RequestMapping(value="/admin/")
public class AdMemberController {

	@Autowired
	private AdMemberService ams;
	
	@PostMapping("admember/login")
	public String login(AdMember adMember,HttpSession sess, Model model){
		
		Map<String,String> map = ams.loginService(adMember);
		
		String viewPage = null;
		
		switch(map.get("stat")) {
		case "ok" : //로그인 성공, 세션값 설정
			sess.setAttribute("sess_id", adMember.getId());
			sess.setAttribute("sess_name", map.get("name"));
			viewPage = "home";
			
			break;				
		default : //id없는 경우, 패스워드 불일치
			model.addAttribute("loginStat", "loginfail");
			viewPage = "index";
	}
		return viewPage;
	}
	
//	private void doAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//
//		response.setContentType("text/html; charset=utf-8");		
//		request.setCharacterEncoding("utf-8");		
//		
//		PrintWriter out = response.getWriter();
//		//w.append("<HTML><BODY><H2>회원관리서비스</H2></body></html>");
//		
//		String uri = request.getRequestURI();
//		String cmd = uri.substring(uri.lastIndexOf("/")+1);
//				
//		System.out.println("URI : " + cmd);
//		String viewPage = null;
//				
//		if(cmd.equals("login.ame")) { //로그인 처리
//			String id = request.getParameter("id");
//			String pw = request.getParameter("pw");
//			Map<String, String> rs = ms.loginService(id, pw);
//			
//			switch(rs.get("stat")) {
//				case "ok" : //로그인 성공, 세션값 설정
//					HttpSession sess = request.getSession();
//					sess.setAttribute("sess_id", id);
//					sess.setAttribute("sess_name", rs.get("name"));
//					int cnt=ms.cntMember(); 
//					int dcnt=ms.delcntMember();
//					request.setAttribute("cnt", cnt);
//					request.setAttribute("dcnt", dcnt);
//					goView(request, response, "home.jsp");	
//					
//					break;				
//				default : //id없는 경우, 패스워드 불일치
//					request.setAttribute("loginStat", 1);
//					goView(request, response, "index.jsp");		
//			}
//			
//		} else if(cmd.equals("logout.ame")) { //로그아웃
//			//세션파괴하고 루트분기
//			HttpSession sess= request.getSession();
//			sess.invalidate();
//			response.sendRedirect("index.jsp");	
//		} else if(cmd.equals("home.ame")) { //홈버튼 누를시 메인화면
//			int cnt=ms.cntMember(); 
//			int dcnt=ms.delcntMember();
//			request.setAttribute("cnt", cnt);
//			request.setAttribute("dcnt", dcnt);
//			goView(request, response, "home.jsp");
//		}
//		  else if(cmd.equals("memberList.ame")) { //회원관리 버튼 누를시 리스트 출력
//			ArrayList<AdMember> m = ms.getMemberList();
//			request.setAttribute("member", m);
//			goView(request, response, "memberlist.jsp");	
//		} else if(cmd.equals("search.ame")) { //검색 버튼 누를시 리스트 해당 리스트 출력
//			String name = request.getParameter("searchbar");
//			String grade = request.getParameter("msearch");
//			System.out.println(grade);
//			ArrayList<AdMember> m = ms.gradeMember(name, grade);
//			request.setAttribute("member", m);
//			request.setAttribute("grade", grade);
//			request.setAttribute("name", name);
//			goView(request, response, "memberlist.jsp");
//			
//		} else if(cmd.equals("delmemberlist.ame")) {
//			ArrayList<AdMember> m = ms.getdelMemberList();
//			request.setAttribute("member", m);
//			goView(request, response, "delmemberlist.jsp");
//		} else if(cmd.equals("delsearch.ame")) {
//			String ddate = request.getParameter("searchbar");
//			ArrayList<AdMember> m = ms.ddateMember(ddate);
//			request.setAttribute("member", m);
//			request.setAttribute("ddate", ddate);
//			
//			System.out.println(ddate);
//			goView(request, response, "searchdelmemberlist.jsp");
//		}else if(cmd.equals("delete.ame")) {
//			String ddate = request.getParameter("del");
//			System.out.println(ddate);
//			ms.ddatedelMember(ddate);
//			goView(request, response, "delmemberlist.jsp");
//			
//		}
//		
//	}

	
	
	
}
