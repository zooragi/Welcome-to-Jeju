
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
      input{
        width:150px;
        height:30px;
        font-size:15px;
      }
</style>

<style>
  .container {
    xborder : 1px solid red;
    width : 640px;
  }</style>
    
</head>

 <body>

<div class="container">
<h1>회원 상세 보기</h1>
<form action='userupdate' method='post'>

<label for='f-no'>번호</label>  
<input id='f-no' type='no' name='no' value='${user.no}' readonly><br>

<label for='f-email'>이메일</label>  
<input id='f-email' type='email' name='email' value='${user.email}' readonly><br>

<label for='f-password'>암호</label>  
<input id='f-password' type='password' name='password'><br>

<label for='f-nickname'>닉네임</label>  
<input id='f-nickname' type='text' name='nickname' value='${user.nickname}'><br>

<label for='f-viewCount'>조회수</label>
<input id='f-viewCount' type='text' name='viewCount' value='${user.viewCount}' readonly><br>

<label for='f-warningCount'>🚨 경고</label>
<input id='f-warningCount' type='text' name='warningCount' value='${user.warningCount}' readonly><br>

<label for='f-registeredDate'>가입일</label>  
<input id='f-registeredDate' type='date' name='registeredDate' value='${user.registeredDate}' readonly><br>

<button>[회원수정]</button>

<a href='userdelete?no=${user.no}'>[회원삭제]</a>
<a href='userlist'>[목록]</a><br>

</form>
</div>

</body>
</html>