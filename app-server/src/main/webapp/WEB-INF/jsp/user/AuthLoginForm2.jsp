
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<style>
	.card-container{
		display : flex;
		justify-content: center;
		padding: var(--font-great) 0 ;
	}
	
	.btn{
	float:left;
	margin-left: 20px;
	margin-right: 20px;
	}
	
</style>

<div class="card-container">
  <div class="card align-middle" style="width:20rem; border-radius:20px;">
    <div class="card-title" style="margin-top:30px;">
      <h2 class="card-title text-center" style="color:#113366;">🔐 로그인하기</h2>
    </div>
    <div class="card-body">
      <form id = "form-signin" class="form-signin" action='../auth/login' method="POST" onSubmit="logincall();return false">
        <h5 class="form-signin-heading">로그인 정보를 입력하세요</h5>
        <label for="inputEmail" class="sr-only">이메일</label>
        <input id='f-email' type='email' name='email' value="${cookie.email.value}" class="form-control" placeholder="Your ID" required autofocus><BR>
        
        <label for="inputPassword" class="sr-only">암호</label>
        <input type="password" id="f-password" name = "password" class="form-control" placeholder="Password" required><br>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" name="saveEmail" ${not empty cookie.email ? "checked":""}/> 이메일 저장
          </label>
        </div>
        <br>
        <button id="btn-Yes" class="btn btn-lg btn-primary btn-block" type="submit" style="color : #ffff; background-color:#F6BB43; border:none;">로 그 인</button>
    </form>
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
