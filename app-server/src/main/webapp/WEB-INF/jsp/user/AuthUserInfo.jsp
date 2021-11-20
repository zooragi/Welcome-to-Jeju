
<%@page import="com.welcomeToJeju.moj.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<style>
.info-form {
    width: 30%;
    /* border-radius: 6px; */
    /* box-shadow: 3px 3px; */
    background-color: transparent;
    /* background-color: #f8f8f8; */
    /* background-color: #F6BB43; */
    /* text-align: center; */
    margin: 0 auto;
    padding: 30px;
  }
</style>
    

<br>
<h1 style=text-align:center;>회원 정보</h1>
<br>

<div class = "info-form">
<form id = "update-form" action='../user/update' method='post'>


<div class="mb-3 row">
<h5>이메일</h5>
  <div class="col-sm-13">
  <input id='f-email' type='email' name='email' value='${loginUser.email}' class="form-control" readonly><br>
  </div>
</div>

<div class="mb-3 row">
<h5>암호</h5>
  <div class="col-sm-16">
  <input id='f-password' type='password' name='password' class="form-control" ><br>
  </div>
</div>

<div class="mb-3 row">
<h5>닉네임</h5>
  <div class = "col-sm-16">
  <input id='f-nickname' type='nickname' name='nickname' class="form-control" value = "${loginUser.nickname}">
  </div>
</div>

<div class="mb-3 row">
<h5>조회수</h5>
  <div class="col-sm-16">
  <input id='f-viewCount' type='text' name='viewCount' value='${loginUser.viewCount}' class="form-control"  readonly><br>
  </div>
</div>

<div class="mb-3 row">
<h5>경고</h5>
  <div class="col-sm-16">
  <input id='f-warningCount' type='text' name='warningCount' value='${loginUser.warningCount}' class="form-control"  readonly><br>
  </div>
</div>

<div class="mb-3 row">
<h5>가입일</h5>
  <div class="col-sm-16">
  <input id='f-registeredDate' type='date' name='registeredDate' value='${loginUser.registeredDate}' class="form-control"  readonly><br>
  </div>
</div>

<button id ="x-update-btn">회원 수정</button>
<button><a href='../user/delete?no=${loginUser.no}'>탈퇴하기</a></button>
<button><a href='logout'>로그아웃</a></button>

<!-- 
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


 -->
</form>
</div>



