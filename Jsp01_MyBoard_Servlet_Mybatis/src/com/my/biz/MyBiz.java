package com.my.biz;

import java.util.List;

import com.my.dao.MyDao;
import com.my.dto.MyDto;

public class MyBiz {
	
	private MyDao dao = new MyDao();
	
	public List<MyDto> selectList(){
		
		return dao.selectList();
	}
	
	public MyDto selectOne(int myno) {
		
		return dao.selectOne(myno);
	}
	
	public int update(MyDto dto) {
		
		return dao.update(dto);
	}
	
	public int delete(int myno) {
		
		return dao.delete(myno);
	}
	
	public int insert(MyDto dto) {
		
		return dao.insert(dto);
	}

}
