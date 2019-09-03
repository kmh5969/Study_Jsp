<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

	<a href="mvc.do?command=list">list</a><br>
	
	<a href="selectall">목록1</a><br>
	<a href="/selectall">목록2</a><br>
	<a href="./selectall">목록3</a><br>
	<a href="<%=request.getContextPath() %>">목록4</a>

</body>
</html>