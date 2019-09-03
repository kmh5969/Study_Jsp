<%@page import="com.mvc.dto.MVCBoardDto"%>
<%@page import="java.util.List"%>
<%@page import="com.mvc.dao.MVCBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mycontroller</title>
</head>
<body>

<%
	String command = request.getParameter("command");
	System.out.println("<" + command + ">");
	
	MVCBoardDao dao = new MVCBoardDao();
	
	if(command != null){
		
		if(command.equals("boardlist")){
			
			List<MVCBoardDto> listall = dao.selectList();
			
			int i = Integer.parseInt(request.getParameter("i"));
			int listsize = listall.size();
			int start = (i-1)*5 + 1;
			int end = i * 5;
			int pagenum = listsize/5;
			
			if(listsize%5 != 0){
				pagenum += 1;
			}
			
			if(end > listsize){
				end = listsize;
			}
			
			List<MVCBoardDto> list = dao.selectList(start, end);
			request.setAttribute("list", list);
			request.setAttribute("pagenum", pagenum);
			// forward : request, response 객체를 ()에 연결시켜줌
			pageContext.forward("boardlist.jsp");
			
		} else if(command.equals("insertform")) {
			
			response.sendRedirect("boardinsertform.jsp");
			
		} else if(command.equals("insertres")){
			
			String writer = request.getParameter("writer");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto(writer, title, content);
			
			int res = dao.insert(dto);
			
			if(res > 0){
				
%>
				<script type="text/javascript">

					alert("글 작성 성공");
					location.href="mycontroller.jsp?command=boardlist&i=1";

				</script>
<%
			} else {
%>
				<script type="text/javascript">

					alert("글 작성 실패");
					location.href="mycontroller.jsp?command=insertform";

				</script>
<%
			}
			
		} else if(command.equals("selectone")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			pageContext.forward("selectone.jsp");
			
		} else if(command.equals("updateform")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			MVCBoardDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			pageContext.forward("updateform.jsp");
			
		} else if(command.equals("updateres")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			MVCBoardDto dto = new MVCBoardDto();
			dto.setSeq(seq);
			dto.setTitle(title);
			dto.setContent(content);
			
			int res = dao.update(dto);
			
			if(res > 0) {
%>
			<script type="text/javascript">
			
			alert("글 수정 성공");
			location.href="mycontroller.jsp?command=selectone&seq=<%=dto.getSeq() %>";
			
			</script>
<%
			} else {
%>				
			<script type="text/javascript">
			
			alert("글 수정 실패");
			location.href="mycontroller.jsp?command=updateform&seq=<%=dto.getSeq() %>";
			
			</script>
<%
			}
		} else if(command.equals("delete")){
			
			int seq = Integer.parseInt(request.getParameter("seq"));
			int res = dao.delete(seq);
			
			if(res > 0){
%>
			<script type="text/javascript">
			
				alert("글 삭제 성공");
				location.href="mycontroller.jsp?command=boardlist&i=1";
			
			</script>
<%
			} else {
%>
			<script type="text/javascript">
			
				alert("글 삭제 실패");
				location.href="mycontroller.jsp?command=selectone&seq=<%=seq %>";
			
			</script>
<%
			}		
		}
	}
%>

	<h1>잘못왔습니다</h1>
	
</body>
</html>