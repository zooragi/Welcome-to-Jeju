
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<head>

</head>
 <body>
<h1>로그인 성공!</h1>
<p>'${loginUser.nickname}' 관리자님 환영합니다! 🙋</p>
<button><a href='../admin/alltheme'>전체 테마 목록 보기</a></button><br>
<button><a href='../admin/list'>회원 목록 보기</a></button><br>
<button><a href='../auth/userinfo'>내 정보</a></button>
<button><a href='../auth/loginout'>로그아웃</a></button>

</body>
</html>
