package com.gak.service.user;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gak.controller.user.BoardController;
import com.gak.dao.user.*;
import com.gak.domain.BoardFileVo;
import com.gak.domain.BoardVo;
import com.gak.dto.user.*;
import com.gak.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Autowired
	private BoardDao boardDao;
		
	@Autowired
	private BoardMapper boardMapper;
	
	
	@Override
	public ArrayList<Board> getBoardList() {
		
		return boardDao.getBoardList();
		//return boardMapper.getList();
	}
	
	

	@Override
	public int insertBoard(Board board) {				
		return boardDao.insertBoard(board);
	}	

	@Override
	public void updateBoard(Board board) {			
		boardDao.updateBoard(board);		
	}

	@Override
	public int deleteBoard(int bno) {						
		return boardDao.deleteBoard(bno);
	}

	@Override
	public Board getBoard(int bno) {
		
		return boardDao.getBoard(bno);
	}

//	@Override
//	public BoardFileVo getBoard2(int bno) {
//		
//		return boardMapper.getBoard(bno);
//	}
	
	
	@Override
	public Board[] searchBoard(String criteria, String keyword) {
		
		return null;
	}

}
