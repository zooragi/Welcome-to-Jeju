package com.welcomeToJeju.moj.web;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
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
public class AuthController {


  @Autowired UserDao userDao;
  @Autowired ServletContext sc;

  @GetMapping("/auth/loginform")
  public ModelAndView loginform() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그인");
    mv.addObject("contentUrl", "user/AuthLoginForm.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @PostMapping("/auth/login")
  public ModelAndView login(String email, String password, String saveEmail, HttpServletResponse response, HttpSession session) throws Exception {
    Cookie cookie = null;
    if (saveEmail != null) {
      cookie = new Cookie("email", email);
      cookie.setMaxAge(60 * 60 * 24 * 7);
      //cookie.setPath(sc.getContextPath() + "/app/auth"); // 예) http://localhost:8080/pms/app/auth

    } else {
      cookie = new Cookie("email", "");
      cookie.setMaxAge(0); // 유효기간을 0으로 설정하면 웹브라우저가 받는 즉시 무효한 쿠기가 된다.
    }
    response.addCookie(cookie);

    User user = userDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    if (user != null) {
      session.setAttribute("loginUser", user);
      mv.addObject("pageTitle", "로그인 성공");
      mv.addObject("contentUrl", "user/AuthLogin.jsp");
      mv.setViewName("template_main");

    } else {
      mv.addObject("refresh", "2;url=loginForm");
      mv.addObject("pageTitle", "로그인 오류");
      mv.addObject("contentUrl", "user/LoginFail.jsp");
      mv.setViewName("template_main");
    }
    return mv;
  }


  @GetMapping("/auth/logout")
  public ModelAndView logout(HttpSession session) throws Exception {
    session.invalidate();
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그아웃");
    mv.addObject("contentUrl", "user/AuthLogout.jsp");
    mv.addObject("refresh", "1;url=loginform");
    mv.setViewName("template_main");
    return mv;
  }


  @GetMapping("/auth/userinfo")
  public ModelAndView userinfo() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "회원 상세 보기");
    mv.addObject("contentUrl", "user/AuthUserInfo.jsp");
    mv.setViewName("template_main");
    return mv;
  }



}
