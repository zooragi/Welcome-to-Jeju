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
<h1 style=text-align:center;>${user.nickname}님의 테마 목록 보기</h1>
<br>

<ul class="theme-list">
<c:forEach items="${themeList}" var="theme"
begin="0" end="${fn:length(themeList)-((fn:length(themeList))%3)-1}">
  <a class="list-container" href="../place/list?no=${theme.no}">
    <li>
    <div class="content">
      <div class="icon">${theme.emoji}</div>
      <div class="theme-title">${theme.title}</div>
      <div class="theme-count">${theme.hashtags}</div>
    </div>  <!-- .content -->
    </li>
  </a>
</c:forEach>
</ul>

</div>  <!-- .main-container -->
</div>  <!-- .container -->
