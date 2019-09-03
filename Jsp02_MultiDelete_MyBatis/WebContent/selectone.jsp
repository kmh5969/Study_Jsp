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
<title>selectone</title>
</head>
<%
	int seq = Integer.parseInt(request.getParameter("seq"));
	MDBoardBiz biz = new MDBoardBiz();
	MDBoardDto dto = biz.selectOne(seq);
%>

<script type="text/javascript">

	function del() {
		if(confirm("삭제 하시겠습니까?")){
			location.href="delete.jsp?seq=<%=seq %>"
		}
	}

</script>
<body>

	<%@ include file="./form/header.jsp" %>

	<h1>SelectOne Page</h1>
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<td><%=dto.getWriter() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='update.jsp?seq=<%=seq %>'"/> &nbsp;
				<input type="button" value="삭제" onclick="del()"/> &nbsp;
				<input type="button" value="목록" onclick="location.href='boardlist.jsp'"/>
			</td>
		</tr>
	</table>

	<%@ include file="./form/footer.jsp" %>
	
</body>
</html>