<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>

<div class="side-menu-bar">
<button class="close-button">
<i class="fas fa-times"></i>
</button>

<!-- λ‘κ·ΈμΈ -->
<c:if test="${!empty loginUser}">
<div class="my-menu-box">
<ul class="my-menu-list">
  <a href = "${contextRoot}/app/auth/userinfo">
  <li><span class="emozi">π</span><span class="name">λ΄ μ λ³΄</span></li>
  </a>
  <a href="${contextRoot}/app/mytheme/list?no=${loginUser.no}">
  <li><span class="emozi">πΊ</span><span class="name">λμ νλ§</span></li>
  </a>
  <a href="${contextRoot}/app/likedtheme/list">
  <li><span class="emozi">π</span><span class="name">μ’μνλ νλ§</span></li>
  </a>
  <a href="${contextRoot}/app/likeduser/list">
  <li><span class="emozi">π</span><span class="name">μ’μνλ μ μ </span></li>
  </a>
</ul>
</div>
</c:if>

<div class="public-menu-box">
<ul class="public-menu-box-list">
  <a href="${contextRoot}/app/theme/list">
	<li><span class="emozi">πΊ</span><span class="name">μ μ²΄ νλ§ λ³΄κΈ°</span></li>
	</a>
	<a href="${contextRoot}/app/ranking">
	<li><span class="emozi">π</span><span class="name">μμ</span></li>
	</a>
	<li><span class="emozi">π¨</span><span class="name">μ κ³ </span></li>
</ul>        
</div>

<!-- κ΄λ¦¬μ -->
<c:if test="${loginUser.nickname eq 'μ μ£Όμ μΉ'}">
<div class="public-menu-box">
<ul class = "public-menu-box-list">
  <a href="${contextRoot}/app/admin/themelist">
  <li><span class="emozi">πβ</span><span class="name">νλ§ κ΄λ¦¬</span></li>
  </a>
  <a href="${contextRoot}/app/admin/userlist">
  <li><span class="emozi">πΊ</span><span class="name">νμ κ΄λ¦¬</span></li>
  </a>
  <li><span class="emozi">π¨</span><span class="name">μ κ³  κ΄λ¦¬</span></li> 
</ul>
</div>
</c:if>

<!-- λ‘κ·ΈμΈ -->
<c:if test="${!empty loginUser}">
<div class="logout-menu-box">
<ul class="logout-menu-box-list">
<li>
	<form action='${contextRoot}/app/auth/logout'>
	<span class="emozi">πͺ</span><button type="submit" class="name">λ‘κ·Έμμ</button>
	</form>
</li>
</ul>
</div>
</c:if>

<!-- λ‘κ·Έμμ -->
<c:if test="${empty loginUser}">
<div class="logout-menu-box">
<ul class="logout-menu-box-list">
<li>
	<form action='${contextRoot}/app/auth/loginform'>
	<span class="emozi">πͺ</span><button type="submit" class="name">λ‘κ·ΈμΈ</button>
</form>
</li>
</ul>
</div>
</c:if>

</div>  <!-- .side-menu-bar -->