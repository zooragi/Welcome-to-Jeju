
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<head>

</head>
 <body>
<h1>로그인 성공!</h1>
<p>'${loginUser.nickname}' 관리자님 환영합니다! 🙋</p>
<button><a href='../admin/themelist'>전체 테마 목록 보기</a></button> <br>
<button><a href='../admin/userlist'>전체 유저 목록 보기</a></button> <br>
<button><a href='../auth/userinfo'>내 정보</a></button> <br>
<button><a href='../auth/logout'>로그아웃</a></button>

</body>
</html>
