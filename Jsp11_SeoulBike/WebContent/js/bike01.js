
$(function(){
	getJson();
});

function getJson(){
	$.getJSON("json/bike.json",function(data){
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
							"<input type='hidden' name='bike' value='" +
								str.rent_id+"/"+str.addr_gu+"/"+str.content_id+"/"+str.content_nm+"/"+str.new_addr+"/"+str.cradle_count+"/"+str.longitude+"/"+str.latitude+
							"'/>"+
						"</tr>"	
					);
				}
			}
		});
	});
}