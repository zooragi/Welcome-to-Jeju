<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <style>
  .container {
    xborder : 1px solid red;
    width : 640px;
  }</style>

<div class = "container">

<h1>🤝${keyword}로 검색한 유저 목록 보기</h1>
<table class = "table table-hover">
<thead>
  <tr>
    <th>닉네임</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${userList}" var="user">
<tr>
<td><a href='../theme/userlist?no=${user.no}'>${user.nickname}님의 테마 목록</a></td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
