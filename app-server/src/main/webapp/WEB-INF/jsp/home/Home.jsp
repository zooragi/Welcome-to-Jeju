<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html>
<title>제주 옵서예</title>
<jsp:include page="../template_head.jsp"/>
<link rel="stylesheet" href="${contextRoot}/css/home.css?ver=1">
<script defer src="${contextRoot}/javascript/home.js"></script>
<style>
.owl-dots{display:none;}
.owl-item{
}
.customPrevBtn, .customNextBtn{
    background : none;
    border: none;
    color: #999999;
    font-size: 20px;
    font-weight: bold;
}
@media(max-width:768px){
    .pbPrevBtn, .pbNextBtn{
        display: none;
    }
}
</style>	
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
                    <a href="place/list?no=${theme.no}" class="slide-theme-content-item">
                        <div class="icon">${emoji2}</div>
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
                <span>유저 순위</span>
                <div class="sub">인기 있는 유저</div>
            </div>
            <ul class="hot-curators-list .owl-carousel2">
                <c:forEach items="${Top10User}" var="user">
                    <li>
                        <a href="theme/userlist?no=${user.no}" class="content">
                            <div class="icon">${emoji3}</div>
                            <div class="curator-name">${user.nickname}</div>
                            <div class="theme-count">${user.registeredDate}</div>
                        </a>
                    </li>
                </c:forEach>
            </ul>
            </div>
        
        <div class="hot-theme">
            <div class="title">
                <span>테마 순위</span>
                <div class="sub">인기 있는 테마</div>
            </div>
            <ul class="hot-theme-list owl-carousel">
                <c:forEach items="${Top10Themes}" var="theme">
                    <li>
                        <a href="place/list?no=${theme.no}" class="content">
                            <div class="icon">🧚</div>
                            <div class="theme-content">
                                <div class="theme-title">${theme.title}</div>
                                <div class="curators">${theme.hashtags}</div>
                            </div>
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
                <ul class="hot-theme-list owl-carousel">
                <c:forEach items="${latest10Theme}" var="theme">
                    <li>
                        <a href="place/list?no=${theme.no}" class="content">
                            <div class="icon">${emoji4}</div>
                            <div class="theme-content">
                                <div class="theme-title">${theme.title}</div>
                                <div class="curators">${theme.hashtags}</div>
                            </div>
                        </a>
                    </li>
                </c:forEach>
                </ul>
             </div>
        
            <div class="hot-theme">
            <div class="title">
                <span>전체 테마</span>
                <div class="sub">전체 테마 목록</div>
            </div>
                <ul class="hot-theme-list owl-carousel">
                <c:forEach items="${allTheme}" var="theme">
                    <li>
                        <a href="place/list?no=${theme.no}" class="content">
                            <div class="icon">${emoji5}</div>
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
            <span>지도 순위</span>
            <div class="sub">인기있는 지도</div>
        </div>
        <ul class="hot-place-list owl-carousel">
            <c:forEach items="${Top10Places}" var="place">
                <li>
                    <a href="#" class="content">
                        <div class="desc">
                            <div class="name">${place.place_name}</div>
                            <div class="address">${place.address_name}</div>
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


    <script>
    $(document).ready(function(){
        var owl = $('.owl-carousel');
        
        owl.owlCarousel({
            items:3,                 // 한번에 보여줄 아이템 수
            loop:true,               // 반복여부
            margin:35,               // 오른쪽 간격
            autoplay:false,           // 자동재생 여부
            autoplayTimeout:1800,    // 재생간격
            autoplayHoverPause:true  //마우스오버시 멈출지 여부
        });    
        
        $('.customNextBtn').click(function() {
            owl.trigger('next.owl.carousel');
        })
        
        $('.customPrevBtn').click(function() {
            owl.trigger('prev.owl.carousel', [300]);
        })
    });
    $(document).ready(function(){
        var owl = $('.owl-carousel2');
        
        owl.owlCarousel({
            items:7,                 // 한번에 보여줄 아이템 수
            loop:true,               // 반복여부
            margin:35,               // 오른쪽 간격
            autoplay:false,           // 자동재생 여부
            autoplayTimeout:1800,    // 재생간격
            autoplayHoverPause:true  //마우스오버시 멈출지 여부
        });    
        
        $('.customNextBtn').click(function() {
            owl.trigger('next.owl.carousel');
        })
        
        $('.customPrevBtn').click(function() {
            owl.trigger('prev.owl.carousel', [300]);
        })
    });
    </script>

</body>
</html>