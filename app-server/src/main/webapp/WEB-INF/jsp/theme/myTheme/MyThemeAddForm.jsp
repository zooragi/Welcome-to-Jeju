<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<style>
  .add-form {
    /* width: 60%; */
    /* border-radius: 6px; */
    /* box-shadow: 3px 3px; */
    background-color: transparent;
    /* background-color: #f8f8f8; */
    /* background-color: #F6BB43; */
    /* text-align: center; */
    margin: 0 auto;
    padding: 30px;
  }
  
  /* .form-select {
    width: 66%;
  } */
</style>

<br>
<h1 style=text-align:center;>나의 테마 만들기</h1>
<br>

<div class="add-form">
<form id="theme-form" action="add" method="post" enctype="multipart/form-data">

<div class="mb-3 row">
<h4>테마 이름</h4>
	<!-- <label for='f-title' class="col-sm-2 col-form-label form-control-lg">테마 이름</label> -->
	<div class="col-sm-6">
	<input id='f-title' type='text' name='title' class="form-control" >
	</div>
</div>

<div class="mb-3 row">
<h4>닉네임</h4>
  <!-- <label for='f-owner' class="col-sm-2 col-form-label form-control-lg">닉네임</label> -->
  <div class = "col-sm-6">
  <input id='f-owner' type='text' name='owner' class="form-control" value="${loginUser.nickname}" readonly>
  </div>
</div>

<div>
<h4>카테고리</h4>
<select class="form-select" aria-label="f-category" id="f-category" name ="category" >
  <option selected value="">👇</option>
  <option value="1">식당</option>
  <option value="2">카페</option>
  <option value="3">관광명소</option>
  <option value="4">기타</option>
</select>
</div>

<div>
<h4>공개 여부</h4>
<select class="form-select" aria-label="f-isPublic" id="f-isPublic" name ="isPublic" >
  <!-- <option selected>👇</option> -->
  <option selected value="1">공개</option>
  <!-- <option value="1">공개</option> -->
  <option value="0">비공개</option>
</select>
</div>

<!-- 
<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" value ="1" id="f-isPublic" name="isPublic">
  <label class="form-check-label" for="flexCheckChecked">
  공개
  </label>
</div>

<div class="form-check form-check-inline">
  <input class="form-check-input" type="radio" value ="0" id="f-isNotPublic" name="isPublic">
  <label class="form-check-label" for="flexCheckDefault">
  비공개
  </label>
</div>
 -->

<div class="mb-3 row">
	<!-- <label for='f-hashtag' class="col-sm-2 col-form-label">해시태그</label> -->
	<h4>해시태그</h4>
	<div class="col-sm-6">
	<input id='f-hashtag' type='text' name='hashtags' class="form-control" value="">
	</div>
</div>

<button type="submit" class="btn btn-primary btn-sm">만들기</button>

</form>
</div>

<script>
document.querySelector("#theme-form").onsubmit = () => {
  if (document.querySelector("#f-title").value == "" ||
      document.querySelector("#f-category").value == "" ||
      document.querySelector("#f-hashtag").value == "") {
    window.alert("필수 입력 항목이 비어 있음!")
    return false;
  }
};
</script>
