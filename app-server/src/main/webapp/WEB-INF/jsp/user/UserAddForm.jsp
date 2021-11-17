
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<style>
label {
margin-right: 5px;
text-align: right;
display: inline-block;
width: 60px;

}
</style>

 <style>
  .container {
  xborder: 1px solid red;
  width: 640px;
  }
  </style>




 <div class = "container">
<h1>회원 가입</h1>
<form id="user-form" action='add' method='post'>
<label for='f-email'>이메일</label>  <input id='f-email' type='email' name='email'><br>
<label for='f-password'>암호</label>  <input id='f-password' type='password' name='password'><br>
<label for='f-nickname'>닉네임</label>  <input id='f-nickname' type='nickname' name='nickname'><br>

<button class="btn btn-primary btn-sm">가입하기!</button>
<button><a href='../auth/loginform'>로그인!</a></button><br>
</form>
</div>


<script>
document.querySelector("#user-form").onsubmit = () => {
  if (document.querySelector("#f-email").value == "" ||
      document.querySelector("#f-password").value == "" ||
      document.querySelector("#f-nickname").value == "") {
    window.alert("필수 입력 항목이 비어 있음!")
    return false;
  }
};
</script>

    