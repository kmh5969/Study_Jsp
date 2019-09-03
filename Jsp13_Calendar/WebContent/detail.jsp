<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>detail</title>

<script type="text/javascript">
	
	function del(){
		if(confirm("삭제 하시겠습니까?")){
			location.href="cal.do?command=delete&seq=${dto.seq }&ymd=${dto.mdate }"
		}
	}
	
</script>

</head>
<body>

	<h1>${dto.title }의 일정</h1>
	
	<table border="1">
		<tr>
			<th>제	목</th>
			<td>${dto.title }</td>
		</tr>
		<tr>
			<th>내	용</th>
			<td><textarea rows="10" cols="60" readonly="readonly">${dto.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='cal.do?command=updateform&seq=${dto.seq }'"/>
				<input type="button" value="삭제" onclick="del()"/>
				<input type="button" value="목록" onclick="location.href='cal.do?command=selectdate&year=${year }&month=${month }&date=${date }'"/>
			</td>
		</tr>
	</table>

</body>
</html>