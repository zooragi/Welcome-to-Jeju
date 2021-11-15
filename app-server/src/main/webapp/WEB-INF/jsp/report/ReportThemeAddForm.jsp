<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  h1 {
    text-align: center;
  }

	.add-form {
		width: 30%;
		border-radius: 6px;
		box-shadow: 3px 3px;
		background-color: #f8f8f8;
		/* background-color: #F6BB43; */
		/* text-align: center; */
		margin: 0 auto;
	}

	.mb-3 {
    padding: 20px 22% 0px 20px;
		text-align: right;
	}
	
	.f-button {
    padding-bottom: 20px;
    text-align: center;
	}
</style>

<br>
<h1>테마 신고하기</h1>

<div class="add-form">
  <form class="f-form" action="theme">
	  <div class="mb-3">
	  <label for='f-no'>번호</label>
	  <input id='f-no' type='text' name='no' value='${reportTheme.reportedTheme.no}'><br>
	  </div>
	  
	  <div class="mb-3">
	  <label for='f-title'>신고당한 테마</label>
	  <input id='f-title' type='text' name='title' value='${reportTheme.reprotedTheme.title}'><br>
	  </div>
	  
	  <div class="mb-3">
	  <label for='f-nickname'>신고한 유저</label>
	  <input id='f-nickname' type='text' name='nickname' value='${loginUser.nickname}' readonly><br>
	  </div>
	  
	  <div class="mb-3">
	  <textarea id='f-content' name='content' cols="32" placeholder="내용"></textarea><br>
	  </div>
  
    <div class="f-button">
	  <button>신고하기</button>
	  </div>
  </form>
</div>
