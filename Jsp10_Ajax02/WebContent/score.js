
/*
 	<한글 사용 인코딩>
 	encodeURIComponent() : 모든 문자를 인코딩
 	encodeURI() : 주소줄에 사용되는 특수문자는 제외하고 인코딩
 */

function getParameterValues(){
	var name = "name="+encodeURIComponent(document.getElementById("name").value);
	var kor = "kor="+document.getElementById("kor").value;
	var eng = "eng="+document.getElementById("eng").value;
	var math = "math="+document.getElementById("math").value;
	
	return name+"&"+kor+"&"+eng+"&"+math;
}

function load(){
	var url = "score.do?"+getParameterValues();
	alert(url);
	
	httpRequest = new XMLHttpRequest();
	httpRequest.onreadystatechange = callback;
	httpRequest.open("GET",url,true);	// true : 비동기	/	false : 동기
	httpRequest.send();					// GET : send()	/	POST : send(String)
}

function callback(){
	alert("readystate : " + httpRequest.readyState);

	//if(httpRequest.readyState == 4 && httpRequest.status == 200)
	if(httpRequest.readyState == 4){
		alert("status : " + httpRequest.status);
		
		if(httpRequest.status == 200){
			// httpRequest.responseText : 응답받은 data
			var obj = JSON.parse(httpRequest.responseText);
			// JSON.parse
			// JSON.stringify
			
			document.getElementById("result").innerHTML = decodeURIComponent(obj.name) + "의 총점은 : " + obj.sum + ", 평균은 : " + obj.avg;
		}else{
			alert("데이터 계산 실패");
		}
	}
}

/*
 	XMLHttpRequest : javascript object. http를 통한 데이터 송수신 지원 (XHR)
 	
 	<onreadystatechange>
 	-readyState
 	0 : uninitialized - 실행(load) 되지 않음
 	1 : loading
 	2 : loaded
 	3 : interactive
 	4 : comlete
 	
 	-status
 	200 : 통신성공
 	403 : forbidden(권한이 없다.)
 	404 : not found
 	500 : internal server error(서버에서 null값 일때)
 */

















