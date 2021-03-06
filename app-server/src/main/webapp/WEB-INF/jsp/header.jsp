<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<header>
    <a class="logo" href="${contextRoot}/app/home">
        <button type="submit" class="login">
            <span class="title">๐ ์ ์ฃผ์ต์์</span>
        </button>
    </a>

    <div class="logout-box">
        <c:if test="${empty loginUser}">
            <form class="login_form" action='${contextRoot}/app/auth/loginform'>
                <button type="submit" class="login">๋ก๊ทธ์ธ</button>
            </form>
        </c:if>
        <c:if test="${!empty loginUser}">
                <button type="button" class="login">${loginUser.nickname }</button>
        </c:if>

        <button class="side-menu">
            <i class="fas fa-align-justify"></i>
        </button>
    </div>
</header>