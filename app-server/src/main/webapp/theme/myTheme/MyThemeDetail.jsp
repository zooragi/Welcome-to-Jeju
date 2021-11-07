<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>테마 상세 보기</title>
</head>

<body>
<h1>테마 상세 보기(MVC + EL)</h1>

테마 상세 보기 🔍
<form action='MyThemeUpdate.jsp'>
  <label for='f-no'>번호</label>
  <input id='f-no' type='text' name='no' value='${theme.no}' readonly><br>
  
  <label for='f-title'>제목</label>
  <input id='f-title' type='text' name='title' value='${theme.title}'> 🤍<br>
  
  <label for='f-nickname'>닉네임</label>
  <input id='f-nickname' type='text' name='nickname' value='${theme.owner.nickname}' readonly> 🤍<br>
  
  <label for='f-category'>카테고리</label>
  <input id='f-category' type='text' name='category' value='${theme.category.name}'><br>
  
  <label for='f-hashtags'>해시태그</label>
  <input id='f-hashtags' type='text' name='hashtags' value='${theme.hashtags}'><br>

  <button>변경</button>
  <a href='delete?no=${theme.no}'>[삭제]</a>
  <a href='list'>[목록]</a><br>
</form>

<br>
<a href='../place/add'>장소 등록하기</a> ✏️
<br>
<a href='../place/list'>장소 목록 보기</a> 📄
<!-- 
<form action='PlaceAdd.jsp'>
  <button>장소 등록하기 ✏️</button>
</form>

<br>
<form action='PlaceList.jsp'>
  <button>장소 목록 보기 📄</button>
</form>
 -->
</body>
</html>
