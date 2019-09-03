
function allChk(bool){
	var chks = document.getElementsByName("chk");
	for(var i = 0; i < chks.length; i++){
		chks[i].checked=bool;
	}
}

$(function(){
	$("input[name=chk]").click(function(){
		var chks = $("input[name=chk]").length;
		var checks = $("input[name=chk]:checked").length;
		var all = $("input[name=all]").eq(0);
		
		if(chks == checks){
			all.prop("checked",true);
		} else {
			all.prop("checked",false);
		}
	});
	
	$("#mulDelForm").submit(function(){
		if(confirm("삭제 하시겠습니까?")){
			if($("input[name=chk]:checked").length == 0){
				alert("하나이상 선택해 주세요");
				
				return false
			}
		}
	});
});

