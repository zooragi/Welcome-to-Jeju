<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set scope="page" var="contextRoot" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet" href="${contextRoot}/css/theme_list.css">
<div class="container">
    <div class="main-container">
        <h1>테마 목록 보기</h1>
        <a href='../mytheme/addform' class ="btn btn-outline-primary btn-sm" >새 테마 만들기</a><br>
        <ul class="theme-list">
            <c:forEach items="${themeList}" var="theme" begin="0" end="${fn:length(themeList)-((fn:length(themeList))%3) -1}">
                <a class="list-container" href="#">
                    <li>
                        <div class="content">
                            <div class="icon">💀</div>
                            <div class="curator-name">${theme.owner.nickname}</div>
                            <div class="theme-count">${theme.hashtags}</div>
                        </div>
                    </li>
                </a>
            </c:forEach>
        </ul>
    </div>
</div><!--  .container -->