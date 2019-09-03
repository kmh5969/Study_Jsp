package com.my.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import com.my.biz.MyLoginBiz;
import com.my.dto.MyLoginDto;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		MyLoginBiz biz = new MyLoginBiz();
		PrintWriter out = response.getWriter();
		
		if(command.equals("login")) {
			
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			
			MyLoginDto dto = biz.login(myid, mypw);
			
			if(dto.getMyid() != null) {
				
				request.getSession().setAttribute("dto", dto);
				response.sendRedirect("my.do?command=list");
				
			} else {
				
				out.println("<script type=\"text/javascript\">" + 
							"	alert(\"ID, PW를 확인해주세요\");" + 
							"	location.href='my.do?command=index';"+
							"</script>");
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
