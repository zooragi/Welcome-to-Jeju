
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
 
.add-form {
    width: 30%;
    background-color: transparent;
    margin: 0 auto;
    padding: 30px;
  }
  .col-auto{
  display: flex;
  	width:100%;
  }
  .col-auto button {
  	justify-content: flex-end;
  	width:50%;
  }
  </style>

<br>
<h1 style=text-align:center;>회원 가입</h1>
<br>
<div class = "add-form">
<form id="user-form" action='add' method='post'>

<div class="mb-3 row">
<h5>이메일</h5>
  <div class="col-sm-16">
  <input id='f-email' type='email' name='email' class="form-control" > 
  <div class="invalid-feedback">
        이미 존재하는 이메일입니다.
    </div>
  </div>
  <div class="col-auto">
    <button id="x-email-check-btn" type="button" class="btn btn-outline-dark form-control">중복검사</button>
  </div>
</div>

<div class="mb-3 row">
<h5>암호</h5>
  <div class = "col-sm-16">
  <input id='f-password' type='password' name='password' class="form-control">
  </div>
</div>

<div class="mb-3 row">
<h5>닉네임</h5>
  <div class = "col-sm-16">
  <input id='f-nickname' type='nickname' name='nickname' class="form-control">
  <div class="invalid-feedback">
        이미 존재하는 닉네임입니다.
    </div>
  </div>
  <div class="col-auto">
    <button id="x-nickname-check-btn" type="button" class="btn btn-outline-dark form-control">중복검사</button>
  </div>
</div>
<!-- 
<label for='f-email'>이메일</label>  <input id='f-email' type='email' name='email'><br>
<label for='f-password'>암호</label>  <input id='f-password' type='password' name='password'><br>
<label for='f-nickname'>닉네임</label>  <input id='f-nickname' type='nickname' name='nickname'><br>
 -->
<button id="x-add-btn" class="btn btn-outline-dark form-control">가입하기</button><br>
<br>
<button class="btn btn-outline-dark form-control"><a href='../auth/loginform'>로그인!</a></button><br>
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

var addBtn = document.querySelector("#x-add-btn");
var emailTag = document.querySelector("#f-email");
addBtn.setAttribute("disabled", "disabled");

document.querySelector("#x-email-check-btn").onclick = () => {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function() {
      if (this.responseText == "false") {
          addBtn.removeAttribute("disabled");
          emailTag.classList.remove("is-invalid");
      } else {
        addBtn.setAttribute("disabled", "disabled");
        emailTag.classList.add("is-invalid");
      }
    })
    xhr.open("get", "checkEmail?email=" + emailTag.value);
    xhr.send();
};

var addBtn = document.querySelector("#x-add-btn");
var nicknameTag = document.querySelector("#f-nickname");
addBtn.setAttribute("disabled", "disabled");

document.querySelector("#x-nickname-check-btn").onclick = () => {
    var xhr = new XMLHttpRequest();
    xhr.addEventListener("load", function() {
      if (this.responseText == "false") {
          addBtn.removeAttribute("disabled");
          nicknameTag.classList.remove("is-invalid");
      } else {
        addBtn.setAttribute("disabled", "disabled");
        nicknameTag.classList.add("is-invalid");
      }
    })
    xhr.open("get", "checkNickname?nickname=" + nicknameTag.value);
    xhr.send();
};

</script>

    