
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
    
<!DOCTYPE html>
<html>
<head>
   <title>로그인</title>
<style>
label {
margin-right: 5px;
text-align: right;
display: inline-block;
width: 60px;
}
</style>
</head>
 <body>
<h1>로그인</h1>
<form action='login'>
<label for='f-email'>이메일</label>  
<input id='f-email' type='email' name='email'><br>

<label for='f-password'>암호</label>  
<input id='f-password' type='password' name='password'><br>

<button>로그인</button><br>

</form>
</body>
</html>
    