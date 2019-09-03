<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mem.dto.MemberDto"%>
<%@page import="com.mem.dao.MemberDao"%>
<%@page import="com.mem.biz.Memberbiz"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>controller</title>
</head>
<body>
<%
	String command = request.getParameter("command");
	System.out.println("[ "+ command  +" ]");
	
	Memberbiz biz = new Memberbiz();
	
	if(command.equals("login")){
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDto login = biz.login(id, pw);
		
		if(login.getMyid() != null){
			
			// session
			session.setAttribute("dto", login);
			session.setMaxInactiveInterval(10*60);
			// session.setMaxInactiveInterval(sec);
			// sec초 동안 활동이 없으면 session의 'dto' 사라짐.
			// default : 30분
			// 음수 일 때, 무제한
			
			if(login.getMyrole().equals("ADMIN")){
				response.sendRedirect("adminmain.jsp");
				
			} else if(login.getMyrole().equals("USER")){
				response.sendRedirect("usermain.jsp");
				
			} else if(login.getMyrole().equals("GEUST")){
				response.sendRedirect("usermain.jsp");
			}
			
		} else {
%>
			<script type="text/javascript">

				alert("login 실패! id와 pw를 다시 확인해 주세요!");
				location.href="index.jsp";
			</script>
<%
		}
	} else if(command.equals("logout")){
		
		session.invalidate();
		// session 정보 삭제
		response.sendRedirect("index.jsp");
		
	} else if(command.equals("userinfo")){
		
		response.sendRedirect("userinfo.jsp");
		
	} else if(command.equals("update")){
		
		response.sendRedirect("userupdateform.jsp");
		
	} else if(command.equals("updateres")){
		
		String mypw = request.getParameter("pw");
		String myaddr = request.getParameter("addr");
		String myphone = request.getParameter("phone");
		String myemail = request.getParameter("email");
		
		MemberDto dto = (MemberDto)session.getAttribute("dto");
		dto.setMypw(mypw);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = biz.update(dto);
		
		if(res > 0){
%>
			<script type="text/javascript">
				alert("수정 성공");
				location.href="logincontroller.jsp?command=userinfo";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("수정 실패");
				location.href="logincontroller.jsp?command=update";
			</script>
<%
		}
	} else if(command.equals("delete")){

		MemberDto dto = (MemberDto)session.getAttribute("dto");
		
		int res = biz.delUser(dto);
		
		if(res > 0){
%>
			<script type="text/javascript">
				alert("탈퇴 성공");
				location.href="index.jsp";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("탈퇴 실패");
				location.href="logincontroller.jsp?command=userinfo";
			</script>
<%			
		}
	} else if(command.equals("registform")){
		
		response.sendRedirect("registform.jsp");
		
	} else if(command.equals("idchk")){
		
		String myid = request.getParameter("id");
		String res = biz.idChk(myid);
		
		boolean idnotused = true;
		
		if(res != null){
			idnotused = false;
		}
		
		response.sendRedirect("idchk.jsp?idnotused=" + idnotused);
	} else if(command.equals("registres")){
		
		String myid = request.getParameter("id");
		String mypw = request.getParameter("pw");
		String myname = request.getParameter("name");
		String myaddr = request.getParameter("addr");
		String myphone = request.getParameter("phone");
		String myemail = request.getParameter("email");
		
		MemberDto dto = new MemberDto();
		dto.setMyid(myid);
		dto.setMypw(mypw);
		dto.setMyname(myname);
		dto.setMyaddr(myaddr);
		dto.setMyphone(myphone);
		dto.setMyemail(myemail);
		
		int res = biz.insert(dto);
		
		if(res > 0){
%>
			<script type="text/javascript">
				alert("회원가입 성공");
				location.href="logincontroller.jsp?command=login&id=<%=myid %>&pw=<%=mypw %>";
			</script>
<%
		} else {
%>
			<script type="text/javascript">
				alert("회원가입 실패");
				location.href="logincontroller.jsp?command=registform";
			</script>
<%
		}
	} else if(command.equals("index")){
		
		response.sendRedirect("index.jsp");
		
	} else if(command.equals("userlistall")){
		
		List<MemberDto> list = biz.selectList();
		
		int presentPage = Integer.parseInt(request.getParameter("presentPage"));
		int listSize = list.size();
		int start = (presentPage - 1) * 4 + 1;
		int end = presentPage * 4;
		int pageNum = listSize/4;
		
		if(listSize % 4 != 0){
			pageNum += 1; 
		}
		
		if(end > listSize){
			
			end = listSize;
		}
		
		session.setAttribute("listall", list);
		session.setAttribute("pageNum", pageNum);
		session.setAttribute("start", start);
		session.setAttribute("end", end);
		session.setAttribute("presentPage", presentPage);
		response.sendRedirect("userlistall.jsp");
		
	} else if(command.equals("userlisten")){
		
		List<MemberDto> list = biz.selectList();
		List<MemberDto> listen = new ArrayList<MemberDto>();
		
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getMyenabled().equals("Y")){
				listen.add(list.get(i));
			}
		}
		
		int presentPage = Integer.parseInt(request.getParameter("presentPage"));
		int listSize = listen.size();
		int start = (presentPage - 1) * 4 + 1;
		int end = presentPage * 4;
		int pageNum = listSize/4;
		
		if(listSize % 4 != 0){
			pageNum += 1; 
		}
		
		if(end > listSize){
			
			end = listSize;
		}
		
		session.setAttribute("pageNum", pageNum);
		session.setAttribute("start", start);
		session.setAttribute("end", end);
		session.setAttribute("presentPage", presentPage);
		session.setAttribute("listen", listen);
		response.sendRedirect("userlisten.jsp");
		
	} else if(command.equals("updateroleform")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		MemberDto dto = biz.selectOne(myno);
		request.setAttribute("dto", dto);
		pageContext.forward("updateroleform.jsp");
		
	} else if(command.equals("updateroleres")){
		
		int myno = Integer.parseInt(request.getParameter("myno"));
		String myrole = request.getParameter("role");
		MemberDto dto = biz.selectOne(myno);
		
		int res = biz.updateRole(myno, myrole);
		
		if(res > 0){
%>
		<script type="text/javascript">
			
			alert("수정 성공");
			location.href="logincontroller.jsp?command=userlisten&presentPage=1";
		
		</script>
<%
		} else {
%>
		<script type="text/javascript">
			
			alert("수정 실패");
			location.href="logincontroller.jsp?command=updateroleform&myno=<%=myno %>";
		
		</script>
<%
		}
	}
%>

	<h1>잘못왔습니다!</h1>
	
</body>
</html>