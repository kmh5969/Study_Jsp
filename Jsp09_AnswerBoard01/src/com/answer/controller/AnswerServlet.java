package com.answer.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.answer.biz.AnswerBizImpl;
import com.answer.dto.AnswerDto;

@WebServlet("/answer.do")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AnswerServlet() {

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[ " + command + " ]");
		
		AnswerBizImpl biz = new AnswerBizImpl();
		PrintWriter out = response.getWriter();
		
		if(command.equals("list")) {
			
			List<AnswerDto> list = biz.selectList();
			
			request.setAttribute("list", list);
			dispatch(request, response, "boardlist.jsp");
			
		} else if(command.equals("selectone")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			
			request.setAttribute("dto", dto);
			dispatch(request, response, "answerselectone.jsp");
			
		} else if(command.equals("insertanswerform")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			dto.setTitle("RE:" + dto.getTitle());
			dto.setContent("\n\n\n-------------------------------------\n" + dto.getContent());
			request.setAttribute("dto", dto);
			
			dispatch(request, response, "insertanswerform.jsp");
			
		} else if(command.equals("insertanswerres")) {
			
			int parentno = Integer.parseInt(request.getParameter("parentno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			AnswerDto dto = new AnswerDto(parentno, title, content, writer);
			
			int res = biz.answerProc(dto);
			
			if(res > 0) {
				System.out.println("답글 달기 성공");
				response.sendRedirect("an.do?command=list");
				
			} else {
				System.out.println("답글 달기 실패");
				dispatch(request, response, "an.do?command=insertanswerform&boardno=" + parentno);
				
			}
			
		} else if(command.equals("delete")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			
			int res = biz.delete(boardno);
			
			if(res > 0) {
				System.out.println("삭제 성공");
				response.sendRedirect("an.do?command=list");
			} else {
				System.out.println("삭제 실패");
				response.sendRedirect("an.do?command=selectone&boardno=" + boardno);
			}
			
		} else if(command.equals("updateform")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			AnswerDto dto = biz.selectOne(boardno);
			
			request.setAttribute("dto", dto);
			dispatch(request, response, "answerupdateform.jsp");
			
		} else if(command.equals("updateres")) {
			
			int boardno = Integer.parseInt(request.getParameter("boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			AnswerDto dto = new AnswerDto(boardno, title, content);
			
			int res = biz.updateBoard(dto);
			
			if(res > 0) {
				System.out.println("수정 성공");
				response.sendRedirect("an.do?command=selectone&boardno=" + boardno);
				
			} else {
				System.out.println("수정 성공");
				response.sendRedirect("an.do?command=selectone&boardno=" + boardno);
				
			}
			
		} else if(command.equals("insertform")) {
			
			response.sendRedirect("answerinsertform.jsp");
			
		} else if(command.equals("insertres")) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			AnswerDto dto = new AnswerDto(title, content, writer);
			
			int res = biz.insertBoard(dto);
			
			if(res > 0) {
				System.out.println("글 쓰기 성공");
				response.sendRedirect("an.do?command=list");
				
			} else {
				System.out.println("글 쓰기 실패");
				response.sendRedirect("answerinsertform.jsp");
				
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
