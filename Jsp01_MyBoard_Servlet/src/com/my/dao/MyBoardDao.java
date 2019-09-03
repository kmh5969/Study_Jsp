package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.my.dto.MyBoardDto;

public class MyBoardDao extends JDBCTemplate{
	
	public List<MyBoardDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
				   + " FROM MYBOARD "
				   + " ORDER BY MYNO DESC ";
		
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		try {
			
			stmt = con.createStatement();
			System.out.println("03 query 준비" + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04 query 실행");
			
			while(rs.next()) {
				
				MyBoardDto dto = new MyBoardDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				
				list.add(dto);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, stmt, rs);
		}
		
		return list;
	}
	
	public MyBoardDto selectOne(int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
				   + " FROM MYBOARD "
				   + " WHERE MYNO = ? ";
		
		MyBoardDto dto = new MyBoardDto();
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}
	
	public int update(MyBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYBOARD "
				   + " SET MYTITLE = ?, MYCONTENT = ? "
				   + " WHERE MYNO = ? ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
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
	
	public int delete(int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MYBOARD "
				   + " WHERE MYNO = ? ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
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
	
	public int insert(MyBoardDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD "
				   + " VALUES(MYSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
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
