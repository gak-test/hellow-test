package com.gak.dao.user;

import java.sql.*;
import java.util.*;

import com.gak.dto.user.*;
import oracle.jdbc.OracleTypes;

public class BoardDao2 {
	
	final Connection dbconn = OracleConn.getInstance().getConnect();
	
	public ArrayList<Board2> getBoardList2() {
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Board2> list = new ArrayList<Board2>();
		
		String sql = "{call p_getBoardList2(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.registerOutParameter(1,OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(1);
			
			while(rs.next()) {
				Board2 b = new Board2();
				b.setBno2(rs.getInt("board_no2"));
				b.setTitle2(rs.getString("title2"));
				b.setContent2(rs.getString("content2"));
				b.setCount2(rs.getInt("cnt2"));
				b.setOpen2(rs.getString("isopen2"));
				b.setWdate2(rs.getString("wdate2"));
				b.setId(rs.getString("member_id"));
				
				list.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int insertBoard2(Board2 board2,UploadFile file) {
		CallableStatement stmt = null;
		String sql = "{call p_insertBoard2(?,?,?,?,?,?,?,?,?)}";
		int rs = 0;
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, board2.getTitle2());
			stmt.setString(2, board2.getContent2());
			stmt.setString(3, board2.getId());
			stmt.setString(4, file.getFileName());
			stmt.setString(5, file.getSaveFileName());
			stmt.setString(6, file.getFileSize());
			stmt.setString(7, file.getFileType());
			stmt.setString(8, file.getFilePath());
									
			stmt.registerOutParameter(9,OracleTypes.INTEGER);
			stmt.executeUpdate();
			
			rs = stmt.getInt(9);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			System.out.println(rs);
		return rs;
	}

	public Board2 getBoard2(int bno2) {
		CallableStatement stmt = null;
		ResultSet rsBoard = null;
		ResultSet rsFile = null;
		int exists = 0;
		
		Board2 b = new Board2();
		
		String sql = "{call p_getBoard2(?,?,?,?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setInt(1, bno2);
			stmt.registerOutParameter(2,OracleTypes.CURSOR);
			stmt.registerOutParameter(3,OracleTypes.CURSOR);
			stmt.registerOutParameter(4,OracleTypes.INTEGER);
			stmt.executeQuery();
			
			rsBoard = (ResultSet)stmt.getObject(2);
			rsFile = (ResultSet)stmt.getObject(3);
			exists = stmt.getInt(4);
			
			if(rsBoard.next()) {
				
				b.setBno2(rsBoard.getInt("board_no2"));
				b.setTitle2(rsBoard.getString("title2"));
				b.setContent2(rsBoard.getString("content2"));
				b.setCount2(rsBoard.getInt("cnt2"));
				b.setOpen2(rsBoard.getString("isopen2"));
				b.setWdate2(rsBoard.getString("wdate2"));
				b.setId(rsBoard.getString("member_id"));
				
				if(rsFile.next()) {
					UploadFile f = new UploadFile();
					f.setFileNo(rsFile.getString("file_no"));
					f.setFileName(rsFile.getString("filename"));
					f.setFilePath(rsFile.getString("filepath"));
					f.setFileSize(rsFile.getString("filesize"));
					f.setFileType(rsFile.getString("filetype"));
					f.setSaveFileName(rsFile.getString("savefilename"));
					
					b.setUpfile(f);
				}
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public void updateBoard2(Board2 board2) {
		CallableStatement stmt = null;
		int rs = 0;
		
		String sql = "{call p_updateBoard2(?,?,?)}";
		try {
			stmt = dbconn.prepareCall(sql);			
			stmt.setString(1, board2.getTitle2());
			stmt.setString(2, board2.getContent2());
			stmt.setInt(3, board2.getBno2());
			stmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return;
			
	}

	public int deleteBoard2(int bno2) {
		CallableStatement stmt = null;
		int rs = 0;
		Board2 b = new Board2();
		
		String sql = "{call p_deleteBoard2(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setInt(1, bno2);
			rs = stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
}
