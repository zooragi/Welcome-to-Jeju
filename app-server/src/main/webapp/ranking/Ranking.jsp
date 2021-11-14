<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>순위 보기</title>

  <style>
    .themeranking {
    margin : 30px;
    float : left;
    }
    
    .userranking {
    margin : 30px;
    float : left;
    }
  </style>
  
</head>
<body>

<div class="ranking">
<div class="themeranking">
  <jsp:include page="ThemeRanking.jsp"/>
</div>

<div class="userranking">
  <jsp:include page="UserRanking.jsp"/>
</div>
</div>

</body>
</html>