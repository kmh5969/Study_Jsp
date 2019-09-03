<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>
	
	<form action="login.do" method="post">
		<input type="hidden" name="command" value="login"/>
		<table border="1">
			<tr>
				<th>I	D</th>
				<td><input type="text" name="myid"></td>
			</tr>
			<tr>
				<th>P	W</th>
				<td><input type="password" name="mypw"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="LOGIN"/>
					<input type="button" value="회원가입" onclick=""/>
				</td>
			</tr>
		</table>
	</form>

	<a href="my.do?command=list">list...</a>

</body>
</html>