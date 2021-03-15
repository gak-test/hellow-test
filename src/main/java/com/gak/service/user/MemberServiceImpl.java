package com.gak.service.user;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gak.dao.user.*;
import com.gak.dto.user.*;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired
	MemberDao mdao;
	
	@Override
	public int insert(Member member) {		
		
		String email = member.getEid() + "@" + member.getDomain();
		member.setEmail(email);
		
		int rs = mdao.insertMember(member);
		return rs;
	}
	
	
	@Override
	public int update(Member member) {
		
		String email = member.getEid() + "@" + member.getDomain();
		member.setEmail(email);
		
		int rs = mdao.updateMember(member);
		
		
		return rs;
				
	}

	@Override
	public int delete(String id) {		
		return mdao.deleteMember(id);
	}

	@Override
	public Member[] select() {		
		return mdao.getMemberList();
	}

	@Override
	public Map<String, String> loginService(Member member) {					
		return mdao.loginProc(member);		
	}

	@Override
	public int idDoubleCheck(String id) {
		
		return mdao.idDoubleCheck(id);
	}

	@Override
	public Member getMember(String id) {
		
		return mdao.getMember(id);
	}

}
