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
	public int insertBoard(AnswerDto dto) {

		return dao.insertBoard(dto);
	}

	@Override
	public int updateBoard(AnswerDto dto) {
		
		return dao.updateBoard(dto);
	}

	@Override
	public int delete(int boardno) {
		
		return dao.delete(boardno);
	}

	@Override
	public int answerProc(AnswerDto dto) {
		
		dao.updateAnswer(dto.getBoardno());
		
		return dao.insertAnswer(dto);
	}

}
