<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set scope="page" var="contextRoot" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${contextRoot}/css/theme_list.css">

  <style>
    .themeranking {
    margin : 30px;
    float : left;
    }
    
    .userranking {
    margin : 30px;
    float : left;
    }
  </style>

<div class="ranking">
<div class="themeranking">
  <br>
<h1>🏆 테마 순위 보기</h1>

<table class="table table-hover">
<thead>
<tr>
  <th>순위</th>
  <th>이름</th>
  <th>닉네임</th>
  <th>카테고리</th>
  <th>해시태그</th>
</tr>
</thead>
  
<tbody>
  <c:forEach items="${themeList}" var="theme" varStatus="status">
  <tr>
    <td>${status.count}</td>
    <td><a href='theme/detail?no=${theme.no}'>${theme.title}</a></td>
    <td><a href='theme/userlist?no=${theme.owner.no}'>${theme.owner.nickname}</a></td>
    <td>${theme.category.name}</td>
    <td>${theme.hashtags}</td>
  </tr>
  </c:forEach>
</tbody>
</table>
</div>

<div class="userranking">
  <br>
<h1>🏆 유저 순위 보기</h1>

<table class="table table-hover">
<thead>
<tr>
  <th>순위</th>
  <th>닉네임</th>
</tr>
</thead>
  
<tbody>
  <c:forEach items="${userList}" var="user" varStatus="status">
  <tr>
    <td>${status.count}</td>
    <td><a href='../theme/userlist?no=${user.no}'>${user.nickname}</a></td>
  </tr>
  </c:forEach>
</tbody>
</table>
</div>

</div>


