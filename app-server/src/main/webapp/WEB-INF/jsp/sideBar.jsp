<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>

<div class="side-menu-bar">
<button class="close-button">
<i class="fas fa-times"></i>
</button>

<!-- 로그인 -->
<c:if test="${!empty loginUser}">
<div class="my-menu-box">
<ul class="my-menu-list">
  <a href = "${contextRoot}/app/auth/userinfo">
  <li><span class="emozi">📝</span><span class="name">내 정보</span></li>
  </a>
  <a href="${contextRoot}/app/mytheme/list?no=${loginUser.no}">
  <li><span class="emozi">🗺</span><span class="name">나의 테마</span></li>
  </a>
  <a href="${contextRoot}/app/likedtheme/list">
  <li><span class="emozi">📌</span><span class="name">좋아하는 테마</span></li>
  </a>
  <a href="${contextRoot}/app/likeduser/list">
  <li><span class="emozi">📌</span><span class="name">좋아하는 유저</span></li>
  </a>
</ul>
</div>
</c:if>

<div class="public-menu-box">
<ul class="public-menu-box-list">
  <a href="${contextRoot}/app/theme/list">
	<li><span class="emozi">🗺</span><span class="name">전체 테마 보기</span></li>
	</a>
	<a href="${contextRoot}/app/ranking">
	<li><span class="emozi">🏆</span><span class="name">순위</span></li>
	</a>
	<li><span class="emozi">🚨</span><span class="name">신고</span></li>
</ul>        
</div>

<!-- 관리자 -->
<c:if test="${loginUser.nickname eq '제주정승'}">
<div class="public-menu-box">
<ul class = "public-menu-box-list">
  <a href="${contextRoot}/app/admin/themelist">
  <li><span class="emozi">📝‍</span><span class="name">테마 관리</span></li>
  </a>
  <a href="${contextRoot}/app/admin/userlist">
  <li><span class="emozi">🗺</span><span class="name">회원 관리</span></li>
  </a>
  <li><span class="emozi">🚨</span><span class="name">신고 관리</span></li> 
</ul>
</div>
</c:if>

<!-- 로그인 -->
<c:if test="${!empty loginUser}">
<div class="logout-menu-box">
<ul class="logout-menu-box-list">
<li>
	<form action='${contextRoot}/app/auth/logout'>
	<span class="emozi">🚪</span><button type="submit" class="name">로그아웃</button>
	</form>
</li>
</ul>
</div>
</c:if>

<!-- 로그아웃 -->
<c:if test="${empty loginUser}">
<div class="logout-menu-box">
<ul class="logout-menu-box-list">
<li>
	<form action='${contextRoot}/app/auth/loginform'>
	<span class="emozi">🚪</span><button type="submit" class="name">로그인</button>
</form>
</li>
</ul>
</div>
</c:if>

</div>  <!-- .side-menu-bar -->