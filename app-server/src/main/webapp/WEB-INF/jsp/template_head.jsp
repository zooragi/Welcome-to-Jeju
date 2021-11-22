<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set scope="page" var="contextRoot" value="${pageContext.servletContext.contextPath}"/>
<head>
    <link rel="stylesheet" href="${contextRoot}/node_modules/bootstrap/dist/css/bootstrap.css">
    <link rel="stylesheet" href="${contextRoot}/css/common.css?ver=4">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gugi&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${contextRoot}/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${contextRoot}/css/owl.theme.default.min.css">

    <script defer src="https://kit.fontawesome.com/a340a3bb10.js" crossorigin="anonymous"></script>
    <script src = "${contextRoot}/node_modules/@popperjs/core/dist/umd/popper.js"> </script>
    <script src = "${contextRoot}/node_modules/bootstrap/dist/js/bootstrap.js"> </script>
    <script src = "${contextRoot}/node_modules/jquery/dist/jquery.min.js"> </script>
    <script defer src="${contextRoot}/javascript/common.js?ver=1"></script>
    <script defer src="${contextRoot}/javascript/owl.carousel.min.js"></script>
</head>