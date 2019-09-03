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
<title>update</title>
</head>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	MDBoardBiz biz = new MDBoardBiz();
	MDBoardDto dto = biz.selectOne(seq);
%>
<body>

	<%@ include file="./form/header.jsp" %>
	
	<h1>Update Page</h1>
	
	<form action="updateres.jsp">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><%=dto.getWriter() %></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" value="<%=dto.getTitle() %>" name="title"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인"/> &nbsp;
					<input type="button" value="취소" onclick="location.href='selectone.jsp?seq=<%=seq %>'"/>
					<input type="hidden" value="<%=dto.getSeq() %>" name="seq">
				</td>
			</tr>
		</table>
	</form>
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>