
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
<h1>๐ ๋ก๊ทธ์ธํ๊ธฐ</h1>
<br>
<form action='../auth/login' method='post'>
<label for='f-email'>์ด๋ฉ์ผ</label>  
<input id='f-email' type='email' name='email' value="${cookie.email.value}"><br>

<label for='f-password'>์ํธ</label>  
<input id='f-password' type='password' name='password'><br>

<div class="form-check">
  <input class="form-check-input" type="checkbox" name="saveEmail" ${not empty cookie.email ? "checked":""}>
  <label class="form-check-label" for="flexCheckDefault">
์ด๋ฉ์ผ ์ ์ฅ
  </label>
</div>

<button><a href='../user/addform'>ํ์๊ฐ์</a></button><br>
<button><a href='../admin/loginform'>๊ด๋ฆฌ์ ๋ก๊ทธ์ธ</a></button><br>


</form>

</body>
</html>
    