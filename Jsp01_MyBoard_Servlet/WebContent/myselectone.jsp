<%@page import="com.my.dto.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectone</title>
<%
	MyBoardDto dto = (MyBoardDto)request.getAttribute("dto");
%>

<script type="text/javascript">
	
	function del(){
		if(confirm("삭제 하시겠습니까?")){
			location.href="my.do?command=delete&myno=<%=dto.getMyno() %>";
		}
	}
	
</script>

</head>
<body>

	<h1>내용</h1>
	
	<table border="1">
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getMytitle() %></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='my.do?command=updateform&myno=<%=dto.getMyno() %>'"/>
				<input type="button" value="삭제" onclick="del()"/>
				<input type="button" value="목록" onclick="location.href='my.do?command=list'"/>
			</td>
		</tr>
	</table>

</body>
</html>