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
	
	<a href="selectall">���1</a><br>
	<a href="/selectall">���2</a><br>
	<a href="./selectall">���3</a><br>
	<a href="<%=request.getContextPath() %>">���4</a>

</body>
</html>