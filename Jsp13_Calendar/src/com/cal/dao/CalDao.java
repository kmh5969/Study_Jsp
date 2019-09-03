package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

import common.JDBCTemplate;

public class CalDao extends JDBCTemplate {
	
	public int insertCalBoard(CalDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO CALBOARD "
				   + " VALUES(CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			System.out.println("03 query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	public List<CalDto> getCalList(String id, String yyyymmdd){
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = " SELECT SEQ, ID, TITLE, CONTENT, MDATE, REGDATE "
				   + " FROM CALBOARD "
				   + " WHERE ID = ? AND SUBSTR(MDATE, 1, 8) = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyymmdd);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return list;
	}
	
	public CalDto selectOne(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT * FROM CALBOARD "
				   + " WHERE SEQ = ? ";
		CalDto dto = new CalDto();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행");
			
			while(rs.next()) {
				
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setMdate(rs.getString(5));
				dto.setRegdate(rs.getDate(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}
	
	public List<CalDto> getCalViewList(String id, String yyyyMM){
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		String sql = " SELECT * " + 
					 " FROM(SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN, SEQ, ID, TITLE, CONTENT, MDATE, REGDATE " + 
					 "		FROM CALBOARD " + 
					 "		WHERE ID = ? " + 
					 "		AND SUBSTR(MDATE,1,6)= ?) " + 
					 " WHERE RN BETWEEN 1 AND 3 ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				CalDto dto = new CalDto();
				dto.setSeq(rs.getInt(2));
				dto.setId(rs.getString(3));
				dto.setTitle(rs.getString(4));
				dto.setContent(rs.getString(5));
				dto.setMdate(rs.getString(6));
				dto.setRegdate(rs.getDate(7));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return list;
	}
	
	public int getCalViewCount(String id, String yyyyMMdd) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int count = 0;
		String sql = " SELECT COUNT(*) "
				   + " FROM CALBOARD "
				   + " WHERE ID = ? "
				   + " AND SUBSTR(MDATE,1,8) = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return count;
	}
	
	public int mulDel(String[] arr) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int[] res = null;
		int cnt = 0;
		String sql = " DELETE FROM CALBOARD "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i < arr.length; i++) {
				pstm.setString(1, arr[i]);
				pstm.addBatch();
				System.out.println("03 query 준비" + sql);
			}
			
			res = pstm.executeBatch();
			System.out.println("04 query 실행 및 리턴");
			
			for(int i = 0; i < res.length; i++) {
				if(res[i] == -2) {
					cnt++;
				}
			}
			
			if(cnt == arr.length) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(pstm);
			close(con);
		}
		
		return cnt;
	}
	
	public int delete(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM CALBOARD "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03 query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
				
		return res;
	}
	
	public int update(CalDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE CALBOARD"
				   + " SET TITLE = ?, CONTENT = ? "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03 query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04 query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}

}
