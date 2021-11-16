
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<style>

.container {
 xwidth: 680px;
  xmargin: 100px 300px 100px 500px;
  xpadding: 100px;
  
}

.card {
  xpadding:100px;
  margin:100px 200px 100px 400px;
}

</style>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

  </head>

<body cellpadding="0" cellspacing="0" marginleft="0" margintop="0" width="100%" height="100%" align="center">
<div class = "container">
  <div class="card align-middle" style="width:20rem; border-radius:20px;">
    <div class="card-title" style="margin-top:30px;">
      <h2 class="card-title text-center" style="color:#113366;">🔐 로그인하기</h2>
    </div>
    <div class="card-body">
      <form class="form-signin" action='../auth/login' method="POST" onSubmit="logincall();return false">
        <h5 class="form-signin-heading">로그인 정보를 입력하세요</h5>
        <label for="inputEmail" class="sr-only">이메일</label>
        <input id='f-email' type='email' name='email' value="${cookie.email.value}" class="form-control" placeholder="Your ID" required autofocus><BR>
        
        <label for="inputPassword" class="sr-only">암호</label>
        <input type="password" id="f-password" name = "password" class="form-control" placeholder="Password" required><br>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" value="saveEmail" ${not empty cookie.email ? "checked":""}/> 이메일 저장
          </label>
        </div>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" style="color : #ffff; background-color:#F6BB43; border:none;">로 그 인</button>
    </form>
    <br>
      <form class="form-signin" action='../user/addform'>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" style="color : #ffff; background-color:#F6BB43; border:none;">회원가입</button><br>
     </form>
      
    </div>
  </div>

  <div class="modal">
  </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script> 
  </div>
  </body>
