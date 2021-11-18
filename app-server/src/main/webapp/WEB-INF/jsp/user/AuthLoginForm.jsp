
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<style>
label {
margin-right: 5px;
text-align: right;
display: inline-block;
width: 50px;
}

  .container {
  xborder: 1px solid red;
  width: 680px;
  xmargin: 100px;
  padding: 200px;
  }

.form-check-label {
margin-right: 5px;
text-align: left;
display: inline-block;
width: 100px;
}
</style>

<br>
<h1>🔐 로그인하기</h1>
<br>
<form action='../auth/login' method='post'>
<label for='f-email'>이메일</label>  
<input id='f-email' type='email' name='email' value="${cookie.email.value}"><br>

<label for='f-password'>암호</label>  
<input id='f-password' type='password' name='password'><br>

<div class="form-check">
  <input class="form-check-input" type="checkbox" name="saveEmail" ${not empty cookie.email ? "checked":""}>
  <label class="form-check-label" for="flexCheckDefault">
이메일 저장
  </label>
</div>

<button><a href='../user/addform'>회원가입</a></button><br>
<button><a href='../admin/loginform'>관리자 로그인</a></button><br>


</form>

</body>
</html>
    