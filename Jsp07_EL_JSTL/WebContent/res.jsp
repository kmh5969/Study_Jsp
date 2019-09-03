<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>res</title>
</head>
<body>

	${sc.name }<br/>
	
	<jsp:useBean id="sc" class="com.el.dto.Score" scope="session"></jsp:useBean>
	
	등급 : <jsp:getProperty property="grade" name="sc"/>

</body>
</html>