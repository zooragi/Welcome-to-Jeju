package com.welcomeToJeju.moj.web;

import java.util.Collection;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class AdminUserController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired UserDao userDao;
  @Autowired ServletContext sc;
  @Autowired ThemeDao themeDao;

  @GetMapping("admin/list")
  public ModelAndView list() throws Exception {
    Collection<User> userList = userDao.findAll();
    ModelAndView mv = new ModelAndView();
    mv.addObject("userList", userList);
    mv.addObject("pageTitle", "회원 목록보기");
    mv.addObject("contentUrl", "admin/AdminUserList.jsp");
    mv.setViewName("template_main");
    return mv;

  }

  @GetMapping("/admin/delete")
  public ModelAndView delete(int no) throws Exception {
    User user = userDao.findByNo(no);
    themeDao.deleteAllLikedThemeByUserNo(user.getNo());
    userDao.deleteAllLikedUser(user.getNo());
    userDao.updateActive(user.getNo());
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;

  }

  @GetMapping("/admin/detail")
  public ModelAndView detail(int no) throws Exception {

    User user = userDao.findByNo(no);
    ModelAndView mv = new ModelAndView();
    mv.addObject("user", user);
    mv.addObject("pageTitle", "회원 상세 보기");
    mv.addObject("contentUrl", "admin/AdminUserDetail.jsp");
    mv.setViewName("template_main");
    return mv;

  }

  @PostMapping("/admin/update")
  public ModelAndView update(User user, HttpSession session) throws Exception {
    User oldUser = userDao.findByNo(user.getNo());
    user.setNo(oldUser.getNo());
    user.setEmail(oldUser.getEmail());
    user.setRegisteredDate(oldUser.getRegisteredDate());
    user.setReportedCount(oldUser.getReportedCount());
    user.setViewCount(oldUser.getViewCount());

    userDao.update(user);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:list");
    return mv;

  }


}
