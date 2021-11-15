package com.welcomeToJeju.moj.servlet.place;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Photo;
import com.welcomeToJeju.moj.domain.User;

@WebServlet("/photo/add")
public class PhotoAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  PlaceDao placeDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext 웹애플리케이션공용저장소 = config.getServletContext();
    placeDao = (PlaceDao) 웹애플리케이션공용저장소.getAttribute("placeDao");
    sqlSession = (SqlSession) 웹애플리케이션공용저장소.getAttribute("sqlSession");
  }

  @Override 
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Photo photo = new Photo();

      photo.setPlaceId(request.getParameter("id"));

      User loginUser = (User) request.getSession(true).getAttribute("loginUser");
      photo.setUserNo(loginUser.getNo());

      photo.setFilePath(request.getParameter("filePath"));

      placeDao.insertPhoto(photo);
      sqlSession.commit();

      request.setAttribute("pageTitle", "사진 등록하기");
      request.setAttribute("contentUrl", "/place/PhotoAdd.jsp");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("error").forward(request, response);
    }
  }


}
