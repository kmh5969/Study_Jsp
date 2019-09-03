package com.mul.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.mul.dto.MDBoardDto;

public class MDBoardDao extends SqlMapConfig{
	
	private String namespace = "muldelmapper.";
	
	public List<MDBoardDto> selectList(){
		
		SqlSession session = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		try {
			session = getSqlsessionFactory().openSession(false);
			list = session.selectList(namespace+"selectList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}
	
	public MDBoardDto selectOne(int seq) {
		
		SqlSession session = null;
		MDBoardDto dto = new MDBoardDto();
		
		session = getSqlsessionFactory().openSession(false);
		dto = session.selectOne(namespace+"selectOne", seq);
		
		return dto;
	}
	
	public boolean insert(MDBoardDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlsessionFactory().openSession(false);
			res = session.insert(namespace+"insert", dto);
			
			if(res > 0) {
				session.commit();
			}
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return (res > 0) ? true:false;
	}
	
	public boolean update(MDBoardDto dto) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlsessionFactory().openSession(false);
			res = session.update(namespace+"update", dto);
			
			if(res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return (res > 0) ? true:false;
	}
	
	public boolean delete(int seq) {
		
		SqlSession session = null;
		int res = 0;
		
		try {
			session = getSqlsessionFactory().openSession(false);
			res = session.delete(namespace+"delete", seq);
			
			if(res > 0) {
				session.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return (res > 0) ? true:false;
	}
	
	public boolean multiDelete(String[] seq) {
		
		int count = 0;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seq);
		
		SqlSession session = null;
		
		try {
			session = getSqlsessionFactory().openSession(false);
			count = session.delete(namespace+"muldel", map);
			
			if(count == seq.length) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return (count == seq.length) ? true:false;
	}
	
	public List<MDBoardDto> seachTitle(String title){
		
		SqlSession session = null;
		List<MDBoardDto> list = new ArrayList<MDBoardDto>();
		
		session = getSqlsessionFactory().openSession();
		list = session.selectList(namespace+"seachtitle", title);
		
		return list;
	}

}
