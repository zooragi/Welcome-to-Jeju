<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>테마 상세 보기</title>
  
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../css/common.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
  
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"></script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"></script>
  <script defer src="../javascript/common.js"></script>
  <script defer src="https://kit.fontawesome.com/a340a3bb10.js" crossorigin="anonymous"></script>
  
  <style>
    .themedetail {
    margin : 30px;
    float : left;
    }
    
    .placemenu {
    margin : 30px;
    float : right;
    }
  </style>
</head>

<body>

<jsp:include page="../../header.jsp"></jsp:include>
<jsp:include page="../../sideBar.jsp"></jsp:include>

<div class="themedetail">
  <p>
    <button class="btn btn-primary" type="button" data-bs-toggle="collapse" data-bs-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
      ${theme.title} 상세 보기 🔍
    </button>
  </p>
  <div class="collapse" id="collapseExample">
    <div class="card card-body">
      <form action='update'>
      <label for='f-no'>번호</label>
      <input id='f-no' type='text' name='no' value='${theme.no}' readonly><br>
      
      <label for='f-title'>제목</label>
      <input id='f-title' type='text' name='title' value='${theme.title}'><br>
      
      <label for='f-nickname'>닉네임</label>
      <input id='f-nickname' type='text' value='${loginUser.nickname}' readonly><br>
      
			<select class="form-select" id = "f-category" name = "category">
			<option selected>카테고리</option>
			<option value="1">식당</option>
			<option value="2">카페</option>
			<option value="3">관광명소</option>
			<option value="4">기타</option>
			</select>
 
 
		 <label for='f-hashtags'>해시태그</label>
		 <input id='f-hashtags' type='text' name='hashtags' value='${theme.hashtags}'><br>
		    
		<button>변경</button>
		<a href='delete?no=${theme.no}'>[삭제]</a>
		<a href='list?no=${user.no}'>[목록]</a>
      </form>
    </div>
  </div>
</div>  <!-- .themedetail -->

<div class="placemenu">
<a href='../place/add'>장소 등록하기 ✏️</a>
<br>
<jsp:include page="../../place/PlaceList.jsp"/>
</div>  <!-- .placemenu -->
</body>
</html>