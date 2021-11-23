<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set scope="page" var="contextRoot" value="${pageContext.request.contextPath}"/>
<jsp:include page="../template_head.jsp"/>
<link rel="stylesheet" href="${contextRoot}/css/theme_list.css">
<link rel="stylesheet" href="${contextRoot}/css/home.css">
<script defer src="${contextRoot}/javascript/home.js"></script>

<div class="container">
<div class="main-container">

<br>
<h1 style=text-align:center;>🏆 테마 순위</h1>
<br>

<ul class="theme-list">
<c:forEach items="${themeList}" var="theme" varStatus="status">
<c:set var="i" value="${i+1}"/>
  <a class="list-container" href="place/list?no=${theme.no}">
  <td> 🏅 ${i}등 </td>
    <li>
    <div class="content">
      <div class="icon">${theme.emoji}</div>
      <div class="theme-title">${theme.title}</div>
      <div class="theme-count">${theme.hashtags}</div>
    </div>  <!-- .content -->
    </li>
  </a>
</c:forEach>  
<c:forEach begin="0" end="${3-(fn:length(themeList)%3)-1}">
    <a class="list-container" style=visibility:hidden;>
      <li>
        <div class="content">
          <div class="icon">${theme.emoji}</div>
          <div class="theme-title">${theme.title}</div>
          <div class="theme-count">#${theme.hashtags}</div>
        </div>  <!-- .content -->
      </li>
    </a>
</c:forEach>
</ul>

</div>  <!-- .main-container -->
</div>  <!-- .container -->

<div class="dash-board">
        <div class="hot-curators">
            <div class="title">
                <br>
                  <h1 style=text-align:center;>🏆 유저 순위</h1>
                <br>
            </div>
            <ul class="hot-curators-list">
                <c:forEach items="${userList}" var="user" varStatus="status">
                <c:set var="z" value="${z+1}"/>
                <td> 🏅 ${z}등 </td>
                    <li>
                        <a href="theme/userlist?no=${user.no}" class="content">
                            <div class="icon">${user.emoji}</div>
                            <div class="curator-name">${user.nickname}</div>
                            <div class="theme-count">${user.registeredDate}</div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            </div>
            </div>
