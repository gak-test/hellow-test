package com.gak.dao.user;

import java.sql.*;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.gak.dto.user.*;
import oracle.jdbc.OracleTypes;

@Repository
public class NoticeDao {
	
	final Connection dbconn = OracleConn.getInstance().getConnect();
	
	public ArrayList<Notice> getNoticeList() {
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		
		String sql = "{call p_getNoticeList(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.registerOutParameter(1,OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(1);
			
			while(rs.next()) {
				Notice n = new Notice();
				n.setNotice_no(rs.getInt("notice_no"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setOdate(rs.getString("odate"));
				n.setWdate(rs.getString("wdate"));
				list.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	

	public Notice getNotice(int notice_no) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		
		Notice n = new Notice();
		
		String sql = "{call p_getNotice(?,?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setInt(1, notice_no);
			stmt.registerOutParameter(2,OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(2);
					
			if(rs.next()) {
				
				n.setNotice_no(rs.getInt("notice_no"));
				n.setTitle(rs.getString("title"));
				n.setContent(rs.getString("content"));
				n.setOdate(rs.getString("odate"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	
	
}
