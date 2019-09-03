
$(function(){
	$("#weaview").click(function(){
		var url = "weather.do";
		var code = $("#address option:selected").val();
		
		$.ajax({
			type : "GET",
			url : url+"?code="+code,
			dataType : "text",
			success : function(data){
				var tmp = $.trim(data);
				var obj = JSON.parse(tmp);
				
				$("#pubDate").val(obj.pubDate);
				$("#temp").val(obj.temp);
				$("#x").val(obj.x);
				$("#y").val(obj.y);
				$("#reh").val(obj.reh);
				$("#pop").val(obj.pop);
				$("#wfKor").val(obj.wfKor);
				
				var weather_condition = obj.wfKor;
				var img = $("#weather_img");
				
				if(weather_condition == "맑음"){
					img.attr("src","image/sun.png");
				} else if(weather_condition == "비"){
					img.attr("src","image/rain.png");
				} else if(weather_condition == "눈"){
					img.attr("src","image/snow.png");
				} else if(weather_condition == "흐림"){
					img.attr("src","image/cloud.png");
				} else if(weather_condition == "구름 조금"){
					img.attr("src","image/cloud_sun.png");
				} else {
					img.attr("src","image/etc.png");
				}
			},
			error : function(){
				alert("통신 실패")
			}
		});
	});
});