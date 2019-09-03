<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registform</title>

<script type="text/javascript">

	function idChkConfirm(){
		var chk = document.getElementsByName("id")[0].title;
		if(chk == "n"){
			alert("아이디 중복체크를 해주세요");
			document.getElementsByName("id")[0].focus();
		}
	}
	
	function idChk(){
		var doc = document.getElementsByName("id")[0];
		
		if(doc.value.trim() == "" || doc.value == null){
			alert("아이디를 입력해 주세요");
		} else {
			open("logincontroller.jsp?command=idchk&id="+doc.value,"","width=400,height=200");
		}
	}

</script>

</head>
<body>

	<h1>회원가입</h1>
	
	<form action="logincontroller.jsp" method="post">
		<input type="hidden" name="command" value="registres">
		<table border="1">
			<tr>
				<th>아이디</th>
					<td>
						<input type="text" name="id" required="required" title="n"/>
						<input type="button" value="중복체크" onclick="idChk()"/>
					</td>
				</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pw" required="required" onclick="idChkConfirm()"></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="addr"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="확인"/>
					<input type="button" value="취소" onclick="location.href='logincontroller.jsp?command=index'"/>
				</td>
			</tr>
		</table>
	</form>

</body>
</html>