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
<title>updateres</title>
</head>
<body>

<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MDBoardDto dto = new MDBoardDto(seq, title, content);
	MDBoardBiz biz = new MDBoardBiz();
	boolean res = biz.update(dto);
	
	if(res){
%>
<script type="text/javascript">

	alert("글 수정 성공");
	location.href="selectone.jsp?seq=<%=seq %>"

</script>
<%
	} else {
%>
<script type="text/javascript">

	alert("글 수정 실패");
	location.href="update.jsp?seq=<%=seq %>"

</script>
<%
	}
%>

</body>
</html>