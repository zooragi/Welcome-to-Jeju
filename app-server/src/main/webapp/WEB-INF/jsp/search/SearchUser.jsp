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

<h1>π€${keyword}λ‘ κ²μν μ μ  λͺ©λ‘ λ³΄κΈ°</h1>
<table class = "table table-hover">
<thead>
  <tr>
    <th>λλ€μ</th>
  </tr>
</thead>
<tbody>

<c:forEach items="${userList}" var="user">
<tr>
<td><a href='../theme/userlist?no=${user.no}'>${user.nickname}λμ νλ§ λͺ©λ‘</a></td> 
</tr>

</c:forEach>

</tbody>
</table>
</div><!--  .container -->
