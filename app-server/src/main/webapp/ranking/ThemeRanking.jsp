<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테마 순위 보기</title>
</head>

<body>

테마 순위 보기
<table border='1'>
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
    <td><a href='../theme/detail?no=${theme.no}'>${theme.title}</a></td>
    <td><a href='../theme/userlist?no=${theme.owner.no}'>${theme.owner.nickname}</a></td>
    <td>${theme.category.name}</td>
    <td>${theme.hashtags}</td>
  </tr>
  </c:forEach>
</tbody>
</table>

</body>

</html>