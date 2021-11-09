
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>테마 만들기</title>
<link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../css/common.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
  
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script defer src="../javascript/common.js"></script>
  <script defer src="https://kit.fontawesome.com/a340a3bb10.js" crossorigin="anonymous"></script>
  
</head>
<body>

<jsp:include page="../../header.jsp"></jsp:include>
<jsp:include page="../../sideBar.jsp"></jsp:include>

<div class="container">
<h1>테마 만들기(MVC)</h1>
<form action='add'>
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

<select class="form-select" aria-label="카테고리" name ="category" >
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
</div><!--  .container -->
</body>
</html>
