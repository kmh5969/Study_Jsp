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
<body>

   <a href="controller.do?command=basic">Expression Language</a>
   
   <a href="controller.do?command=eltest">EL Test</a>
   
   <a href="controller.do?command=jstltest">JSTL Test</a>
   
   <a href="controller.do?command=usebean">UseBean</a>

</body>
</html>