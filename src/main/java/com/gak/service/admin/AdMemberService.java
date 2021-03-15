package com.gak.service.admin;

import java.util.ArrayList;
import java.util.Map;

import com.gak.dto.admin.*;


public interface AdMemberService {
	
	
	//회원조회
	ArrayList<AdMember> getMemberList();
	//로그인 
	Map<String, String> loginService(AdMember adMember);
	
	//탈퇴회원 조회
	ArrayList<AdMember> getdelMemberList();
	
	//회원등록수
	int cntMember();
	
	//검색기능
	ArrayList<AdMember> gradeMember(String name,String grade);
	
	//탈퇴회원 검색기능
	ArrayList<AdMember> ddateMember(String ddate);
	
	//탈퇴회원 날짜기준 삭제
	int ddatedelMember(String ddate);
	
	//탈퇴회원등록수
	int delcntMember();
	
}
