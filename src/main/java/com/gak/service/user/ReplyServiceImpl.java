package com.gak.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gak.dao.user.*;
import com.gak.dto.user.*;

public class ReplyServiceImpl implements ReplyService {

	@Override
	public ArrayList<Reply> getReplyList() {
		return null;
	}

	@Override
	public int insertReply(HttpServletRequest request, HttpServletResponse response) {
		
		return 0;
	}

	@Override
	public void updateReply(Reply reply) {

	}

	@Override
	public int deleteReply(int rno) {
		return 0;
	}

}
