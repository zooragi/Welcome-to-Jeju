<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p>💙 유저 좋아요 목록 보기</p>
<table border='1'>
<thead>
<tr>
  <th>번호</th>
  <th>닉네임</th>
  <th>🤍[취소]</th>
</tr>
</thead>
	
<tbody>
	<c:forEach items="${userList}" var="user">
	<tr>
    <td>${user.no}</td>
    <td><a href='../theme/userlist?no=${user.no}'>${user.nickname}</a></td>
    <td>💙</td>
	</tr>
	</c:forEach>
</tbody>
</table>

