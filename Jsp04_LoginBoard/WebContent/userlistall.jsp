<%@page import="com.mem.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>userlistall</title>
</head>
<%
	List<MemberDto> list = (List<MemberDto>)session.getAttribute("listall");
	MemberDto dto = (MemberDto)session.getAttribute("dto");
	int pageNum = (int)session.getAttribute("pageNum");
	int start = (int)session.getAttribute("start");
	int end = (int)session.getAttribute("end");
	int presentPage = (int)session.getAttribute("presentPage");
%>
<body>
	
	<h1>가입회원 정보</h1>
	
	
<% 
	for(int i = start; i <= end; i++){
%>
		<table border="1">
			<col width="100px">
			<col width="300px">
			<tr>
				<th>아이디</th>
				<td><%=list.get(i-1).getMyid() %></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><%=list.get(i-1).getMyname() %></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><%=list.get(i-1).getMyaddr() %></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><%=list.get(i-1).getMyphone() %></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><%=list.get(i-1).getMyemail() %></td>
			</tr>
			<tr>
				<th>탈퇴여부</th>
				<td><%=list.get(i-1).getMyenabled().equals("Y")? "가입":"탈퇴" %></td>
			</tr>
			<tr>
				<th>등급</th>
				<td><%=list.get(i-1).getMyrole().equals("USER")? "정회원":"준회원" %></td>
			</tr>
		</table><br/>
<%
	}
	
	for(int i = 1; i <= pageNum; i++){
%>
		<a href="logincontroller.jsp?command=userlistall&presentPage=<%=i %>"><%=i %> </a>
<%
	}
%>

		<br/><input type="button" value="메인" onclick="location.href='logincontroller.jsp?command=login&id=<%=dto.getMyid() %>&pw=<%=dto.getMypw()%>'"/>

</body>
</html>