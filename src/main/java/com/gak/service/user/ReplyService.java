package com.gak.service.user;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gak.dto.user.*;

public interface ReplyService {
	//게시판 리스트
		ArrayList<Reply> getReplyList();
		
		//게시물 등록
		int insertReply(HttpServletRequest request, HttpServletResponse response);
		
		//게시물 수정
		void updateReply(Reply reply);
		
		//게시물 삭제
		int deleteReply(int rno);
}
