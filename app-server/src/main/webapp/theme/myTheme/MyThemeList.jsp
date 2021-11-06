<%@page import="java.util.Collection"%>
<%@page import="com.welcomeToJeju.moj.domain.Theme"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테마 목록 보기</title>
</head>

<body>
<h1>테마 목록 보기(MVC)</h1>

<table border='1'>
<thead> <tr>
  <th>번호</th> <th>제목</th> <th>카테고리</th> <th>해시태그</th> <th>공개</th> <th>조회수</th>
</tr> </thead>
<tbody>
<% 
Collection<Theme> themeList = (Collection<Theme>) request.getAttribute("themeList");

for (Theme theme : themeList) { %>
<tr>
  <td><%=theme.getNo()%></td>
  <td><a href='detail?title=<%=theme.getTitle()%>'><%=theme.getTitle()%></a></td>
  <td><%=theme.getCategory().getName()%></td>
  <td><%=theme.getHashtags()%></td>
  <td><%=theme.getIsPublic()%></td>
</tr>
<% } %>
</tbody>
</table>
  
</body>
</html>
