package com.my.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.my.dto.MyDto;

public class MyDao extends SqlMapConfig {
	
	private String namespace = "com.my.db.mapper.";
	
	public List<MyDto> selectList(){
		SqlSession session = null; 
		List<MyDto> list = new ArrayList<MyDto>();
		
		session = getSqlSessionFactory().openSession();
		list = session.selectList(namespace+"selectList");
		
		return list;
	}
	
	public MyDto selectOne(int myno) {
		
		SqlSession session = null;
		MyDto dto = new MyDto();
		
		session = getSqlSessionFactory().openSession(true);
		dto = session.selectOne(namespace+"selectOne", myno);
		
		session.close();
		
		return dto;
	}
	
	public int insert(MyDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.insert(namespace+"insert", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	public int update(MyDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.update(namespace+"update", dto);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
	public int delete(int myno) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlSessionFactory().openSession(true);
			res = session.delete(namespace+"delete", myno);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return res;
	}
	
}
