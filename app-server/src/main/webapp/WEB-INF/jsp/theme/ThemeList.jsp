<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  .container {
    width : 640px;
  }</style>
</head>

<div class = "container">
<h1>테마 목록 보기</h1>
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

<c:forEach items="${themeList}" var="theme">
<tr>
<td>

<c:choose>  
  <c:when test="${theme.owner.no eq loginUser.no}">
    <a href ="../mytheme/detail?no=${theme.no}"> ${theme.title}</a>
  </c:when> 
  <c:otherwise> 
    <a href = "detail?no=${theme.no}">${theme.title}</a>
  </c:otherwise> 
</c:choose>
</td>
<td>${theme.owner.nickname}</td>
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->