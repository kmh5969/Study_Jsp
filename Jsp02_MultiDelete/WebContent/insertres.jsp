<%@page import="com.mul.biz.MDBoardBiz"%>
<%@page import="com.mul.dto.MDBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insertres</title>
</head>
<body>

<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MDBoardDto dto = new MDBoardDto(writer, title, content);
	MDBoardBiz biz = new MDBoardBiz();
	boolean res = biz.insert(dto);
	
	if(res){
%>
<script type="text/javascript">

	alert("글 작성 성공");
	location.href="boardlist.jsp";

</script>
<%
	} else {
%>
<script type="text/javascript">

	alert("글 작성 실패");
	location.href="insert.jsp";

</script>
<%
	}
%>

</body>
</html>