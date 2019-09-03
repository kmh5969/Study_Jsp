<%@page import="com.mul.dto.MDBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mul.biz.MDBoardBiz"%>
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
<%
	String title = request.getParameter("sea");
	MDBoardBiz biz = new MDBoardBiz();
	List<MDBoardDto> list = biz.seachTitle(title);
%>
<body>

	<table border="1">
			<col width="30px"/>
			<col width="50px"/>
			<col width="100px"/>
			<col width="300px"/>
			<col width="100px"/>
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked)"/></th>
				<th>번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>작성일</th>
			</tr>
<%
			if(list.size() == 0){
%>
				<tr>
					<td colspan="5">---------------작성된 글이 없습니다---------------</td>
				</tr>
<%
			} else {
				
				for(int i = 0; i < list.size(); i++){
%>
				<tr>
					<td style="text-align: center;"><input type="checkbox" name="chk" value="<%=list.get(i).getSeq() %>"/></td>
					<td><%=list.get(i).getSeq() %></td>
					<td><%=list.get(i).getWriter() %></td>
					<td><a href="selectone.jsp?seq=<%=list.get(i).getSeq() %>"><%=list.get(i).getTitle() %></a></td>
					<td><%=list.get(i).getRegdate() %></td>
				</tr>
<%
				}
			}
%>
			<tr>
				<td colspan="5">
					<input type="button" value="글쓰기" onclick="location.href='insert.jsp'"/>
				</td>
			</tr>
		</table>

</body>
</html>