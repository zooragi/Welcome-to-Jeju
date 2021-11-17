<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set scope="page" var="contextRoot" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${contextRoot}/css/theme_list.css">

<div class="container">
<div class="main-container">

<br>
<h1 style=text-align:center;>나의 테마 목록 보기</h1>
<br>

<button type="button" class="btn btn-link">
  <a href="../mytheme/addform">만들기</a>
</button>

<ul class="theme-list">
	<c:forEach items="${themeList}" var="theme"
	begin="0" end="${fn:length(themeList)-((fn:length(themeList))%3) -1}">
		<a class="list-container" href="#">
			<li>
				<div class="content">
					<div class="icon">🌴</div>
					<div class="theme-title">${theme.title}</div>
					<div class="theme-count">${theme.hashtags}</div>
				</div>
			</li>
		</a>
	</c:forEach>
</ul>
</div>
</div><!--  .container -->

<!-- 
<style>
  .container {
    xborder : 1px solid red;
    width : 1000px;
    margin: 10px 100px 0px 320px;
    
  }
  
  .card {
    display:inline-block;
    
  }
  
  .themedetail {
  
  xborder : 1px solid red;
  
  }
  
  
</style>

<div class = "container">
<h1>📷${loginUser.nickname}님의 테마 목록 보기</h1>
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
</div> -->
<!--  .container -->

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

