
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<head>

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

</head>

 <body>
 <div class = "container">
<h1>로그인하기</h1>
<form action='../auth/login' method='post'>
<label for='f-email'>이메일</label>  
<input id='f-email' type='email' name='email'><br>

<label for='f-password'>암호</label>  
<input id='f-password' type='password' name='password'><br>

<button>로그인</button>
<button><a href='../user/addform'>회원가입</a></button><br>


</form>
</div>
</body>
</html>
    