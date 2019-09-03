<%@page import="com.mem.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userupdateform</title>
</head>
<%
	MemberDto dto = (MemberDto)session.getAttribute("dto");
%>
<body>

	<h1>수정하기</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="updateres">
		<table border="1">
		<col width="100px">
		<col width="300px">
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pw" value="<%=dto.getMypw() %>"></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><input type="text" name="addr" value="<%=dto.getMyaddr() %>"></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type="text" name="phone" value="<%=dto.getMyphone() %>"></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><input type="text" name="email" value="<%=dto.getMyemail() %>"></td>
		</tr>
		<tr>
			<th>등급</th>
			<td><%=dto.getMyrole().equals("USER")? "정회원":"준회원" %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="수정"/>
				<input type="button" value="취소" onclick="location.href='logincontroller.jsp?command=userinfo'"/>
			</td>
		</tr>
	</table>
	</form>

</body>
</html>