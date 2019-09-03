package com.my.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.el.dto.Score;

@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		if(command.equals("basic")) {
			
			response.sendRedirect("basic-arithmetic.jsp");
		} else if(command.equals("eltest")) {
			Score sc = new Score();
			sc.setName("하준성");
			sc.setKor(100);
			sc.setEng(100);
			sc.setMath(100);
			
			request.setAttribute("score", sc);
			RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp");
			dispatch.forward(request, response);
			
		} else if(command.equals("jstltest")) {
			
			List<Score> res = new ArrayList<Score>();
			
			for(int i = 10; i < 50; i+=10) {
				Score sc = new Score("이름"+i,50+i,50+i,50+i);
				
				res.add(sc);
				
			}
			
			request.setAttribute("list", res);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("jstltest.jsp");
			dispatch.forward(request, response);
			
		} else if(command.equals("usebean")) {
			
			response.sendRedirect("usebeantest.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
