/**
 * 
 */
function loginFormCheck(){
	if(document.forms["login"]["pw"].value.length < 5){
		document.getElementById("msg").innerText = 
		"패스워드 길이를 확인하세요.";
		document.forms["login"]["pw"].focus();
		return false;
	}
	
	
}


function idCheck(root_path){
	var id = document.forms["memReg"]["id"].value;
	if(id.length == 0){
		alert('아이디를 입력하세요');
		return;
	}
	var x = new XMLHttpRequest();
	x.onreadystatechange = function(){
		if(x.readyState === 4){
			if(x.status ===200){
				var msg = document.getElementById("idCheckMsg");
				document.getElementById("isIdCheck").value = x.responseText.trim();
				if(x.responseText.trim() ==="0"){
					msg.innerText = "사용가능한 아이디 입니다.";
				}else{
					msg.innerText = "중복된 아이디 입니다.";
				}
				
			}else{
				
				console.log('에러코드 : ' + x.status);
			}
		}
		
	};
//	x.open("GET", root_path +"/member/idCheck.jsp?id=" + id,true);
//	x.send();
//	x.open("GET", root_path +"/member/idCheck.jsp?id=" + id + "&pw" + pw,true);
//  비밀번호까지 가져오고싶을때 사용
//	회원가입 페이지에서 받은 ID값을 경로에 request.getParameter("id")설정되있는 변수값에 넣는다
	x.open("POST", root_path +"/member/idCheck.jsp",true);//포스트 방식
	x.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	x.send("id="+ id);//포스트방식 end
	
}


















