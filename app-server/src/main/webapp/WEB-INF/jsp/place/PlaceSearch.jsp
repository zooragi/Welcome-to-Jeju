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
	<script defer type="text/javascript" src="${contextRoot}/javascript/place_search.js?ver=3"></script>
	<script src = "${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"> </script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${contextRoot}/css/place_search.css?ver=2">
</head>
<body>
	<div class="modal">
   <div class="modal_body">
 		<form action='add' method='post' enctype="multipart/form-data">
 		
		    <input id='place_id' value="" type='hidden' name='id' >
		    <input id='place_x' value="" type='hidden' name='x' >
		    <input id='place_y' value="" type='hidden' name='y' >
		    
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
			    <textarea id='place_comment' name='comment' class="form-control" rows="5" cols="30"></textarea>
				</div>
				
		    <div class="place_info_box">
			  	<label for='f-photo'>사진</label> 
	   	  	<input id='place_photo' type='file' name='photoFile' class="form-control" multiple>
				</div>
				
				<button class="place_add_btn" type="submit">저장</button>
				<button class="place_cancel_btn" type="button">취소</button>
   	</form>
   </div>
  </div>
	<div class="container1 full-height">
		<div class="map_wrap">
			<div id="map"></div>

				<div id="menu_wrap" class="bg_white">
					<div class="option">
						<div>
							<input type="text" value="${searchedPlace}" id="keyword" size="15"> 
							<button class="keywordSearhButton" >검색하기</button> 
						</div>
					</div>
					<hr>
					<ul id="placesList">
		    	</ul>
		    	<button class="placeButton" >선택하기</button>
					<div id="pagination"></div>

				</div>
			</div>
		</div>
</body>
</html>