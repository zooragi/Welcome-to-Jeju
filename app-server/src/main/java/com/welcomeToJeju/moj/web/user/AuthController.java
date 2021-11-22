package com.welcomeToJeju.moj.web.user;

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

  @GetMapping("/auth/loginform")
  public ModelAndView loginform() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "로그인");
    mv.addObject("contentUrl", "user/AuthLoginForm2.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @PostMapping("/auth/login")
  public ModelAndView login(String email, String password, String saveEmail,
      HttpServletResponse response, HttpSession session) throws Exception {

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

    // 관리자
    if(email.equals("root@test.com") && password.equals("0000")) {
      User user = new User();
      user.setEmail(email);
      user.setPassword(password);
      user.setNickname("제주정승");
      session.setAttribute("loginUser", user);

      ModelAndView mv = new ModelAndView();
      mv.setViewName("redirect:../home");

      return mv;
    } 

    // 유저
    User user = userDao.findByEmailAndPassword(email, password);

    ModelAndView mv = new ModelAndView();

    if (user != null) {
      session.setAttribute("loginUser", user);
      mv.setViewName("redirect:../home");
      return mv;

    } else {
      //mv.addObject("refresh", "2;url=loginform");
      mv.addObject("pageTitle", "로그인 실패!");
      mv.addObject("contentUrl", "Error.jsp");
      mv.setViewName("template_main");
      return mv;
    }
  }

  @GetMapping("/auth/logout")
  public ModelAndView logout(HttpSession session) throws Exception {
    session.invalidate();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../home");

    return mv;
  }

  @GetMapping("/auth/userinfo")
  public ModelAndView userinfo() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "회원 상세 보기");
    mv.addObject("contentUrl", "user/AuthUserInfo2.jsp");
    mv.setViewName("template_main");

    return mv;
  }


}
