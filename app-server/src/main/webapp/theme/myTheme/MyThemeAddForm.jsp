
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 <style>
  .container {
  xborder: 1px solid red;
  width: 640px;
  }
  </style>

<div class = "container">
<h1>테마 만들기(MVC)</h1>
<form id ="theme-form" action='add' method = "post">
<div class="mb-3 row">
    <label for='f-title' class="col-sm-2 col-form-label">테마 제목</label>
    <div class="col-sm-6">
       <input id='f-title' type='text' name='title' class = "form-control" value = '${theme.title}'>
  </div>
</div>

<!-- 
<div class="mb-3 row">
    <label for='f-email' class="col-sm-2 col-form-label">카테고리</label>
    <div class="col-sm-10">
      <input id='f-email' type='text' name='name' class = "form-control">
  </div>
</div>
 -->

<select class="form-select" aria-label= "f-category" name ="category" >
  <option selected>카테고리</option>
  <option value="1">식당</option>
  <option value="2">카페</option>
  <option value="3">관광명소</option>
  <option value="4">기타</option>
</select>


<div class="mb-3 row">
    <label for='f-hashtag' class="col-sm-2 col-form-label">해시태그</label>
      <input id='f-hashtag' type='text' name='hashtag' class = "form-control" value = '${theme.hashtags}'>
</div>

<!--  
<div class="mb-3 row">
    <label for='f-photo' class="col-sm-2 col-form-label">공개 여부</label>
    <div class="col-sm-10">
      <input id='f-photo' type='text' name='name' class = "form-control">
  </div>
</div>
-->

<div class="form-check">
  <input class="form-check-input" type="radio" name="isPublic" id="f-isPublic" value = '1' checked>
  <label class="form-check-label" for="flexRadioDefault1">
   공개
  </label>
</div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="isPublic" id="f-notPublic" value = '0' >
  <label class="form-check-label" for="flexRadioDefault2">
    비공개
  </label>
</div>


<div>
  <label for='f-owner' class="col-sm-2 col-form-label">만든이</label>
  <input id='f-owner' type='text' name='owner' class = "form-control" value = '${loginUser.nickname}' readonly>

</div>

<button class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!--  .content -->

<script>
document.querySelector("#theme-form").onsubmit = () => {
  if (document.querySelector("#f-title").value == "" ||
      document.querySelector("#f-category").value == "" ||) {
    window.alert("필수 입력 항목이 비어 있습니다.")
    //Swal.fire("필수 입력 항목이 비어 있습니다.")
    return false;
  }
};

</script>

