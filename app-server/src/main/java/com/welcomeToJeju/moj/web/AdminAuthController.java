package com.welcomeToJeju.moj.web;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class AdminAuthController {

  @Autowired UserDao userDao;
  @Autowired ServletContext sc;

  @GetMapping("/admin/loginform")
  public ModelAndView loginform() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "관리자 로그인");
    mv.addObject("contentUrl", "admin/AdminLoginForm.jsp");
    mv.setViewName("template_main");
    return mv;
  }


  @PostMapping("/admin/login")
  public ModelAndView login(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {

    User user = userDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    if (email.equals("root@test.com") && password.equals("0000")) {
      session.setAttribute("loginUser", user);
      mv.addObject("pageTitle", "로그인 성공");
      mv.addObject("contentUrl", "admin/AdminLogin.jsp");

      mv.setViewName("template_main");

    } else {
      mv.addObject("refresh", "2;url=loginform");
      mv.addObject("pageTitle", "로그인 오류");
      mv.addObject("contentUrl", "user/LoginFail.jsp");
      mv.setViewName("template_main");
    }
    return mv;
  }


  @GetMapping("/admin/logout")
  public ModelAndView logout(HttpSession session) throws Exception {
    session.invalidate();
    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:loginform");
    return mv;
  }


}
