package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.biz.MVCBoardBiz;
import com.mvc.biz.MVCBoardBizImpl;
import com.mvc.dto.MVCBoardDto;


@WebServlet(urlPatterns = {"/selectall", "/selectone", "/updateform", "/updateres", "/deleteone", "/insertform", "/insertres"})
public class MVCServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MVCBoardBiz biz;
       
    public MVCServlet() {
    	
    }
    
    private void getRequest(HttpServletRequest request, HttpServletResponse response) {
    	
    	biz = new MVCBoardBizImpl();
    	
    	String command = request.getRequestURI();
    	System.out.println("[" + command + "]");
    	
    	if(command.endsWith("/selectall")) {
    		doSelectAll(request, response);
    		
    	} else if(command.endsWith("/selectone")) {
    		doSelOne(request, response);
    		
    	} else if(command.endsWith("/updateform")) {
    		doUpdateForm(request, response);
    		
    	} else if(command.endsWith("/updateres")) {
    		doUpdateRes(request, response);
    		
    	} else if(command.endsWith("/deleteone")) {
    		doDeleteOne(request, response);
    		
    	} else if(command.endsWith("/insertform")) {
    		doInsertForm(request, response);
    		
    	} else if(command.endsWith("insertres")) {
    		doInsertRes(request, response);
    		
    	}
    	
    }

	private void doInsertRes(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto(writer, title, content);
		
		int res = biz.insert(dto);
		
		if(res > 0) {
			try {
				response.sendRedirect("selectall");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doInsertForm(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			response.sendRedirect("boardinsertform.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doDeleteOne(HttpServletRequest request, HttpServletResponse response) {
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		int res = biz.delete(seq);
		
		if(res > 0) {
			try {
				response.sendRedirect("selectall");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doUpdateRes(HttpServletRequest request, HttpServletResponse response) {
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		MVCBoardDto dto = new MVCBoardDto(seq, title, content);
		int res = biz.update(dto);
		
		if(res > 0) {
			try {
				response.sendRedirect("selectone?seq=" + seq);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doUpdateForm(HttpServletRequest request, HttpServletResponse response) {
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = biz.selectOne(seq);
		request.setAttribute("dto", dto);
		
		try {
			dispatch(request, response, "boardupdateform.jsp");
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doSelOne(HttpServletRequest request, HttpServletResponse response) {
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		MVCBoardDto dto = biz.selectOne(seq);
		request.setAttribute("selectone",dto);
		
		try {
			dispatch(request, response, "boardselectone.jsp");
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void doSelectAll(HttpServletRequest request, HttpServletResponse response) {
		
		List<MVCBoardDto> list = biz.selectList();
		request.setAttribute("list", list);
		
		try {
			dispatch(request, response, "boardlist.jsp");
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		getRequest(request, response);
		
//		
//		String command = request.getParameter("command");
//		System.out.println("[ " + command + " ]");
//		
//		MVCBoardBizImpl biz = new MVCBoardBizImpl();
//		PrintWriter out = response.getWriter();
//		
//		if(command.equals("list")) {
//			
//			List<MVCBoardDto> list = biz.selectList();
//			
//			request.setAttribute("list", list);
//			dispatch(request, response, "boardlist.jsp");
//			
//		} else if(command.equals("selectone")) {
//			
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			MVCBoardDto dto = biz.selectOne(seq);
//			
//			request.setAttribute("selectone", dto);
//			dispatch(request, response, "boardselectone.jsp");
//			
//		} else if(command.equals("updateform")) {
//			
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			MVCBoardDto dto = biz.selectOne(seq);
//			
//			request.setAttribute("dto", dto);
//			dispatch(request, response, "boardupdateform.jsp");
//			
//		} else if(command.equals("updateres")) {
//			
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			
//			MVCBoardDto dto = new MVCBoardDto();
//			dto.setSeq(seq);
//			dto.setTitle(title);
//			dto.setContent(content);
//			
//			int res = biz.update(dto);
//			
//			response.sendRedirect("mvc.do?command=selectone&seq=" + seq);
//			
//		} else if(command.equals("delete")) {
//			
//			int seq = Integer.parseInt(request.getParameter("seq"));
//			
//			int res = biz.delete(seq);
//			
//			response.sendRedirect("mvc.do?command=list");
//			
//		} else if(command.equals("insertform")) {
//			
//			dispatch(request, response, "boardinsertform.jsp");
//			
//		} else if(command.equals("insertres")) {
//			
//			String writer = request.getParameter("writer");
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			
//			MVCBoardDto dto = new MVCBoardDto(writer, title, content);
//			
//			int res = biz.insert(dto);
//			
//			if(res > 0) {
//				
//				System.out.println("작성 성공");
//				dispatch(request, response, "mvc.do?command=list");
//				
//			} else {
//				
//				System.out.println("작성 실패");
//				dispatch(request, response, "mvc.do?command=insertform");
//			}
//		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
