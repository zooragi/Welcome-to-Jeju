
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
   <title>로그인</title>
</head>
 <body>
<h1>로그인 성공!</h1>
<p>'${loginUser.nickname}' 님 환영합니다! 🙋</p>
<a href='../mytheme/list?no=${loginUser.no}'>나의 테마 목록 보기</a>

</body>
</html>
