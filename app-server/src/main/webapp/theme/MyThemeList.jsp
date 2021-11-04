<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>내 테마 목록</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"> </script>
  <style>
  .container {
    xborder : 1px solid red;
    width : 640px;
  }</style>
</head>
<body>
<div class = "container">
<h1>나의 테마 목록 보기</h1>
<a href='form' class ="btn btn-outline-primary btn-sm" >새 테마 만들기</a><br>
<table class = "table table-hover">
<thead>
  <tr>
    <th>제목</th>
    <th>카테고리</th>
    <th>해시태그</th>
    <th>조회수</th>
    <th>등록일</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${themeList}" var="theme">
<tr>
<td>${theme.title}</td> 
<td><a href='detail?no=${theme.no}'>${theme.title}</a></td> 
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
<td>${theme.viewCount}</td>
<td>${theme.isPublic}</td>
</tr>


<script>
"use strict"

var v1 = ${theme.isPublic};
function isPublic() { 
	for(var i = 0; i < ${themeList.size}; i++) {
		if(v1 = 1) {
			var ispublic = "공개 테마"
		}
	}
}
</script>
</c:forEach>

</tbody>
</table>
</div><!--  .container -->
</body>
</html>

