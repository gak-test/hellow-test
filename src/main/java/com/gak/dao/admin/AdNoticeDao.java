package com.gak.dao.admin;

import java.sql.*;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.gak.dto.admin.*;

import oracle.jdbc.OracleTypes;

@Repository
public class AdNoticeDao {
	
	final Connection dbconn = AdOracleConn.getInstance().getConnect();
	
	public ArrayList<AdNotice> getNoticeList() {
		
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<AdNotice> list = new ArrayList<AdNotice>();
		
		String sql = "{call p_getNoticeList(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.registerOutParameter(1,OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(1);
			
			while(rs.next()) {
				AdNotice n = new AdNotice();
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

	public int insertNotice(AdNotice adNotice) {
		CallableStatement stmt = null;
		String sql = "{call p_insertNotice(?,?,?)}";
		int rs = 0;
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, adNotice.getTitle());
			stmt.setString(2, adNotice.getContent());
			
			stmt.registerOutParameter(3,OracleTypes.INTEGER);
			stmt.executeUpdate();
			
			rs = stmt.getInt(3);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}

	public AdNotice getNotice(int notice_no) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		
		
		AdNotice n = new AdNotice();
		
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
	
	public void updateNotice(AdNotice adNotice) {
		CallableStatement stmt = null;
		int rs = 0;
		
		String sql = "{call p_updateNotice(?,?,?)}";
		try {
			stmt = dbconn.prepareCall(sql);			
			stmt.setString(1, adNotice.getTitle());
			stmt.setString(2, adNotice.getContent());
			stmt.setInt(3, adNotice.getNotice_no());
			stmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return;
			
	}

	public int deleteNotice(int notice_no) {
		CallableStatement stmt = null;
		int rs = 0;
		AdNotice n = new AdNotice();
		
		String sql = "{call p_deleteNotice(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setInt(1, notice_no);
			rs = stmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rs;
	}
	
}
