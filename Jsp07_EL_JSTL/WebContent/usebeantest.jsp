<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usebeantest</title>
</head>
<body>

	<h1>jsp:useBean을 통한 객체 생성</h1>
	
	<jsp:useBean id="sc" class="com.el.dto.Score" scope="session"></jsp:useBean>
	<!--
	 	java beans : 객체
	 	Score sc = new Score(); 
	 -->
	
	<jsp:setProperty property="name" name="sc" value="홍길동"/>
	<jsp:setProperty property="kor" name="sc" value="100"/>
	<jsp:setProperty property="eng" name="sc" value="50"/>
	<jsp:setProperty property="math" name="sc" value="77"/>
	<!-- 
		set : setter 호출
		property = "name" value="홍길동" : sc.setName("홍길동");
	 -->
	
	총점 : <jsp:getProperty property="sum" name="sc"/><br/>
	평균 : <jsp:getProperty property="avg" name="sc"/><br/>
	등급 : <jsp:getProperty property="grade" name="sc"/><br/>
	<!-- 
		get : getter 호출
		property="sum" : sc.getSum();
	 -->
	 
	 <button onclick="location.href='res.jsp'">res</button>

</body>
</html>