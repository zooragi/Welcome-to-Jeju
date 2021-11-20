<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set scope="page" var="contextRoot" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="${contextRoot}/css/theme_list.css">

  <style>
    .ranking {
    margin : 30px;
    float : center;
    }
  
    .themeranking {
    margin : 30px;
    float : left;
    }
    
    .userranking {
    margin : 30px;
    float : left;
    }
  </style>
  
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
