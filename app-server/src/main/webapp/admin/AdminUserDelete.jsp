
<%@page import="com.welcomeToJeju.moj.dao.ThemeDao"%>
<%@page import="com.welcomeToJeju.moj.domain.User"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.welcomeToJeju.moj.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>

<html>
<head>
   <title>회원삭제</title>
</head>
 <body>
<h1>회원삭제결과</h1>

<%
int no = Integer.parseInt(request.getParameter("no"));

User user = userDao.findByNo(no);

if (user == null) { 
throw new Exception("해당 번호의 회원이 없습니다!");

} else {

  themeDao.deleteAllLikedThemeByUserNo(user.getNo());
  userDao.deleteAllLikedUser(user.getNo());
  userDao.updateActive(user.getNo());
  sqlSession.commit(); 
  response.sendRedirect("list");
  %>
  
  회원 삭제하기 성공!<br>
  
<a href='AdminUserList.jsp'>[목록]</a><br>
  <%} %>
</body>
</html>

<%!
UserDao userDao;
ThemeDao themeDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
  userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  themeDao = (ThemeDao) 웹애플리케이션공용저장소.getAttribute("themeDao");
  sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
}
%>
