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
<h1>π νλ§ μμ λ³΄κΈ°</h1>

<table class="table table-hover">
<thead>
<tr>
  <th>μμ</th>
  <th>μ΄λ¦</th>
  <th>λλ€μ</th>
  <th>μΉ΄νκ³ λ¦¬</th>
  <th>ν΄μνκ·Έ</th>
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
