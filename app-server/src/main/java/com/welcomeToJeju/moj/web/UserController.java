package com.welcomeToJeju.moj.web;

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
public class UserController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired UserDao userDao;
  @Autowired ServletContext sc;
  @Autowired ThemeDao themeDao;



  @PostMapping("/user/add")
  public ModelAndView add(User user) throws Exception {

    userDao.insert(user);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("refresh", "2;url=../auth/loginform");
    mv.addObject("pageTitle", "회원 가입 완료");
    mv.addObject("contentUrl", "user/UserAdd.jsp");
    mv.setViewName("template_main");
    return mv;
  }


  @GetMapping("/user/addform")
  public ModelAndView form() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "회원 가입 하기");
    mv.addObject("contentUrl", "user/UserAddForm.jsp");
    mv.setViewName("template_main");
    return mv;
  }


  @GetMapping("/user/delete")
  public ModelAndView delete(int no, HttpSession session) throws Exception {

    User user = userDao.findByNo(no);

    themeDao.deleteAllLikedThemeByUserNo(user.getNo());
    userDao.deleteAllLikedUser(user.getNo());
    userDao.updateActive(user.getNo());
    sqlSessionFactory.openSession().commit();
    session.invalidate();

    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "회원 탈퇴 완료");
    mv.addObject("refresh", "1;../auth/loginform");
    mv.addObject("contentUrl", "user/UserDelete.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @PostMapping("/user/update")
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
    mv.addObject("pageTitle", "회원 정보 수정");
    mv.addObject("refresh", "1;../auth/login");
    mv.addObject("contentUrl", "user/UserUpdate.jsp");
    mv.setViewName("template_main");
    return mv;


  }

}
