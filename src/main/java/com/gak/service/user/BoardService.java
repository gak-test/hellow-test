package com.gak.service.user;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.gak.domain.BoardFileVo;
import com.gak.domain.BoardVo;
import com.gak.dto.user.*;


public interface BoardService {
	
	
	//게시판 리스트
	ArrayList<Board> getBoardList();
	
	//게시물 등록
	int insertBoard(Board board);
	
	//게시물 수정
	void updateBoard(Board board);
	
	//게시물 삭제
	int deleteBoard(int bno);
	
	//게시물 내용 확인
	Board getBoard(int bno);
	

	//public BoardFileVo getBoard2(int bno);
	
	//게시물 조회
	Board[] searchBoard(String criteria, String keyword);
	
	
	
}
