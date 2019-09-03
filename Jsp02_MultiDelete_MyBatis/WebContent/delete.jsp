<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="com.mul.biz.MDBoardBiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>delete</title>
</head>
<body>

<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	MDBoardBiz biz = new MDBoardBiz();
	boolean res = biz.delete(seq);
	
	if(res){
%>
	<script type="text/javascript">

		alert("글 삭제 성공");
		location.href="boardlist.jsp";

	</script>
<%
	} else {
%>
	<script type="text/javascript">

		alert("글 삭제 실패");
		location.href="selectone.jsp?seq=<%=seq %>";

	</script>
<%
	}
%>

</body>
</html>