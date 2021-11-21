
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
 <style>

 .card-container{
    display : flex;
    justify-content: center;
    padding: var(--font-great) 0 ;
  }
  
  .btn-block {
  float:left;
  margin-left: 20px;
  margin-right: 20px;
  }
  
  </style>
  
<div class="card-container">
  <div class="card align-middle" style="width:20rem; border-radius:20px;">
    <div class="card-title" style="margin-top:30px;">
      <h2 class="card-title text-center" style="color:#113366;">회원 가입</h2>
    </div>
<div class = "card-body">
<form id="user-form" action='add' method='post'>

<div class="mb-3 row">
<h5>이메일</h5>
  <div class = "col-sm-16">
  <input id='f-email' type='email' name='email' class="form-control" placeholder="Your Email" required autofocus> 
  <div class="invalid-feedback">
        이미 존재하는 이메일입니다.
    </div>
    </div>
  <div class="col-auto">
    <button id="x-email-check-btn" type="button" class="btn btn-outline-dark form-control" 
    style="color : #ffff; background-color:#F6BB43; border:none; margin-left: 197px; width: 85px;">중복검사</button>
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
    <button id="x-nickname-check-btn" type="button" class="btn btn-outline-dark form-control"
    style="color : #ffff; background-color:#F6BB43; border:none; margin-left: 197px; width: 85px;">중복검사</button>
  </div>
</div>


<button id="x-add-btn" class="btn btn-lg btn-primary btn-block" 
style = "color : #ffff; background-color:#F6BB43; border:none;">가입하기</button>
</form>

<form class = "form-login" action = "../auth/loginform">
<button class="btn btn-lg btn-primary btn-block"
style = "color : #ffff; background-color:#F6BB43; border:none;">로그인</button>
</form>
</div>
</div>
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

    