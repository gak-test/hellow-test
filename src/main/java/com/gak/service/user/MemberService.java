package com.gak.service.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gak.dto.user.*;

public interface MemberService {
	
	//회원등록
	int insert(Member member);
	
	//회원수정
	int update(Member member);
	
	//회원삭제
	int delete(String id);
	
	//회원조회
	Member[] select();
	
	//1인 회원정보 출력
	Member getMember(String id);
	
	//로그인 
	Map<String, String> loginService(Member member);
	
	//아이디 중복체크
	int idDoubleCheck(String id);

	
}
