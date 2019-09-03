<%@page import="com.mvc.dto.MVCBoardDto"%>
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
	MVCBoardDto dto = (MVCBoardDto)request.getAttribute("dto");
%>

<script type="text/javascript">

	function del(){
		if(confirm("삭제 하시겠습니까")){
			location.href='mycontroller.jsp?command=delete&seq=<%=dto.getSeq() %>'
		}
	}

</script>
<body>

	<h1>글 보기</h1>
	
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
				<input type="button" value="수정" onclick="location.href='mycontroller.jsp?command=updateform&seq=<%=dto.getSeq() %>'"/> &nbsp;
				<input type="button" value="삭제" onclick="del()"/> &nbsp;
				<input type="button" value="목록" onclick="location.href='mycontroller.jsp?command=boardlist&i=1'"/>
			</td>
		</tr>
	</table>

</body>
</html>