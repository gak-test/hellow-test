/**
 * 
 */

var login = 0;

function formCheck()
{
	var login = document.forms["login"]["pw"].value;
	var pw = document.forms["login"]["pw"].value;
//	alert(pw);
	console.log('아이디 패스워드길이 : ' + pw.length + login.length);
	//아이디가 최소 5자리 이상일때
//	if(document.forms["login"]["pw"].value.length <5){
//	 document.getElementsByClassName("msg")[0].innerHTML
//	 = "<font color='red'>아이디 5자리 이상이어야합니다.</font>";
//	return false;
//	}
	if(login.length < 5 ){
	 document.getElementsByClassName("msg")[0].innerText 
	 = '아이디 5자리 이상이어야합니다.';
	 return false;
	}	
	
	if(pw.length < 5 ){
	 document.getElementsByClassName("msg")[0].innerText 
	 = '비밀번호 5자리 이상이어야합니다.';
	 return false;
	}	
	
	
	
}











