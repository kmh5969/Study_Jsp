<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>datelist</title>

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/cal.js"></script>

</head>
<body>


	<h1><c:out value="${ymd }"/>의 일정</h1>
		
	<form action="cal.do" method="post" id="mulDelForm">
		<input type="hidden" name="command" value="muldel"/>
		<jsp:useBean id="util" class="com.cal.dao.Util"/>
			
		<table border="1">
			<col width="50px">
			<col width="50px">
			<col width="300px">
			<col width="200px">
			<col width="100px">
			<tr>
				<th><input type="checkbox" name="all" onclick="allChk(this.checked)"></th>
				<th>번	호</th>
				<th>제	목</th>
				<th>일	정</th>
				<th>작성일</th>
			</tr>
			<c:choose>
				<c:when test="${empty list }">
					<tr>
						<td colspan="5" style="text-align: center;">----------------------------일정이 없습니다----------------------------</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${list }" var="dto">
						<tr>
							<td style="text-align: center;"><input type="checkbox" name="chk" value="${dto.seq }"/></td>
							<td>${dto.seq }</td>
							<td><a href="cal.do?command=detail&seq=${dto.seq }">${dto.title }</a></td>
							<td>
								<jsp:setProperty property="toDates" name="util" value="${dto.mdate }"/>
								<jsp:getProperty property="toDates" name="util"/>
								<input type="hidden" name="ymd" value="${dto.mdate }">
							</td>
							<td>
								<fmt:formatDate value="${dto.regdate }" pattern="yyyy-MM-dd"/>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<tr>
				<td colspan="5">
					<input type="submit" value="삭제"/>
					<input type="button" value="돌아가기" onclick="location.href='cal.do?command=calendar'"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>