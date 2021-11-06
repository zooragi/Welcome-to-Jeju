package com.welcomeToJeju.moj.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("user/add")
public class UserAddHandler2 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  UserDao userDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {

    try {
      sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
          "com/welcomeToJeju/moj/conf/mybatis-config.xml")).openSession();

      userDao = sqlSession.getMapper(UserDao.class);

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("   <title>회원등록</title>");
    out.println("</head>");
    out.println(" <body>");
    out.println("<h1>회원등록결과</h1>");

    User user = new User();

    user.setEmail(request.getParameter("email"));
    user.setPassword(request.getParameter("password"));
    user.setNickname(request.getParameter("nickname"));

    try {
      userDao.insert(user);
      sqlSession.commit();
      out.println("회원 가입 성공!");

    } catch (Exception e) {

      out.println(
          e.getCause().toString().contains("UIX_email") ?
              "이메일 중복!" : "닉네임 중복!");
    }
    out.println("</body>");
    out.println("</html>");
  }

  @Override
  public void destroy() {
    sqlSession.close();
  }
}
