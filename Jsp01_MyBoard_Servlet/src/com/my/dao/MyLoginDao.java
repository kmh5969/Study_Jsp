package com.my.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.JDBCTemplate;
import com.my.dto.MyLoginDto;

public class MyLoginDao extends JDBCTemplate {
	
	public MyLoginDto login(String myid, String mypw) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO, MYID, MYPW, MYNAME "
				   + " FROM LOGINBOARD "
				   + " WHERE MYID = ? AND MYPW = ? ";
		
		MyLoginDto dto = new MyLoginDto();
		
		try {
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, myid);
			pstm.setString(2, mypw);
			System.out.println("03 query 준비" + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04 query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto.setMyno(rs.getInt(1));
				dto.setMyid(rs.getString(2));
				dto.setMypw(rs.getString(3));
				dto.setMyname(rs.getString(4));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("03, 04 error");
		} finally {
			close(con, pstm, rs);
		}
		
		return dto;
	}

}
