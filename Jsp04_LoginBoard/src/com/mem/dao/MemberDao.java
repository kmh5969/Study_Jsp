package com.mem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mem.dto.MemberDto;

import common.JDBCTemplate;

public class MemberDao extends JDBCTemplate{
	
	/*
	 *  관리자 기능
	 *  1. 회원 전체 정보(탈퇴한 회원 포함)
	 *  2. 가입된 회원의 전체 정보(myenabled = 'Y')
	 *  3. 회원 등급 조정
	 */
	
	/*
	 *  유저 기능
	 *  1. 로그인
	 *  2. 회원가입 -> 아이디 중복체크
	 *  3. 정보 조회
	 *  4. 정보 수정(비밀번호, 주소, 전화번호, 이메일)
	 *  5. 회원 탈퇴(UPDATE MYENABLED='N')
	 */
	
	public MemberDto login(String myid, String mypw) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = new MemberDto();
		
		String sql = " SELECT * FROM MEMBERBOARD "
				   + " WHERE MYID = ? AND MYPW = ?"
				   + " AND MYENABLED = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			pstm.setString(3, "Y");
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}
	
	public String idChk(String myid) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT MYID FROM MEMBERBOARD WHERE MYID = ? ";
		String res = null;
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		
		return res;
	}
	
	public int update(MemberDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MEMBERBOARD "
				   + " SET MYPW = ?, MYADDR = ?, MYPHONE = ?, MYEMAIL = ? "
				   + " WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMypw());
			pstm.setString(2, dto.getMyaddr());
			pstm.setString(3, dto.getMyphone());
			pstm.setString(4, dto.getMyemail());
			pstm.setInt(5, dto.getMyno());
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
	
	public int delUser(MemberDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MEMBERBOARD "
				   + " SET MYENABLED = ? "
				   + " WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "N");
			pstm.setInt(2, dto.getMyno());
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
	
	public int insert(MemberDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MEMBERBOARD"
				   + " VALUES(MEMBERBOARDSEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyid());
			pstm.setString(2, dto.getMypw());
			pstm.setString(3, dto.getMyname());
			pstm.setString(4, dto.getMyaddr());
			pstm.setString(5, dto.getMyphone());
			pstm.setString(6, dto.getMyemail());
			pstm.setString(7, "Y");
			pstm.setString(8, "GEUST");
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
	
	public List<MemberDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYID, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYENABLED, MYROLE "
				   + " FROM MEMBERBOARD "
				   + " ORDER BY MYNO DESC ";
		List<MemberDto> list = new ArrayList<MemberDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("03 query 준비" + sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04 query 실행");
			
			while(rs.next()) {
				
				MemberDto dto = new MemberDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMyname(rs.getString(3));
				dto.setMyaddr(rs.getString(4));
				dto.setMyphone(rs.getString(5));
				dto.setMyemail(rs.getString(6));
				dto.setMyenabled(rs.getString(7));
				dto.setMyrole(rs.getString(8));
				
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
	
	public MemberDto selectOne(int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MemberDto dto = new MemberDto();
		
		String sql = " SELECT * FROM MEMBERBOARD "
				   + " WHERE MYNO = ? ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
				dto.setMyaddr(rs.getString(5));
				dto.setMyphone(rs.getString(6));
				dto.setMyemail(rs.getString(7));
				dto.setMyenabled(rs.getString(8));
				dto.setMyrole(rs.getString(9));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}
	
	public int updateRole(int myno, String myrole) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MEMBERBOARD"
				   + " SET MYROLE = ?"
				   + " WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myrole);
			pstm.setInt(2, myno);
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
