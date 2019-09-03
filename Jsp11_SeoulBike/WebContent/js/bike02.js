
$(function(){
	parseJson();
});

/*
 * JSON.parse() : JSON형식의 문자열 -> JSON Object
 * JSON.stringify : JSON Object -> JSON형식의 문자열
 */

function parseJson(){
	$.getJSON("json/bike.json",function(data){
		$.ajax({
			url : "bike.do?command=second_db",
			method : "post",
			data : {"obj" : JSON.stringify(data)},
			success : function(msg){
				// database에 저장을 성공하면 table을 만들자.
				alert(msg);
				if(msg == data.DATA.length){
					$.each(data,function(key,val){
						if(key=="DESCRIPTION"){
							$("table").attr("border","1");
							
							$("thead").append(
									"<tr>" +
										"<td>"+val.RENT_ID+"</td>"+
										"<td>"+val.ADDR_GU+"</td>"+
										"<td>"+val.CONTENT_ID+"</td>"+
										"<td>"+val.CONTENT_NM+"</td>"+
										"<td>"+val.NEW_ADDR+"</td>"+
										"<td>"+val.CRADLE_COUNT+"</td>"+
										"<td>"+val.LONGITUDE+"</td>"+
										"<td>"+val.LATITUDE+"</td>"+
									"</tr>"
							);
							
						} else if(key=="DATA"){
							var list = val;
							for(var i = 0; i < list.length; i++){
								var str = list[i];
								$("tbody").append(
									"<tr>" +
										"<td>"+str.rent_id+"</td>"+
										"<td>"+str.addr_gu+"</td>"+
										"<td>"+str.content_id+"</td>"+
										"<td>"+str.content_nm+"</td>"+
										"<td>"+str.new_addr+"</td>"+
										"<td>"+str.cradle_count+"</td>"+
										"<td>"+str.longitude+"</td>"+
										"<td>"+str.latitude+"</td>"+
									"</tr>"	
								);
							}
						}
					});
				}
				
			},
			error : function(){
				alert("실패");
			}
		});
	});
}