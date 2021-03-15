package com.gak.service.user;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gak.dto.user.*;

public interface BoardService2 {
	
	
	//게시판 리스트
	ArrayList<Board2> getBoardList2();
	
	//게시물 등록
	int insertBoard2(HttpServletRequest request, HttpServletResponse response);
	
	//게시물 수정
	void updateBoard2(Board2 board2);
	
	//게시물 삭제
	int deleteBoard2(int bno2);
	
	//게시물 내용 확인
	Board2 getBoard2(int bno2);
	
	//게시물 조회
	Board[] searchBoard(String criteria, String keyword);
	
	
	
}
