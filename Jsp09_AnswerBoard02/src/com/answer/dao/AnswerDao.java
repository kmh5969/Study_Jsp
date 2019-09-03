package com.answer.dao;

import java.util.List;

import com.answer.dto.AnswerDto;

public interface AnswerDao {
	
	String SELECT_LIST_SQL = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC, GROUPSQ ";
	
	String SELECT_ONE_SQL = " SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? ";
	
	String INSERT_SQL = " INSERT INTO ANSWERBOARD "
					  + " VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, 'N', ?, ?, ?, SYSDATE) ";
	
	String UPDATE_SQL = " UPDATE ANSWERBOARD "
					  + " SET TITLE = ?, CONTENT = ?"
					  + " WHERE BOARDNO = ? ";
	
	String DELETE_SQL = " DELETE FROM ANSWERBOARD "
					  + " WHERE BOARDNO = ? ";
	
	String DELETE_ANSWER_SQL = " UPDATE ANSWERBOARD "
							 + " SET DELFLAG = 'Y' "
							 + " WHERE BOARDNO = ? ";
	
	String UPDATE_ANSWER_SQL = " UPDATE ANSWERBOARD "
							 + " SET GROUPSQ = GROUPSQ+1 "
							 + " WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) "
							 + " AND GROUPSQ > (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?) ";
	
	String INSERT_ANSWER_SQL = " INSERT INTO ANSWERBOARD "
							 + " VALUES( "
							 + " 	BOARDNOSEQ.NEXTVAL, "
							 + " 	GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO = ?) "
							 + " 	GROUPSQ = (SELECT GROUPSQ FROM ANSWERBOARD WHERE BOARDNO = ?)+1 "
							 + " 	TITLETAB = (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO = ?)+1 "
							 + " 	'N', ?, ?, ?, SYSDATE "
							 + " ) ";
	
	public List<AnswerDto> selectList();
	public AnswerDto selectOne(int boardno);
	public int insert(AnswerDto dto);
	public int update(AnswerDto dto);
	public int delete(int boardno);
	public int updateAnswer(int boardno);
	public int insertAnswer(AnswerDto dto);
	public int deleteAnswer(int boardno);

}
