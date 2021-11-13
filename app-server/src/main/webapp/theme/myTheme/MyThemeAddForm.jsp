
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 <style>
  .container {
  xborder: 1px solid red;
  width: 800px;
  }
 
  .form-select {
    width:110px;
    display:inline-block;
    margin-right: 10px;
    
  }
  
  .publicCheck {
    display:inline-block;
  }

  
  .title {
    width:133px;
  }
  
  .form-check {
    display: inline-block;
    margin-right: 10px;
  
  }
</style>

<script type="text/javascript">
function check() {
  if(!document.f-title.title.value) {
    alert("필수 입력 항목이 비어있습니다.");
  return false;
  }
};
</script>
  

<div class = "container">
<h1>테마 만들기</h1>
<form id ="theme-form" name = "theme-form" action='add' method = "post" onsubmit = "return check()">

<div class = "t">
<div class="mb-3 row">
    <label for='f-title' class="col-sm-2 col-form-label title">테마 제목</label>
    <div class="col-sm-6">
       <input id='f-title' type='text' name='title' class = "form-control" >
    </div>
</div>
</div>




<div class="mb-3 row">
    <label for='f-hashtag' class="col-sm-2 col-form-label">해시태그</label>
    <div class = "col-sm-6">
      <input id='f-hashtag' type='text' name='hashtags' class = "form-control" value = '${theme.hashtags}'>
  </div>
</div>


<select class="form-select" id = "f-category" aria-label= "f-category" name ="category" >
  <option selected>카테고리</option>
  <option value="1">식당</option>
  <option value="2">카페</option>
  <option value="3">관광명소</option>
  <option value="4">기타</option>
</select>

<div class = "publicCheck">
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
</div>

<p>
공개 여부는 추후에 변경이 불가능합니다!
</p>
<p>
공개 테마는 다른 유저들과 공유가 가능한 테마입니다. 
</p>
<p>비공개 테마는 큐레이터님께만 보이는 테마입니다.
</p>
<div class="mb-3 row">
  <label for='f-owner' class="col-sm-2 col-form-label">만든이</label>
 <div class = "col-sm-6">
  <input id='f-owner' type='text' name='owner' class = "form-control" value = '${loginUser.nickname}' readonly>
  </div>
</div>

<button type = "submit" class="btn btn-primary btn-sm">등록</button><br>
</form>
</div><!--  .content -->

<!-- 
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
 -->

