package com.gak.domain;

import com.gak.dto.user.UploadFile;

public class BoardFileVo {
	private BoardVo boardvo;
	private UploadFile uploadFile;
	
	public BoardVo getBoardvo() {
		return boardvo;
	}
	public void setBoardvo(BoardVo boardvo) {
		this.boardvo = boardvo;
	}
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	
}
