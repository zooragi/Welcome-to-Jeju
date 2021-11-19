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
	<script defer type="text/javascript" src="${contextRoot}/javascript/placeList.js?ver=2"></script>
	<link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${contextRoot}/css/placeList.css">
</head>

<body>
	<div class="container full-height">
		<div class="map_wrap">
			<div id="map"></div>
				<div id="menu_wrap" class="bg_white">
					<ul id="placesList"></ul>
				</div>
			</div>
		</div>
	
<script>
console.log("html");
</script>
</body>

</html>