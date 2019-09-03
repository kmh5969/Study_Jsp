package com.scope.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/scope.do")
public class ScopeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ScopeController() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String reqId = (String)request.getAttribute("reqId");
		String sessionId = (String)request.getSession().getAttribute("sessionId");
		String appId = (String)getServletContext().getAttribute("appId");

		System.out.println("request : " + reqId);
		System.out.println("session : " + sessionId);
		System.out.println("application : " + appId);
		
		PrintWriter out = response.getWriter();
		out.println( "<table border=\"1\">"  
					+"		<tr>"  
					+"			<th>scope</th>"  
					+"			<th>value</th>"  
					+"		</tr>"  
					+"		<tr>"  
					+"			<td>request</td>"  
					+"			<td>" + reqId + "</td>"  
					+"		</tr>"  
					+"		<tr>"  
					+"			<td>session</td>"  
					+"			<td>" + sessionId + "</td>"  
					+"		</tr>"  
					+"		<tr>"  
					+"			<td>application</td>" 
					+"			<td>" + appId + "</td>"  
					+"		</tr>" 
					+"	</table>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
