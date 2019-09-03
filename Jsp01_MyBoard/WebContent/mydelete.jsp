<%@page import="com.my.dao.MyBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mydelete</title>
</head>
<body>
<%
	int myno = Integer.parseInt(request.getParameter("myno"));
	MyBoardDao dao = new MyBoardDao();
	
	int res = dao.delete(myno);
	
	if(res > 0){
%>
<script type="text/javascript">

	alert("글 삭제 성공");
	location.href="mylist.jsp";

</script>
<%
	} else {
%>
<script type="text/javascript">

	alert("글 삭제 실패");
	location.href="myselectone.jsp?myno=<%=myno %>"

</script>
<%
	}
%>
</body>
</html>