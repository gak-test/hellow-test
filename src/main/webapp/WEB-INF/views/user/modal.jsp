<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<div class="modal" id="modal">
 <div class="pop_layer"  id="pop_layer">
	<div class="pop_header">
		<div class="pop_title">로그인 창</div>
		<div class="pop_close" onclick="pop_layer_close()">
		<a href="#">close</a></div>
	</div>
 	
 	<div class="pop_content">
 		<form name="login" method="post" action="/member/login" onsubmit="return loginFormCheck()">
 		 
 		 <input type="text" name="id" placeholder="아이디" class="logpw" size="13" maxlength="15" style="color:green"
 		  		value="<%if( request.getParameter("id")!= null)
 		  		out.print(request.getParameter("id"));%>" required><br>
 		 <input type="password" name="pw" placeholder="비밀번호" class="logpw" size="13" maxlength="15" required><br><br>
 		 <p class="msg" id="msg"></p>
 		 <input type="submit" value="로그인" class="login_btn">
 		
 		</form>
 	<div>
 		<p> <a href="/member/memRegForm">[회원가입]</a></p> <p>[ID/PW찾기]</p>
 	
 	</div>
 	</div>
 </div>
</div>