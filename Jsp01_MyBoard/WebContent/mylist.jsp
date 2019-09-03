<%@ page import="java.util.List"%>
<%@ page import="com.my.dao.MyBoardDao"%>
<%@ page import="com.my.dto.MyBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    	request.setCharacterEncoding("UTF-8");
    %>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mylist</title>
</head>
<%
	MyBoardDto dto = new MyBoardDto();
	MyBoardDao dao = new MyBoardDao();
	List<MyBoardDto> list = dao.selectList();
%>
<body>

	<h1>List Page</h1>
	
	<table border="1">
		<col width="50px">
		<col width="100px">
		<col width="200px">
		<col width="100px">
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>
<%
	for(int i = 0; i < list.size(); i++){
%>
		<tr>
			<td><%=list.get(i).getMyno() %></td>
			<td><%=list.get(i).getMyname() %></td>
			<td><a href="myselectone.jsp?myno=<%=list.get(i).getMyno() %>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate() %></td>
		</tr>
<%
	}
%>
		<tr>
			<td colspan="4"><input type="button" value="글쓰기" onclick="location.href='myinsert.jsp'"/></td>
		</tr>
	</table>

</body>
</html>