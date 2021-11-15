<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고 목록 보기</title>
</head>

<body>

테마 신고 목록 보기
<table border='1'>
<thead>
<tr>
  <th>번호</th>
  <th>신고당한 테마</th>
  <th>신고한 유저</th>
  <th>내용</th>
  <th>날짜</th>
  <th>상태</th>
</tr>
</thead>
  
<tbody>
  <c:forEach items="${themeList}" var="reportTheme">
  <tr>
    <td>${reportTheme.reportedTheme.no}</td>
    <td><a href='../theme/detail?no=${reportTheme.reportedTheme.no}'>${reportTheme.reportedTheme.title}</a></td>
    <td>${loginUser.nickname}</td>
    <td>${reportTheme.content}</td>
    <td>${reportTheme.registeredDate}</td>
    <td>${reportTheme.status.title}</td>
  </tr>
  </c:forEach>
</tbody>
</table>

<br>

유저 신고 목록 보기
<table border='1'>
<thead>
<tr>
  <th>번호</th>
  <th>신고당한 유저</th>
  <th>신고한 유저</th>
  <th>내용</th>
  <th>날짜</th>
  <th>상태</th>
</tr>
</thead>
  
<tbody>
  <c:forEach items="${userList}" var="reportUser">
  <tr>
    <td>${reportUser.reportedUser.no}</td>
    <td><a href='../theme/list?no=${reportUser.reportedUser.no}'>${reportUser.reportedUser.nickname}</a></td>
    <td>${loginUser.nickname}</td>
    <td>${reportUser.content}</td>
    <td>${reportUser.registeredDate}</td>
    <td>${reportUser.status.title}</td>
  </tr>
  </c:forEach>
</tbody>
</table>

</body>
</html>