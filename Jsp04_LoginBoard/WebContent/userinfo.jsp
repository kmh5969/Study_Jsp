<%@page import="com.mem.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userinfo</title>
</head>
<%
	MemberDto dto = (MemberDto)session.getAttribute("dto");
%>
<body>

	<h1><%=dto.getMyname() %>님의 정보</h1>
	
	<table border="1">
		<col width="100px">
		<col width="300px">
		<tr>
			<th>아이디</th>
			<td><%=dto.getMyid() %></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><%=dto.getMypw() %></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><%=dto.getMyname() %></td>
		</tr>
		<tr>
			<th>주소</th>
			<td><%=dto.getMyaddr() %></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%=dto.getMyphone() %></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=dto.getMyemail() %></td>
		</tr>
		<tr>
			<th>등급</th>
			<td><%=dto.getMyrole().equals("USER")? "정회원":"준회원" %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='logincontroller.jsp?command=update'"/>
				<input type="button" value="탈퇴" onclick="location.href='logincontroller.jsp?command=delete'"/>
				<input type="button" value="메인" onclick="location.href='logincontroller.jsp?command=login&id=<%=dto.getMyid() %>&pw=<%=dto.getMypw() %>'"/>
			</td>
		</tr>
	</table>

</body>
</html>