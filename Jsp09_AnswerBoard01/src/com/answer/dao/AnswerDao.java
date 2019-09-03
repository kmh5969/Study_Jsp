package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {
	
	String SELECT_LIST_SQL = " SELECT BOARDNO, GROUPNO, GROUPSQ, TITLETAB, TITLE, CONTENT, WRITER, REGDATE "
						   + " FROM ANSWERBOARD "
						   + " ORDER BY GROUPNO DESC, GROUPSQ ";
	
	String SELECT_ONE_SQL = " SELECT BOARDNO, GROUPNO, GROUPSQ, TITLETAB, TITLE, CONTENT, WRITER, REGDATE "
						  + " FROM ANSWERBOARD "
						  + " WHERE BOARDNO = ? ";
	
	String INSERT_SQL = " INSERT INTO ANSWERBOARD "
					  + " VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, ?, ?, ?, SYSDATE) ";
	
	String UPDATE_SQL = " UPDATE ANSWERBOARD "
					  + " SET TITLE = ?, CONTENT = ?"
					  + " WHERE BOARDNO = ? ";
	
	String DELETE_SQL = " DELETE FROM ANSWERBOARD "
					  + " WHERE BOARDNO = ? ";
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int insertBoard(AnswerDto dto);
	public int updateBoard(AnswerDto dto);
	public int delete(int boardno);
	
	public int updateAnswer(int parentBoardno);
	public int insertAnswer(AnswerDto dto);

}
