<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>글 작성</h1>
	
	<form action="mycontroller.jsp" method="post">
		<input type="hidden" name="command" value="insertres"/>
		<table border="1">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"/></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="60" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인"/>
					<input type="button" value="취소"  onclick="location.href='mycontroller.jsp?command=boardlist&i=1'"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>