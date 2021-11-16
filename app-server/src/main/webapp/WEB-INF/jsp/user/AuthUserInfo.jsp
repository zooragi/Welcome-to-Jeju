
<%@page import="com.welcomeToJeju.moj.domain.User"%>
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
<h1>회원 정보</h1>

<form action='../user/update' method='post'>

<label for='f-no'>번호</label>  
<input id='f-no' type='no' name='no' value='${loginUser.no}' readonly><br>

<label for='f-email'>이메일</label>  
<input id='f-email' type='email' name='email' value='${loginUser.email}' readonly><br>

<label for='f-password'>암호</label>  
<input id='f-password' type='password' name='password'><br>

<label for='f-nickname'>닉네임</label>  
<input id='f-nickname' type='text' name='nickname' value='${loginUser.nickname}'><br>

<label for='f-viewCount'>조회수</label>
<input id='f-viewCount' type='text' name='viewCount' value='${loginUser.viewCount}' readonly><br>

<label for='f-warningCount'>🚨 경고</label>
<input id='f-warningCount' type='text' name='warningCount' value='${loginUser.warningCount}' readonly><br>

<label for='f-registeredDate'>가입일</label>  
<input id='f-registeredDate' type='date' name='registeredDate' value='${loginUser.registeredDate}' readonly><br>

<button>회원 수정</button>
<button><a href='../user/delete?no=${loginUser.no}'>탈퇴하기</a></button>
<button><a href='logout'>로그아웃</a></button>
</form>
</div>
</body>
