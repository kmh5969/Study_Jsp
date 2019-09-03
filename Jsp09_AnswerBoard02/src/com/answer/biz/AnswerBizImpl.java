package com.answer.biz;

import java.util.List;

import com.answer.dao.AnswerDaoImpl;
import com.answer.dto.AnswerDto;

public class AnswerBizImpl implements AnswerBiz {
	
	AnswerDaoImpl dao = new AnswerDaoImpl();

	@Override
	public List<AnswerDto> selectList() {
		
		return dao.selectList();
	}

	@Override
	public AnswerDto selectOne(int boardno) {
		
		return dao.selectOne(boardno);
	}

	@Override
	public int insert(AnswerDto dto) {
		
		return dao.insert(dto);
	}

	@Override
	public int update(AnswerDto dto) {
		
		return dao.update(dto);
	}

	@Override
	public int delete(int boardno) {
		
		return dao.delete(boardno);
	}

	@Override
	public int answerProc(AnswerDto dto) {
		
		int res1 = dao.updateAnswer(dto.getBoardno());
		int res2 = dao.insertAnswer(dto);
		
		return (res1 + res2);
	}

}
