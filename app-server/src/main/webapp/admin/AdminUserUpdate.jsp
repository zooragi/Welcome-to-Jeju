
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.welcomeToJeju.moj.dao.UserDao"%>
<%@page import="com.welcomeToJeju.moj.domain.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html>
<head>
   <title>회원수정</title>
</head>
 <body>
<h1>회원수정결과</h1>

<%
int no = Integer.parseInt(request.getParameter("no"));
User user = userDao.findByNo(no);

if (user == null) { 
throw new Exception("해당 번호의 회원이 없습니다!");

} else {

  String password = request.getParameter("password");
  String nickname = request.getParameter("nickname");

  user.setPassword(password);
  user.setNickname(nickname);

  userDao.update(user);
  sqlSession.commit();

  response.sendRedirect("list");
%>

  회원 수정하기 성공!<br>
<a href='AdminUserList.jsp'>[목록]</a><br>
<%} %>
</body>
</html>

<%!
UserDao userDao;
SqlSession sqlSession;

public void jspInit() {
  ServletConfig config = getServletConfig();
  ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
  userDao = (UserDao) 웹애플리케이션공용저장소.getAttribute("userDao");
  sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
}
%>
