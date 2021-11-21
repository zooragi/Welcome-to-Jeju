<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<style>
  .container {
    xborder : 1px solid red;
    width : 840px;
  }
  </style>

<div class = "container">
<h1>유저 목록</h1>

<h4>활동 중인 유저</h4>
<table class = "table table-hover">
<thead>
<tr>
	<th>번호</th>
	<th>닉네임</th>
	<th>이메일</th>
	<th>등록일</th>
	<th>조회수</th>
	<th>경고수</th>
	<th>신고수</th>
	<th>상태</th>
</tr>
</thead>
<tbody>

<c:forEach items="${userList}" var="user">
<c:choose>
<c:when test="${user.active eq '1'}">
<tr>
    <td>${user.no}</td>
    <td><a href='userdetail?no=${user.no}'>${user.nickname}</a></td>
    <td>${user.email}</td>
    <td>${user.registeredDate}</td>
    <td>${user.viewCount}</td>
    <td>${user.warningCount}</td>
    <td>${user.reportedCount}</td>
    <td>회원</td>    
</tr>
</c:when>
</c:choose>
</c:forEach>
</tbody>
</table>

<h4>탈퇴한 유저</h4>
<table class = "table table-hover">
<thead>
<tr>
  <th>번호</th>
  <th>닉네임</th>
  <th>이메일</th>
  <th>등록일</th>
  <th>조회수</th>
  <th>경고수</th>
  <th>신고수</th>
  <th>상태</th>
</tr>
</thead>
<tbody>

<c:forEach items="${userList}" var="user">
<c:choose>
<c:when test="${user.active eq '0'}">
<tr>
    <td>${user.no}</td>
    <td><a href='userdetail?no=${user.no}'>${user.nickname}</a></td>
    <td>${user.email}</td>
    <td>${user.registeredDate}</td>
    <td>${user.viewCount}</td>
    <td>${user.warningCount}</td>
    <td>${user.reportedCount}</td>
    <td>탈퇴</td>    
</tr>
</c:when>
</c:choose>
</c:forEach>
</tbody>
</table>


</div><!-- .container -->


