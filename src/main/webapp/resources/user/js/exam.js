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

//function pop_layer_open()
//{	
//	document.getElementById('pop_layer').style.display = 'block';
//	document.getElementById('modal').style.display = 'block';
//}

function pop_layer_close()
{
//	document.getElementById('pop_layer').style.display = 'none';
	document.getElementById('modal').style.display = 'none';
}

//function pop_layer_on()
//{
//	document.getElementById('pop_layer').style.display = 'block';
//	document.getElementById('logout').style.display = 'none';
//	document.getElementById('login').style.display = 'none';
//}

function loginCheck()
{
	if(login == 1)
	{
	document.getElementById('login').style.display = 'none';
	}
	else 
	{	
	document.getElementById('logout').style.display = 'none';	
	}
}


//시간이 5시가 넘으면 배경색 검정색(야간모드) 나머지 시간은 배경색 초록색
function night_mode()
{
	var h = new Date();
//	alert('현재시간 :' + h.getHours());
    if(h.getHours()>=17 && h.getHours() <= 8){
		document.body.style.backgroundColor = 'black';
	} 
	else	
	{
		document.body.style.backgroundColor = 'green';		
	}
}

var myname = "gak";



function swOn(root)
{ 
 alert('aaa');
 document.getElementById('lightball').src=root + '/img/pic_bulbon.gif';
}

function swOff(root)
{
 document.getElementById('lightball').src=root + '/img/pic_bulboff.gif';
}

function caculator(a,b)
{
 var a,b,res;
// res=myname +9999;
// var num1=1,num2=2;
// var person ="gak";
// c=a-person;
// c=1;
// sum= num1++ + ++num2;
// sub=num1 -1;
// alert('sum= ' + sum + ',sub = ' +sub);
 res=add(a,b);
 document.getElementById('add').innerText=res;

 res=sub(a,b);
 document.getElementById('sub').innerText=res;

 res=mul(a,b);
 document.getElementById('mul').innerText=res;

 res=div(a,b);
 document.getElementById('div').innerText=res;

 res=rem(a,b);
 document.getElementById('rem').innerText=res;
 alert('res='+res);
}

function add(n1,n2)
{ 
 var adda=(n1+n2)/2;
 return adda;
}
function sub(a1,b1)
{
 return a1-b1;
}
function mul(a,b)
{
 return a*b;
}
function div(a,b)
{
 return a/b;
}
function rem(a,b)
{
 return a%b;
}








