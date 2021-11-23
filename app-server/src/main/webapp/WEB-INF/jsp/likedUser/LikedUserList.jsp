<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<jsp:include page="../template_head.jsp"/>
<link rel="stylesheet" href="${contextRoot}/css/home.css">
<script defer src="${contextRoot}/javascript/home.js"></script>

  <div class="dash-board">
        <div class="hot-curators">
            <div class="title">
                <br>
                  <h1 style=text-align:center;>내가 좋아하는 유저 보기</h1>
                <br>
            </div>
            <ul class="hot-curators-list">
                <c:forEach items="${userList}" var="user">
                        <a href='delete?userNo=${user.no}'>⛔</a>
                    <li>
                        <a href="theme/userlist?userNo=${user.no}" class="content">
                            <div class="icon">${user.emoji}</div>
                            <div class="curator-name">${user.nickname}</div>
                            <div class="theme-count">${user.registeredDate}</div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            </div>
            </div>


