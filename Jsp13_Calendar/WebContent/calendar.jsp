<%@page import="com.cal.dto.CalDto"%>
<%@page import="java.util.List"%>
<%@page import="com.cal.dao.CalDao"%>
<%@page import="com.cal.dao.Util"%>
<%@page import="java.time.DayOfWeek"%>
<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	a{text-decoration: none;}
	#calendar{
		border-collapse: collapse;
		border: 1px solid gray;
	}
	#calendar th{
		width: 80px;
		border: 1px solid gray;
	}
	#calendar td{
		width: 80px;
		height: 80px;
		border: 1px solid gray;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	.jja{
		color: gray;
	}
	#head{
		background-color: #03a9f4;
	}
	.clist > p{
		font-size: 5px;
		margin: 1px;
		background-color: pink;
	}
	.cPreview{
		position: absolute;
		top: -30px;
		left: 10px;
		background-color: pink;
		width: 40px;
		height: 40px;
		text-align: center;
		line-height: 40px;
		border-radius: 40px 40px 40px 1px;
	}

</style>

<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		$(".countview").hover(function(){
			
			var aCountView = $(this);
			var year = $(".y").text().trim();
			var month = $(".m").text().trim();
			var cDate = aCountView.text().trim();
			
			if(month.length == 1){
				month = "0"+month;
			}
			if(cDate.length == 1){
				cDate = "0"+cDate;
			}
			
			var yyyyMMdd = year + month + cDate;
			
			$.ajax({
				url : "CalCountAjax.do",
				type : "post",
				data : "id=kh&yyyyMMdd="+yyyyMMdd,
				dataType : "json",
				async : false,
				success : function(msg){
					var count = msg.cnt;
					aCountView.after("<div class='cPreview'>"+count+"</div>");
				},
				error : function(){
					alert("통신 실패")
				}
			});
			
		},function(){
			$(".cPreview").remove();
		});
	});

</script>

</head>
<%
	Calendar cal = Calendar.getInstance();

	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;
	int cnt = 0;
	
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	
	if(paramYear != null){
		year = Integer.parseInt(paramYear);
	}
	if(paramMonth != null){
		month = Integer.parseInt(paramMonth);
	}
	
	if(month > 12){
		month = 1;
		year++;
	}
	if(month < 1){
		month = 12;
		year--;
	}
	
	// "해당 년도, 해당 월, 해당월의 1일"에 해당하는 Calendar
	cal.set(year, month-1, 1);
	
	// 1일의 요일
	int dom = cal.get(Calendar.DAY_OF_WEEK);
	
	// 해당 월의 마지막 일(28, 29, 30, 31)
	// 4의 배수는 윤년, 100의 배수는 평년, 400의 배수는 윤년
	int lastDate = cal.getActualMaximum(Calendar.DATE);
	
	// 달력의 일정 표현
	CalDao dao = new CalDao();
	String yyyyMM = year + Util.isTwo(month+"");
	List<CalDto> clist = dao.getCalViewList("kh", yyyyMM);
	
	cal.set(year, month-2, 1);
	int beforeLast = cal.getActualMaximum(Calendar.DATE);
	

%>
<body>

	<table id="calendar">
		<caption>
			<a href="calendar.jsp?year=<%=year-1 %>&month=<%=month %>">◀◀</a>
			<a href="calendar.jsp?year=<%=year %>&month=<%=month-1 %>">◁</a>
			<span class="y"><%=year %></span>년
			<span class="m"><%=month %></span>월
			<a href="calendar.jsp?year=<%=year %>&month=<%=month+1 %>">▷</a>
			<a href="calendar.jsp?year=<%=year+1 %>&month=<%=month %>">▶▶</a>
		</caption>
		<tr id = "head">
			<th class="red">일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th class="blue">토</th>
		</tr>
		<tr>
<%
	for(int i = 1; i <= lastDate; i++){
		if(i == 1){
			for(int j = beforeLast-(dom-2); j <= beforeLast; j++){
				cnt++;
%>
				<td class="jja"><%=j %></td>
<%
			}
		}
		cnt++;
%>		
			<td>
				<a class="countview" href="cal.do?command=selectdate&year=<%=year %>&month=<%=month %>&date=<%=i %>" style="color:<%=Util.fontColor(i, dom)%>"><%=i %></a>
				<a href="insertcalboard.jsp?year=<%=year %>&month=<%=month %>&date=<%=i %>&lastday=<%=lastDate %>">
					<img alt="일정 추가" src="image/pen.png" style="width: 10px; height: 10px;"/>
				</a>
				<div class="clist">
					<%=Util.getCalView(i, clist) %>
				</div>
			</td>
<%
		
		if((cnt % 7) == 0){
%>
			</tr>
			<tr>
<%
		}
		
		if(i == lastDate){
			for(int k = 1; k <= cnt; k++){
				cnt++;
%>
				<td class="jja"><%=k %></td>
<%
				if((cnt % 7) == 0){
					break;
				}
			}
		}
	}
%>
		</tr>
	</table>

</body>
</html>