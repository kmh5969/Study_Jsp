<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>selectone</title>

<script type="text/javascript">

	function del(){
		if(confirm("삭제 하시겠습니까?")){
			location.href='deleteone?seq=${selectone.seq}'
		}
	}

</script>

</head>
<body>

	<h1>글 내용</h1>
	
	<table border="1">
		<tr>
			<th>작성자</th>
			<td>${selectone.writer }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${selectone.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="10" cols="60" readonly="readonly">${selectone.content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" onclick="location.href='updateform?seq=${selectone.seq}'"/>
				<input type="button" value="삭제" onclick="del()"/>
				<input type="button" value="목록" onclick="location.href='selectall'"/>
			</td>
		</tr>
	</table>

</body>
</html>