package com.mul.biz;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.mul.dao.MDBoardDao;
import com.mul.dto.MDBoardDto;

public class MDBoardBiz {
	
	private MDBoardDao dao = new MDBoardDao();
	
	public MDBoardBiz() {
		
	}

	public List<MDBoardDto> selectList(){
		
		return dao.selectList();
	}
	
	public MDBoardDto selectOne(int seq) {
		
		return dao.selectOne(seq);
	}
	
	public boolean insert(MDBoardDto dto) {
		
		return dao.insert(dto);
	}
	
	public boolean update(MDBoardDto dto) {
		
		return dao.update(dto);
	}
	
	public boolean delete(int seq) {
		
		return dao.delete(seq);
	}
	
	public boolean multiDelete(String[] seq) {
		
		return dao.multiDelete(seq);
	}
	
	public List<MDBoardDto> seachTitle(String title){
		
		return dao.seachTitle(title);
	}

}