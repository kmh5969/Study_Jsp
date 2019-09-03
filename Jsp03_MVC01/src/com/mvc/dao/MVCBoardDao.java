package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.mvc.dto.MVCBoardDto;

public class MVCBoardDao extends JDBCTemplate{
	
	public List<MVCBoardDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MVCBOARD "
				   + " ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03 query 준비" + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				MVCBoardDto dto = new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con,stmt,rs);
		}
		
		return list;
	}

	public List<MVCBoardDto> selectList(int start, int end){
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<MVCBoardDto> list = new ArrayList<MVCBoardDto>();
		
		String sql = " SELECT  SEQ, WRITER, TITLE, CONTENT, REGDATE, R "
				   + " FROM(SELECT  SEQ, WRITER, TITLE, CONTENT, REGDATE, ROWNUM R "
				   + " FROM (SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + "		 FROM MVCBOARD "
				   + "	 	 ORDER BY SEQ DESC)) "
				   + " WHERE R BETWEEN ? AND ?  ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, start);
			pstm.setInt(2, end);
			
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				MVCBoardDto dto = new MVCBoardDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				dto.setRownum(rs.getInt(6));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con,pstm,rs);
		}
		
		return list;
	}
	
	public MVCBoardDto selectOne(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MVCBOARD "
				   + " WHERE SEQ = ? ";
		MVCBoardDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto = new MVCBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}
	
	public int insert(MVCBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MVCBOARD "
				   + " VALUES(MVCBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
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
		}
		
		return res;
	}
	
	public int update(MVCBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MVCBOARD"
				   + " SET TITLE = ?, CONTENT = ? "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03 query 준비" + sql);
			
			res = pstm.executeUpdate();
			
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
	
	public int delete(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MVCBOARD"
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

}
