package com.mem.biz;

import java.util.List;

import com.mem.dao.MemberDao;
import com.mem.dto.MemberDto;

public class Memberbiz {
	
	MemberDao dao = new MemberDao();
	
	public MemberDto login(String myid, String mypw) {
		
		return dao.login(myid, mypw);
	}
	
	public String idChk(String myid) {
		
		return dao.idChk(myid);
	}
	
	public int update(MemberDto dto) {
		
		return dao.update(dto);
	}
	
	public int delUser(MemberDto dto) {
		
		return dao.delUser(dto);
	}
	
	public int insert(MemberDto dto) {
		
		return dao.insert(dto);
	}
	
	public List<MemberDto> selectList(){
		
		return dao.selectList();
	}
	
	public MemberDto selectOne(int myno) {
		
		return dao.selectOne(myno);
	}
	
	public int updateRole(int myno, String myrole) {
		
		return dao.updateRole(myno, myrole);
	}

}
