package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyBoardDto;

public class MyBoardDao {
	
	public List<MyBoardDto> selectList() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01. error");
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02. error");
		}
		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
				   + " FROM MYBOARD "
				   + " ORDER BY MYNO DESC ";
		
		List<MyBoardDto> list = new ArrayList<MyBoardDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("03. query준비" + sql);
			rs = stmt.executeQuery(sql);
			System.out.println("04. query실행");
			
			while(rs.next()) {
				
				MyBoardDto dto = new MyBoardDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03. 04.error");
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				System.out.println("05. db종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05. error");
			}
			
		}
		
		return list;
	}
	
	public MyBoardDto selectOne(int myno) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01. error");
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02. error");
		}
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE "
				   + " FROM MYBOARD"
				   + " WHERE MYNO = ? ";
		MyBoardDto dto = new MyBoardDto();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03. query준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04. query실행 및 리턴");
			while(rs.next()) {
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03. 04. error");
		} finally {
			try {
				rs.close();
				pstm.close();
				con.close();
				System.out.println("05. db종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05. error");
			}
		}
		
		
		return dto;
	}
	
	public int insert(MyBoardDto dto) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01. error");
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02. error");
		}
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO MYBOARD "
				   + " VALUES(MYSEQ.NEXTVAL, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("03. query준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03. 04. error");
			
		} finally {
			try {
				pstm.close();
				con.close();
				System.out.println("05. db종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05. error");
			}
		}
		
		return res;
	}
	
	public int update(MyBoardDto dto) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01. error");
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02. error");
		}
		
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
			System.out.println("03. query준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03. 04. error");
			
		} finally {
			try {
				pstm.close();
				con.close();
				System.out.println("05. db종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05. error");
			}
		}
		
		return res;
	}
	
	public int delete(int myno) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("01. driver 연결");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("01. error");
		}
		
		Connection con = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "kh";
		String password = "kh";
		
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("02. 계정 연결");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("02. error");
		}
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MYBOARD "
				   + " WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03. query준비" + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. query실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03. 04. error");
			
		} finally {
			try {
				pstm.close();
				con.close();
				System.out.println("05. db종료");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("05. error");
			}
		}
		
		return res;
	}

}
