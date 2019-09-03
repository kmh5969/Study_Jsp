package com.answer.biz;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerBiz {
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int insertBoard(AnswerDto dto);
	public int updateBoard(AnswerDto dto);
	public int delete(int boardno);
	
	public int answerProc(AnswerDto dto);

}
