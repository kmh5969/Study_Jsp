package com.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.biz.MyBiz;
import com.my.dto.MyDto;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		MyBiz biz = new MyBiz();
		PrintWriter out = response.getWriter();
		
		if(command.equals("list")) {
			// biz -> dao -> db에 가서 list 리턴받기.
			List<MyDto> list = biz.selectList();
			
			// request 객체에 담자.
			request.setAttribute("list", list);
			dispatch(request, response, "mylist.jsp");
			
		} else if(command.equals("selectone")) {
			
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyDto dto = biz.selectOne(myno);
			
			request.setAttribute("dto", dto);
			dispatch(request, response, "myselectone.jsp");
			
		} else if(command.equals("updateform")) {
			
			int myno = Integer.parseInt(request.getParameter("myno"));
			MyDto dto = biz.selectOne(myno);
			
			request.setAttribute("dto", dto);
			dispatch(request, response, "myupdateform.jsp");
			
		} else if(command.equals("updateres")) {
			
			int myno = Integer.parseInt(request.getParameter("myno"));
			String mytitle = request.getParameter("mytitle");
			String mycontent = request.getParameter("mycontent");
			
			MyDto dto = new MyDto(myno, mytitle, mycontent);
			int res = biz.update(dto);
			
			if(res > 0) {
				
				out.println("	<script type=\"text/javascript\">" + 
							"		alert(\"수정성공\");" + 
							"		location.href='my.do?command=selectone&myno=" + dto.getMyno() + "';" +
							"	</script>");
				
			} else {
				
				out.println("	<script type=\"text/javascript\">" + 
							"		alert(\"수정실패\");" + 
							"		location.href='my.do?command=updateform&myno=" + dto.getMyno() + "';"+
							"	</script>");
			
			}
			
		} else if(command.equals("delete")) {
			
			int myno = Integer.parseInt(request.getParameter("myno"));
			
			int res = biz.delete(myno);
			
			if(res > 0) {
				
				out.println("	<script type=\"text/javascript\">" + 
							"		alert(\"삭제성공\");" + 
							"		location.href='my.do?command=list';" +
							"	</script>");
				
			} else {
				
				out.println("	<script type=\"text/javascript\">" + 
							"		alert(\"삭제실패\");" + 
							"		location.href='my.do?command=selectone&myno=" + myno + "';"+
							"	</script>");
			
			}
			
		} else if(command.equals("insertform")) {
			
			dispatch(request, response, "myinsertform.jsp");
			
		} else if(command.equals("insertres")) {
			
			String myname = request.getParameter("myname");
			String mytitle = request.getParameter("mytitle");
			String mycontent = request.getParameter("mycontent");
			
			MyDto dto = new MyDto(myname, mytitle, mycontent);
			
			int res = biz.insert(dto);
			
			if(res > 0) {
				
				out.println("	<script type=\"text/javascript\">" + 
							"		alert(\"작성성공\");" + 
							"		location.href='my.do?command=list';" +
							"	</script>");
				
			} else {
				
				out.println("	<script type=\"text/javascript\">" + 
							"		alert(\"작성실패\");" + 
							"		location.href='my.do?command=insertform';" +
							"	</script>");
				
			}
			
		} else if(command.equals("index")) {
			
			dispatch(request, response, "index.jsp");
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
