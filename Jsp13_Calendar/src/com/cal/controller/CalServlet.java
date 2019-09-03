package com.cal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.Util;
import com.cal.dto.CalDto;
import com.cal.dao.CalDao;

@WebServlet("/cal.do")
public class CalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("["+ command +"]");
		CalDao dao = new CalDao();
		
		if(command.equals("calendar")) {
			
			response.sendRedirect("calendar.jsp");
			
		} else if(command.equals("insertcal")) {
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			
			String mdate = year + Util.isTwo(month) + Util.isTwo(date) + Util.isTwo(hour) + Util.isTwo(min);
			
			CalDto dto = new CalDto();
			dto.setId(id);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setMdate(mdate);
			
			int res = dao.insertCalBoard(dto);
			
			if(res > 0) {
				response.sendRedirect("cal.do?command=calendar");
			} else {
				dispatch("error.jsp", request, response);
			}
			
		} else if(command.equals("selectdate")) {
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			
			String yyyymmdd = year + Util.isTwo(month) + Util.isTwo(date);
			String ymd = year + "년 " + month + "월 " + date + "일";
			
			List<CalDto> list = dao.getCalList("kh", yyyymmdd);
			request.setAttribute("list", list);
			request.setAttribute("ymd", ymd);
			dispatch("datelist.jsp", request, response);
			
		} else if(command.equals("detail")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			CalDto dto = dao.selectOne(seq);
			
			int year = Integer.parseInt(Util.getYear(dto.getMdate()));
			int month = Integer.parseInt(Util.getMonth(dto.getMdate()));
			int date = Integer.parseInt(Util.getDate(dto.getMdate()));
			
			request.setAttribute("year", year);
			request.setAttribute("month", month);
			request.setAttribute("date", date);
			request.setAttribute("dto", dto);
			dispatch("detail.jsp", request, response);
			
		} else if(command.equals("muldel")) {
			
			String[] arr = request.getParameterValues("chk");
			String ymd = request.getParameter("ymd");
			
			int year = Integer.parseInt(Util.getYear(ymd));
			int month = Integer.parseInt(Util.getMonth(ymd));
			int date = Integer.parseInt(Util.getDate(ymd));
			
			int res = dao.mulDel(arr);
			
			System.out.println(res);
			
			if(res == arr.length) {
				dispatch("cal.do?command=selectdate&year="+year+"&month="+month+"&date="+date, request, response);
			} else {
				System.out.println("삭제 실패");
			}
			
		} else if(command.equals("delete")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String ymd = request.getParameter("ymd");
			
			int year = Integer.parseInt(Util.getYear(ymd));
			int month = Integer.parseInt(Util.getMonth(ymd));
			int date = Integer.parseInt(Util.getDate(ymd));
			
			int res = dao.delete(seq);
			
			if(res > 0) {
				dispatch("cal.do?command=selectdate&year="+year+"&month="+month+"&date="+date, request, response);
			} else {
				System.out.println("삭제 실패");
			}
			
		} else if(command.equals("updateform")) {
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			CalDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			dispatch("updateform.jsp", request, response);
			
		} else if(command.equals("updateres")) {
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			CalDto dto = new CalDto();
			dto.setTitle(title);
			dto.setContent(content);
			dto.setSeq(seq);
			
			int res = dao.update(dto);
			
			if(res > 0) {
				System.out.println("성공");
			}
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
