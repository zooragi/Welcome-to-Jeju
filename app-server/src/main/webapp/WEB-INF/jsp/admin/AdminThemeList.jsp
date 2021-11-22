<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  .container {
    width : 920px;
  }</style>
</head>

<div class = "container">
<h1>전체 테마 목록 보기</h1>

<h4>공개 테마</h4>
<table class = "table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>테마 만든이</th>
    <th>카테고리</th>
    <th>해시태그</th>
    <th>조회수</th>
    <th>신고수</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${themeList}" var="theme">
<c:choose>
<c:when test="${theme.isPublic eq '1'}">
<tr>
<td>${theme.no}</td>
<td><a href='../admin/themedetail?no=${theme.no}'>${theme.title}</a></td> 
<td><a href='../theme/userlist?no=${theme.owner.no}'>${theme.owner.nickname}</a></td>
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
<td>${theme.viewCount}</td> 
<td>${theme.reportedCount}</td>  
</tr>
</c:when>
 </c:choose>

</c:forEach>
 
</tbody>
</table>

<h4>비공개 테마</h4>
<table class = "table table-hover">
<thead>
  <tr>
    <th>번호</th>
    <th>제목</th>
    <th>테마 만든이</th>
    <th>카테고리</th>
    <th>해시태그</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${themeList}" var="theme">
<c:choose>
<c:when test="${theme.isPublic eq '0'}">
<tr>
<td>${theme.no}</td>
<td><a href='../admin/themedetail?no=${theme.no}'>${theme.title}</a></td> 
<td><a href='../theme/userlist?no=${theme.owner.no}'>${theme.owner.nickname}</a></td>
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
</tr>
</c:when>
 </c:choose>

</c:forEach>
 
</tbody>
</table>

</div><!--  .container -->
