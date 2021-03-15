package com.gak.service.admin;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gak.dao.admin.*;
import com.gak.dto.admin.*;

@Service
public class AdMemberServiceImpl implements AdMemberService {
	
	@Autowired
	private AdMemberDao mdao;	
	
	@Override
	public ArrayList<AdMember> getMemberList() {
		
		return mdao.getMemberList();
	}
	
	@Override
	public ArrayList<AdMember> getdelMemberList() {
		return mdao.getdelMemberList();
	}

	@Override
	public Map<String, String> loginService(AdMember adMember) {					
		return mdao.loginProc(adMember);		
	}


	@Override
	public int cntMember() {
		return mdao.cntMember();
	}


	@Override
	public int delcntMember() {
		return mdao.delcntMember();
	}

	@Override
	public ArrayList<AdMember> gradeMember(String name, String grade) {
		return mdao.gradeMember(name,grade);
	}

	@Override
	public ArrayList<AdMember> ddateMember(String ddate) {
		return mdao.ddateMember(ddate);
	}

	@Override
	public int ddatedelMember(String ddate) {
		return mdao.ddatedelMember(ddate);
		
	}

	


	
	
}
