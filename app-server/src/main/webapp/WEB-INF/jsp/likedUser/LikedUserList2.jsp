<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
  h1 {
    text-align: center;
  }
</style>

<br>
<h1>💙 유저 좋아요 목록 보기</h1>

<table class="table table-hover">
<thead>
<tr>
  <th></th><th></th><th></th>
  <th>번호</th>
  <th>닉네임</th>
  <th>🤍[취소]</th>
</tr>
</thead>

<tbody>
  <c:forEach items="${userList}" var="user">
  <tr>
    <td></td><td></td><td></td>
    <td>${user.no}</td>
    <td><a href='../theme/userlist?no=${user.no}'>${user.nickname}</a></td>
    <td><a href='delete?no=${user.no}'>🧡</a></td>
  </tr>
  </c:forEach>
</tbody>
</table>


