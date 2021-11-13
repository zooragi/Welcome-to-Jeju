<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>테마 신고하기</title>
</head>
<body>

테마 신고하기
  <label for='f-no'>번호</label>
  <input id='f-no' type='text' name='no' value='${reportTheme.reportedTheme.no}'><br>
	
	<label for='f-title'>신고당한 테마</label>
	<input id='f-title' type='text' name='title' value='${reportTheme.reprotedTheme.title}'><br>
	
<form action="theme">
	<label for='f-nickname'>신고한 유저</label>
	<input id='f-nickname' type='text' name='nickname' value='${loginUser.nickname}' readonly><br>
	
	<label for='f-content'>내용</label>
	<textarea id='f-content' name='content'></textarea><br>
		
		<button>신고</button>
</form>

</body>
</html>