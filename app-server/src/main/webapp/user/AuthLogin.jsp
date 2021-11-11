
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<head>

</head>
 <body>
<h1>로그인 성공!</h1>
<p>'${loginUser.nickname}' 님 환영합니다! 🙋</p>
<button><a href='../mytheme/list?no=${loginUser.no}'>나의 테마 목록 보기</a></button>
<button><a href='../auth/userinfo'>내 정보</a></button>
<button><a href='../auth/loginout'>로그아웃</a></button>

</body>
</html>
