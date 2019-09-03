<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>

<script type="text/javascript">

	function registForm(){
		location.href="logincontroller.jsp?command=registform";
	}

</script>

</head>
<body>

	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="login"/>
		<table border="1">
			<col width="100px"/>
			<col width="100px"/>
			<tr>
				<th>I	D</th>
				<td><input type="text" name="id"/></td>
			</tr>
			<tr>
				<th>P	W</th>
				<td><input type="password" name="pw"/></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="login"/>
					<input type="button" value="regist" onclick="registForm()"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>