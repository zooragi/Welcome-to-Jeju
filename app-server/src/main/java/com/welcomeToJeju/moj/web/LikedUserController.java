package com.welcomeToJeju.moj.web;

import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class LikedUserController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired UserDao userDao;
  @Autowired ServletContext sc;
  @Autowired ThemeDao themeDao;

  @GetMapping("/likeduser/add")
  public String add(int userNo, int themeNo, HttpSession session) throws Exception {

    userDao.insertLikedUser(
        userNo, ((User) session.getAttribute("loginUser")).getNo());

    sqlSessionFactory.openSession().commit();

    return "redirect:../place/list?no=" + themeNo;
  }

  @GetMapping("/likeduser/list")
  public ModelAndView list(HttpSession session) throws Exception {

    Collection<User> userList = userDao.findAllLikedUser(((User) session.getAttribute("loginUser")).getNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("userList", userList);
    mv.addObject("pageTitle", "유저 좋아요 목록");
    mv.addObject("contentUrl", "likedUser/LikedUserList.jsp");
    mv.setViewName("template_main");
    return mv;

  }

  @GetMapping("/likeduser/delete")
  public String delete(int userNo, HttpSession session) throws Exception {

    userDao.deleteLikedUser(userNo, ((User)session.getAttribute("loginUser")).getNo());
    sqlSessionFactory.openSession().commit();

    return "redirect:list";
  }
}
