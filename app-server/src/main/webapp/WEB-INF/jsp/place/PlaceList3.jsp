<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<title>장소 리스트</title>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=667ac3a1fc68d5afe81179dea5f4ff16&libraries=services"></script>
	<script defer src="https://kit.fontawesome.com/a340a3bb10.js" crossorigin="anonymous"></script>
	<script defer type="text/javascript" src="${contextRoot}/javascript/place_list.js?ver=2"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${contextRoot}/css/place_list.css?ver=2">
</head>

<body>
	<div class="modal">
  	<div class="modal_body">
  		<div class="place_info_box">
		    <label for='f-place-name'>장소 이름</label>
		    <input id='place_name' type='text' name='place_name' readonly>
	    </div>
		  
	    <div class="place_info_box">
 			  <label for='f-address-name'>주소</label>
		    <input id='address_name' type='text' name='address_name' readonly>
	    </div>
	    
			<div class="place_info_box">
			  <label for='f-comment'>후기</label>
		    <div id='place_comment' rows="5" cols="30"></div>
			</div>
			
			<button class="place_cancel_btn" type="button">닫기</button>
		</div>
	</div>
  	
<div class="map_container full-height">
  <div class="map_wrap">
    <div id="map"></div>
      <div id="menu_wrap" class="bg_white">

<!-- 테마 상세 보기 여기부터 -->      
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
      <input id='f-title' type='text' name='title' value='${theme.title}' readonly>
      <a href='../likedtheme/add?themeNo=${theme.no}&userNo=${loginUser.no}'>📌</a><br>
      <a href='../report/themeform?no=${theme.no}'> 🚨</a><br>
      
      <label for='f-nickname'>닉네임</label>
      <input id='f-nickname' type='text' name='nickname' value='${theme.owner.nickname}' readonly> 
      <a href='../likeduser/add?userNo=${theme.owner.no}&themeNo=${theme.no}'>📌</a><br>
      <a href='../report/userform?no=${theme.no}'> 🚨</a><br>
      
      <label for='f-category'>카테고리</label>
      <input id='f-category' type='text' name='category' value='${theme.category.name}' readonly><br>
      
      <label for='f-hashtags'>해시태그</label>
      <input id='f-hashtags' type='text' name='hashtags' value='${theme.hashtags}' readonly><br>
    
      <a href='../'>[목록]</a>
      </form>
    </div>
  </div>
</div>  <!-- .themedetail -->
<!-- 여기까지 -->

        <ul id="placesList"></ul>
      </div>
    </div>
  </div>
  <form id="place_search_box" action="${contextRoot}/app/place/search">
    <div class="place_search_container">
      <input class="place_search" name="keyword" type="text" placeholder="장소를 검색하세요.">		
        <button class="search-icon">
          <i class="fas fa-search"></i>
        </button>
    </div>
  </form>
</body>

</html>
