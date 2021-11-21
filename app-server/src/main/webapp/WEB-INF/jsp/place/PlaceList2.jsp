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
	<link rel="stylesheet" href="${contextRoot}/css/place_list.css?ver=1">
</head>

<body>
	<div class="map_container full-height">
		<div class="map_wrap">
			<div id="map"></div>
				<div id="menu_wrap" class="bg_white">
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