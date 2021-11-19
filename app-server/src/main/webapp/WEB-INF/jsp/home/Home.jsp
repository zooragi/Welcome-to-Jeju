<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<jsp:include page="../template_head.jsp"/>
<link rel="stylesheet" href="${contextRoot}/css/home.css">
<script defer src="${contextRoot}/javascript/home.js"></script>
<body>

<main>
    <jsp:include page="../header.jsp"/>
    <jsp:include page="../sideBar.jsp"/>
    <div class="theme-slide">
        <button class="left">
            <i class="fas fa-angle-left"></i>
        </button>
        <ul class="slide-theme-content">
            <c:forEach items="${Top10Themes}" var="theme">
                <li>
                    <a href="theme/detail?no=${theme.no}" class="slide-theme-content-item">
                        <div class="icon">👅</div>
                        <div class="theme-title">${theme.title}</div>
                        <div class="curators">${theme.hashtags}</div>
                    </a>
                </li>
            </c:forEach>
        </ul>
        <button class="right">
            <i class="fas fa-angle-right"></i>
        </button>
    </div>
    <form id = "search-form" action = "${contextRoot}/app/search/theme">
    <div class="search-container">
        <input type="text" name="keyword" id="search-bar">
        <button class="search-icon">
            <i class="fas fa-search"></i>
        </button>
    </div>
    </form>
    <div class="dash-board">
        <div class="hot-curators">
            <div class="title">
                <span>핫 큐레이터</span>
                <div class="sub">최근 인기있는 큐레이터</div>
            </div>
            <ul class="hot-curators-list">
                <c:forEach items="${Top10User}" var="user">
                    <li>
                        <a href="theme/userlist?no=${user.no}" class="content">
                            <div class="icon">💀</div>
                            <div class="curator-name">${user.nickname}</div>
                            <div class="theme-count">${user.registeredDate}</div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="hot-theme">
            <div class="title">
                <span>최신 테마</span>
                <div class="sub">최근 등록된 테마</div>
            </div>
            <ul class="hot-theme-list">
                <c:forEach items="${latest10Theme}" var="theme">
                    <li>
                        <a href="theme/detail?no=${theme.no}" class="content">
                            <div class="icon">🥤</div>
                            <div class="theme-content">
                                <div class="theme-title">${theme.title}</div>
                                <div class="curators">${theme.hashtags}</div>
                            </div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>

    <div class="hot-place">
        <div class="title">
            <span>핫 지도</span>
            <div class="sub">최근 인기있는 지도</div>
        </div>
        <ul class="hot-place-list">
            <c:forEach items="${Top10Places}" var="place">
                <li>
                    <a href="#" class="content">
                        <div class="desc">
                            <div class="name">${place.storeName}</div>
                            <div class="address">${place.storeAddress}</div>
                        </div>
                        <div class="themes">
                            <c:forEach items="${place.themes}" var="placeBelongTheme" begin="0" end="2">
                                <div class="theme">${placeBelongTheme.title}</div>
                            </c:forEach>
                        </div>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</main>
<footer>
    <div class="footer-link">
        <a href="#" target="_blank">facebook</a>
        <a href="#" target="_blank">instagram</a>
        <a href="#">e-mail</a>
    </div>
    <div>* SNS를 통해 업데이트 소식을 알려드립니다</div>
    <br>
    <div><a href="#">개인정보처리방침</a></div>

</footer>
</body>
</html>