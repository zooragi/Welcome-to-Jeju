<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  .container {
    width : 800px;
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
    <th>공개여부</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${allThemeList}" var="theme">

<tr>
<td><a href='../mytheme/detail?no=${theme.no}'>${theme.title}</a></td> 
<td><a href='../theme/userlist?no=${theme.owner.no}'>${theme.owner.nickname}</a></td>
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
 
<td>
<c:choose>
 <c:when test="${theme.isPublic eq '1'}">
 공개
 </c:when>
  <c:when test="${theme.isPublic eq '0'}">
 비공개
 </c:when>
 </c:choose>
</td> 

</tr>

</c:forEach>
 
</tbody>
</table>
</div><!--  .container -->
