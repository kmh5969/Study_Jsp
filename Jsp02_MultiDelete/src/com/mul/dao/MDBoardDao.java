package com.mul.dao;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mul.dto.MDBoardDto;

public class MDBoardDao {
	
	public List<MDBoardDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MDBOARD "
				   + " ORDER BY SEQ DESC ";
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query 준비" + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				
				MDBoardDto dto = new MDBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con,stmt,rs);
			System.out.println("05. db 종료");
		}
		
		return list;
	}
	
	public MDBoardDto selectOne(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE "
				   + " FROM MDBOARD WHERE SEQ = ? ";
		
		MDBoardDto dto = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03. query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				dto = new MDBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}
	
	public boolean insert(MDBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MDBOARD "
				   + " VALUES(MDBOARDSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03. query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
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
		
		return (res > 0) ? true:false;
	}
	
	public boolean update(MDBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MDBOARD "
				   + " SET TITLE = ?, CONTENT = ? "
				   + " WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03. query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
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
		
		return (res > 0) ? true:false;
	}
	
	public boolean delete(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03. query 준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			close(pstm);
			close(con);
		}
		
		return (res > 0) ? true:false;
	}
	
	public boolean multiDelete(String[] seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i < seq.length; i++) {
				
				pstm.setString(1, seq[i]);
				pstm.addBatch();				// addBatch() : 메모리에 적재 후, executeBatch()가 호출될 때 한번에 모두 실행.
				System.out.println("03. query 준비" + sql + seq[i]);
			}
			
			cnt = pstm.executeBatch();
			System.out.println("04. query 실행 및 리턴");
			
			for(int i = 0; i < cnt.length; i++) {
				
				// -2 : 성공, -3 : 실패
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(res == seq.length) {
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
		
		return (res == seq.length) ? true:false;
	}

}
