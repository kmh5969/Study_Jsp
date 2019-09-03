<%@page import="com.my.dao.MyBoardDao"%>
<%@page import="com.my.dto.MyBoardDto"%>
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
<title>myselectone</title>
</head>
<%
	int myno = Integer.parseInt(request.getParameter("myno"));
	String myname = request.getParameter("myname");
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	MyBoardDao dao = new MyBoardDao();
	MyBoardDto dto = dao.selectOne(myno);
%>

<script type="text/javascript">

	function del(){
		if(confirm("삭제하시겠습니까?")){
			location.href="mydelete.jsp?myno=<%=dto.getMyno() %>";
		}
	}

</script>

<body>

	<h1>Selectone Page</h1>
	
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
			<td><textarea rows="10" cols="60" name="mycontent" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno() %>'"/>&nbsp;
				<input type="button" value="삭제" onclick="del()"/>&nbsp;
				<input type="button" value="목록" onclick="location.href='mylist.jsp'"/>
			</td>
		</tr>
	</table>
	

</body>
</html>