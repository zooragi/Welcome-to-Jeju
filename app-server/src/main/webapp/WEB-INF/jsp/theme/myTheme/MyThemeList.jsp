<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set scope="page" var="contextRoot" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${contextRoot}/css/theme_list.css">

<div class="container">
<div class="main-container">

<br>
<h1 style=text-align:center;>나의 테마 목록 보기</h1>
<br>

<button type="button" class="btn btn-link">
  <a href="addform">만들기</a>
</button>

<ul class="theme-list">
<c:forEach items="${themeList}" var="theme"
begin="0" end="${fn:length(themeList)-((fn:length(themeList))%3)-1}">
	<a class="list-container" href="detail?no=${theme.no}">
    <li>
    <div class="content">
			<div class="icon">✈️</div>
			<div class="theme-title">${theme.title}</div>
			<div class="theme-count">${theme.hashtags}</div>
		</div>  <!-- .content -->
		</li>
  </a>
</c:forEach>
</ul>
<ul class="theme-list">
  <a class="list-container" href="addform">
    <li>
    <div class="content">
      <div class="icon">📝➕</div>
      <div class="theme-title">나의 테마 만들기</div>
    </div>  <!-- .content -->
    </li>
  </a>
</ul>

</div>  <!-- .main-container -->
</div>  <!-- .container -->

<!-- 

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
