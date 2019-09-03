package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.dto.BikeDto;

import common.JDBCTemplate;

public class BikeDao extends JDBCTemplate{
	
	public int insert(List<BikeDto> list) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		String sql = " INSERT INTO BIKE_TB "
				   + " VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
		
		try {
			
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i < list.size(); i++) {
				
				BikeDto dto = list.get(i);
				
				pstm.setString(1, dto.getRentid());
				pstm.setString(2, dto.getAddrgu());
				pstm.setInt(3, dto.getContentid());
				pstm.setString(4, dto.getContentnm());
				pstm.setString(5, dto.getNewaddr());
				pstm.setInt(6, dto.getCradlecount());
				pstm.setDouble(7, dto.getLongitude());
				pstm.setDouble(8, dto.getLatitude());
				pstm.addBatch();
				System.out.println("03 query 준비" + sql);
			}
			
			cnt = pstm.executeBatch();
			System.out.println("04 query 실행 및 리턴");
			
			for(int i = 0; i < cnt.length; i++) {
				
				// -2 : 성공, -3 : 실패
				if(cnt[i] == -2) {
					res++;
				}
			}
			
			if(res == list.size()) {
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
