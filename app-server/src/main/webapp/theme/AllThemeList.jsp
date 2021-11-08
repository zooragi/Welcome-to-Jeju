<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>테마 목록 보기</title>
  <link rel="stylesheet" href="../node_modules/bootstrap/dist/css/bootstrap.css">
  <link rel="stylesheet" href="../css/common.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
  
  <script defer src="https://kit.fontawesome.com/a340a3bb10.js" crossorigin="anonymous"></script>
  <script src = "../node_modules/@popperjs/core/dist/umd/popper.js"> </script>
  <script src = "../node_modules/bootstrap/dist/js/bootstrap.js"> </script>
  <script defer src="../javascript/common.js"></script>
  <style>
  .container {
    width : 800px;
  }</style>
</head>
<body>

<jsp:include page="../header.jsp"></jsp:include>
<jsp:include page="../sideBar.jsp"></jsp:include>

<div class = "container">
<h1>테마 목록 보기</h1>
<a href='myTheme/form' class ="btn btn-outline-primary btn-sm" >새 테마 만들기</a><br>
<table class = "table table-hover">
<thead>
  <tr>
    <th>제목</th>
    <th>테마 만든이</th>
    <th>카테고리</th>
    <th>해시태그</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${allThemeList}" var="theme">
<tr>
<td><a href='detail?no=${theme.no}'>${theme.title}</a></td> 
<td>${theme.owner.nickname}</td>
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
</body>
</html>

