<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
<%
	pageContext.setAttribute("pageId", "page value");
	request.setAttribute("reqId", "request value");
	session.setAttribute("sessionId", "session value");
	application.setAttribute("appId", "application value");
%>
</head>
<body>

	<h1>INDEX</h1>
	
	pageId : <%=pageContext.getAttribute("pageId") %><br/>
	reqId : <%=request.getAttribute("reqId") %><br/>
	sessionId : <%=session.getAttribute("sessionId") %><br/>
	applicationId : <%=application.getAttribute("appId") %><br/>
	
	<a href="result.jsp?">result</a><br/>
	<a href="scope.do">controller</a>

</body>
</html>