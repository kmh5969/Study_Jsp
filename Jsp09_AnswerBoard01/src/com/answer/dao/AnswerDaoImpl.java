package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.AnswerDto;

import common.JDBCTemplate;

public class AnswerDaoImpl extends JDBCTemplate implements AnswerDao {

	@Override
	public List<AnswerDto> selectList() {
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		List<AnswerDto> list = new ArrayList<AnswerDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("03 query 준비" + SELECT_LIST_SQL);
			
			rs = stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				AnswerDto dto = new AnswerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupsq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				
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

	@Override
	public AnswerDto selectOne(int boardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AnswerDto dto = new AnswerDto();
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("03 query 준비" + SELECT_ONE_SQL);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupsq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		}finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}

	@Override
	public int insertBoard(AnswerDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			System.out.println("03 query 준비" + INSERT_SQL);
			
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

	@Override
	public int updateBoard(AnswerDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());
			System.out.println("03 query 준비" + UPDATE_SQL);
			
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

	@Override
	public int delete(int boardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, boardno);
			System.out.println("03 query 준비" + DELETE_SQL);
			
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

	@Override
	public int updateAnswer(int parentBoardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE ANSWERBOARD "
				   + " SET GROUPSQ = GROUPSQ+1 "
				   + " WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) "
				   + " AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, parentBoardno);
			pstm.setInt(2, parentBoardno);
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

	@Override
	public int insertAnswer(AnswerDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO ANSWERBOARD "
				   + " VALUES( "
				   + " 		BOARDNOSEQ.NEXTVAL, "
				   + "		(SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?), "
				   + "		(SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?)+1, "
				   + "		(SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO = ?)+1, "
				   + "		 ?, ?, ?, SYSDATE "
				   + " ) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
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
