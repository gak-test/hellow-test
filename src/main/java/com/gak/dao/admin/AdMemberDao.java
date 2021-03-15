package com.gak.dao.admin;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.gak.dto.admin.*;

import oracle.jdbc.OracleTypes;

@Repository
public class AdMemberDao {
	
	final Connection dbconn = AdOracleConn.getInstance().getConnect();
	

	//회원조회
	public ArrayList<AdMember> getMemberList() {
		
		ResultSet rs = null;
		ArrayList<AdMember> list = new ArrayList<AdMember>();	
		PreparedStatement stmt = null;
		String sql = "select * from member order by grade , wdate desc";
		
		try {
			stmt=dbconn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				AdMember m = new AdMember();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setGrade(rs.getString("grade"));
				m.setWdate(rs.getString("wdate"));
				m.setDdate(rs.getString("ddate"));
				list.add(m);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	public ArrayList<AdMember> getdelMemberList() {
		
		ResultSet rs = null;
		ArrayList<AdMember> list = new ArrayList<AdMember>();	
		PreparedStatement stmt = null;
		String sql = "select * from member where isdel = 'Y' order by ddate asc , wdate desc";
		
		try {
			stmt=dbconn.prepareStatement(sql);
			rs= stmt.executeQuery();
			while(rs.next()) {
				AdMember m = new AdMember();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setGrade(rs.getString("grade"));
				m.setWdate(rs.getString("wdate"));
				m.setDdate(rs.getString("ddate"));
				list.add(m);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	
	public Map<String, String> loginProc(AdMember adMember) {
		
		CallableStatement stmt = null;
		ResultSet rs = null;	
		
		Map<String, String> map = new HashMap<String, String>();
		
		String sql = "{call p_loginAdmin(?,?)}";
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1,adMember.getId());
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(2);
			if(rs.next()) {
				
				if(adMember.getPw().equals(rs.getString("pw"))) {
					map.put("stat", "ok");
					map.put("name", rs.getString("name"));					
				} else {
					map.put("stat", "pwFail");
				}
				
			} else {
				map.put("stat", "idFail");
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}       
				
		return map;
		
	}
	

	public int cntMember() {
		PreparedStatement stmt = null;
		String sql = "select count(*) as cnt from member where grade=0 and isdel='N'";
		ResultSet rs = null;
		int cnt = 0;
		
		try {
			stmt=dbconn.prepareStatement(sql);
			rs= stmt.executeQuery();
			rs.next();
			cnt=rs.getInt("cnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	public int delcntMember() {
		PreparedStatement stmt = null;
		String sql = "select count(*) as dcnt from member where isdel='Y'";
		ResultSet rs = null;
		int dcnt = 0;
		
		try {
			stmt=dbconn.prepareStatement(sql);
			rs= stmt.executeQuery();
			rs.next();
			dcnt=rs.getInt("dcnt");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dcnt;
	}
	
	public ArrayList<AdMember> gradeMember(String name, String grade) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<AdMember> list = new ArrayList<AdMember>();
		
		
		String sql = "{call p_gradeMember(?,?,?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, name);
			stmt.setString(2, grade);
			stmt.registerOutParameter(3,OracleTypes.CURSOR);			
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(3);
						
			while(rs.next()) {
				AdMember m = new AdMember();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setGrade(rs.getString("grade"));
				m.setWdate(rs.getString("wdate"));
				m.setDdate(rs.getString("ddate"));	
				m.setIsdel(rs.getString("isdel"));
				list.add(m);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<AdMember> ddateMember(String ddate) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		ArrayList<AdMember> list = new ArrayList<AdMember>();
		
		
		String sql = "{call p_ddateMember(?,?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, ddate);
			stmt.registerOutParameter(2,OracleTypes.CURSOR);			
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(2);
						
			while(rs.next()) {
				AdMember m = new AdMember();
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setGender(rs.getString("gender"));
				m.setGrade(rs.getString("grade"));
				m.setWdate(rs.getString("wdate"));
				m.setDdate(rs.getString("p_ddate"));	
				m.setIsdel(rs.getString("isdel"));
				list.add(m);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int ddatedelMember(String ddate) {
		CallableStatement stmt = null;
		int rs = 0;
		
		String sql = "{call p_ddatedelMember(?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, ddate);			
			rs = stmt.executeUpdate();
									
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	
}
