<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
  .container {
    xborder : 1px solid red;
    width : 1000px;
  }
  
  .card {
    display:inline-block;
    
  }
  
  .themedetail {
  
  xborder : 1px solid red;
  
  }
  
  
</style>

<div class = "container">
<h1>${loginUser.nickname}님의 테마 목록 보기</h1>
<a href='addform' class ="btn btn-outline-primary btn-sm" >새 테마 만들기</a><br>
<a href='../theme/list' class ="btn btn-outline-primary btn-sm" >테마 목록 보기</a><br>

<c:forEach items="${myThemeList}" var="theme">

<div class="card" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title">${theme.title}</h5>
    <h6 class="card-subtitle mb-2 text-muted">${theme.category.name}</h6>
    <p class="card-text">#${theme.hashtags}</p>
    <a href="detail?no=${theme.no}" class="themedetail">테마 상세보기</a>
  </div>
</div>
</c:forEach>
</div><!--  .container -->

<!-- 
<tr>
<td><a href='detail?no=${theme.no}'>${theme.title}</a></td> 
<td>${theme.category.name}</td> 
<td>${theme.hashtags}</td> 
<c:if test='${theme.isPublic ==1}'>
<td>${theme.viewCount}</td> 
</c:if>
<c:if test = '${theme.isPublic==0}'>
<td>비공개테마</td>
</c:if>
</tr>
</tbody>


</table>
 -->

