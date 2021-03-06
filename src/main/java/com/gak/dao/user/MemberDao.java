package com.gak.dao.user;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.gak.dto.user.*;
import oracle.jdbc.OracleTypes;

@Repository
public class MemberDao {
	
	final Connection dbconn = OracleConn.getInstance().getConnect();
	
	//회원등록
	public int insertMember(Member m){
		
		//PreparedStatement stmt = null;
		int rs = 0;
		
		//방법1
		CallableStatement stmt = null;
		String sql = "{call p_insertMember(?,?,?,?,?,?,?,?,?,?)}";
		try {
			//stmt = dbconn.prepareStatement(sql);
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, m.getId());
			stmt.setString(2, m.getPw());
			stmt.setString(3, m.getName());
			stmt.setString(4, m.getGender());
			stmt.setString(5, m.getEmail());
			stmt.setString(6, m.getPost());
			stmt.setString(7, m.getAddr1());
			stmt.setString(8, m.getAddr2());
			stmt.setString(9, m.getAddr3());
			stmt.setString(10, m.getIntro());
			
			rs = stmt.executeUpdate();
			
			if(rs>0 && m.getHobby() != null) {
							
				//방법2
				sql = "BEGIN p_addHobby(?,?); END;";
				
				stmt = dbconn.prepareCall(sql);
				stmt.setString(1, m.getId());
				
				for(int i =0; i<m.getHobby().length; i++) {
						stmt.setString(2, m.getHobby()[i]);	
						stmt.executeUpdate();
				
				}
				
			}
		
			
		}catch (SQLException e) {			
			e.printStackTrace();
		}       
				
		return rs;
	}
	
	//회원수정
	public int updateMember(Member m) {
		CallableStatement stmt = null;
		int rs = 0;
			
		String sql ="{call p_updateMember(?,?,?,?,?,?,?,?,?)}";
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, m.getName());
			stmt.setString(2, m.getEmail());
			stmt.setString(3, m.getGender());
			stmt.setString(4, m.getPost());
			stmt.setString(5, m.getAddr1());
			stmt.setString(6, m.getAddr2());
			stmt.setString(7, m.getAddr3());
			stmt.setString(8, m.getIntro());
			stmt.setString(9, m.getId());					
			rs = stmt.executeUpdate();
			
				//취미삭제
			sql = "{call p_delHobby(?)}";
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1, m.getId());
			stmt.executeUpdate();			

				//취미수정
			if(rs > 0 && m.getHobby() != null) {			
				sql = "{call p_addHobby(?,?)}";
				stmt = dbconn.prepareCall(sql);
								
				for(int i=0; i<m.getHobby().length; i++) {					
						
					stmt.setString(1, m.getId());
					stmt.setString(2, m.getHobby()[i]);	
					stmt.executeUpdate();
									
				}
			}
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}       
				
		return rs;
	}
	
	
	//회원삭제
	public int deleteMember(String id) {
		PreparedStatement stmt = null;		
		int rs = 0;
		
		try {
			String sql = "DELETE FROM hobby WHERE member_id = ?";		
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeUpdate();
			
//			sql = "DELETE FROM member WHERE id = ?";
//			stmt = dbconn.prepareStatement(sql);
//			stmt.setString(1, id);
//			rs = stmt.executeUpdate();
			
			sql = "UPDATE member SET isdel = 'Y' WHERE id = ?";
			stmt = dbconn.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeUpdate();
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}       
		
		return rs;
	}

	//회원조회
	public Member[] getMemberList() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Member[] mArr = new Member[100];		
		try {			  
			String sql = "SELECT * FROM member m, hobby h"
					+ " where m.id = h.member_id(+) order by m.id, code_value";
			
			stmt = dbconn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					                            ResultSet.CONCUR_UPDATABLE);					
			rs = stmt.executeQuery();
			
			//레코드 개수 파악
			rs.last();
			int rows = rs.getRow();
		
			//커서를 제일 앞으로(rs.first() 보다 한칸 앞 빈공간)
			rs.beforeFirst();
			
			int i = 0;
			while(rs.next()) {											
				Member m = new Member();
				mArr[i++] = m;
				String id = rs.getString("id");
				m.setId(id);
				m.setName(rs.getString("name"));
				m.setPw(rs.getString("pw"));
				m.setGender(rs.getString("gender"));
				m.setEmail(rs.getString("email"));
				
				if(rs.getString("hobby_no") != null) {
					String[] hobby = new String[10];
					int j = 0;
					do {
						hobby[j++] = rs.getString("code_val");						
						rs.next();
						
						//마지막 행인지 검사필요						
						//System.out.println("현재행 :" + rs.getRow());
						if(rs.getRow() == 0) {
							break;
						}
					} while(id.equals(rs.getString("id")));
					m.setHobby(hobby);
					rs.previous();
				}
				
			}
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}       
				
		return mArr;
	}

	public Map<String, String> loginProc(Member member) {
		
		//PreparedStatement stmt = null;
		CallableStatement stmt = null;
		ResultSet rs = null;	
		
		Map<String, String> map = new HashMap<String, String>();
		
		//String sql = "SELECT pw, name FROM member WHERE id = ? ";
		String sql = "{call p_loginMember(?,?)}";
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1,member.getId());
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.executeQuery();
			
			rs = (ResultSet)stmt.getObject(2);
			if(rs.next()) {
				
				if(member.getPw().equals(rs.getString("pw"))) {
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
	//id 중복검사
	public int idDoubleCheck(String id) {
		CallableStatement stmt = null;
		ResultSet rs = null;
		int stat = 0;
		
		String sql = "{call p_idDoubleCheck(?, ?)}";
		
		try {
			stmt = dbconn.prepareCall(sql);
			stmt.setString(1,id);
			stmt.registerOutParameter(2,OracleTypes.INTEGER);
			stmt.executeQuery();
			//rs = stmt.executeQuery();
			System.out.println(id);
			System.out.println(stmt);
			
			
			//rs.next();
			//stat = rs.getInt("cnt");						
			stat = stmt.getInt(2);
			System.out.println(stat);
		} catch (SQLException e) {			
			e.printStackTrace();
		}       
				
		return stat;
		
	}

	public Member getMember(String id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Member m = new Member();
		
		try {			  
			String sql = "SELECT * FROM member m, hobby h"
					+ " where m.id = h.member_id(+) and m.id = ?";
			
			stmt = dbconn.prepareStatement(sql);	
			stmt.setString(1, id);
			rs = stmt.executeQuery();		
					
			if(rs.next()) {																		
				m.setId(rs.getString("id"));
				m.setName(rs.getString("name"));
				m.setPw(rs.getString("pw"));
				m.setGender(rs.getString("gender"));
				m.setEmail(rs.getString("email"));
				m.setPost(rs.getString("post"));
				m.setAddr1(rs.getString("addr1"));
				m.setAddr2(rs.getString("addr2"));
				m.setAddr3(rs.getString("addr3"));
				m.setIntro(rs.getString("intro"));
				
				if(rs.getString("hobby_no") != null) {
					String[] hobby = new String[10];
					int j = 0;
					do {
						hobby[j++] = rs.getString("code_val");												
					} while(rs.next());
					m.setHobby(hobby);				
				}						
			}
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}       
				
		return m;
		
	}
}
