<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>result</title>
</head>
<body>

	<h1>RESULT</h1>
	
	pageId : <%=pageContext.getAttribute("pageId") %><br/>
	reqId : <%=request.getAttribute("reqId") %><br/>
	sessionId : <%=session.getAttribute("sessionId") %><br/>
	applicationId : <%=application.getAttribute("appId") %><br/>
	
	<a href="index.jsp">index</a><br/>

</body>
</html>