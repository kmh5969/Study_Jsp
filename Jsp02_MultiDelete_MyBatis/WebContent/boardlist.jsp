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
<title>boardlist</title>
<%
	MDBoardBiz biz = new MDBoardBiz();
	List<MDBoardDto> list = biz.selectList();
%>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">

	function allChk(bool){
		var chks = $("input[name=chk]");
		for(var i = 0; i < chks.length; i++){
			chks[i].checked = bool;
		}
	}
	
	$(function(){
		$("#muldelform").submit(function(){
			if($("#muldelform input:checked").length == 0){
				alert("하나 이상 체크해 주세요!!");
				return false;
			}
		});
		
		$("input[name=chk]").click(function(){
			var cnt = 0;
			var chks = $("input[name=chk]").length;
			var chkl = $("input[name=chk]:checked").length;
			var all = $("input[name=all]").eq(0);
			
			if(chkl == chks){
				all.prop("checked",true);
			} else {
				all.prop("checked",false);
			}
		});
		
	});
	
	function seaTitle(){
		var sea = $("#seach").val();
		location.href="seachtitle.jsp?sea="+sea;
	}

</script>

</head>

<body>

	<%@ include file="./form/header.jsp" %>
	
	<h1>글 목록</h1>
	
	<form action="muldel.jsp" method="get" id="muldelform">
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
					<input type="submit" value="삭제"/>
					<input type="button" value="글쓰기" onclick="location.href='insert.jsp'"/>
					<input type="text" id="seach">
					<input type="button" id="seachBtn" value="검색" onclick="seaTitle()">
				</td>
			</tr>
		</table>
	</form>
	
	<%@ include file="./form/footer.jsp" %>

</body>
</html>