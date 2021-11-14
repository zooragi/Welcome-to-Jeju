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
<h1>💗해시태그 ${keyword}로 찾은 테마 목록</h1>
<table class = "table table-hover">
<thead>
  <tr>
    <th>테마 제목</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${themeList}" var="theme">
<tr>
<td><a href='../theme/detail?no=${theme.no}'>${theme.title}</a></td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
