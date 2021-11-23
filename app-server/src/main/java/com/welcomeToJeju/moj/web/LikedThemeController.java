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
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class LikedThemeController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired UserDao userDao;
  @Autowired ServletContext sc;
  @Autowired ThemeDao themeDao;

  @GetMapping("/likedtheme/add")
  public String add(int themeNo, int userNo) throws Exception {

    themeDao.insertLikedTheme(themeNo, userNo);
    sqlSessionFactory.openSession().commit();

    return "redirect:../place/list?no=" + themeNo;
  }

  @GetMapping("/likedtheme/list")
  public ModelAndView list(HttpSession session) throws Exception {

    Collection<Theme> themeList = themeDao.findAllLikedTheme(((User) session.getAttribute("loginUser")).getNo());

    ModelAndView mv = new ModelAndView();
    mv.addObject("themeList", themeList);
    mv.addObject("pageTitle", "테마 좋아요 목록");
    mv.addObject("contentUrl", "likedTheme/LikedThemeList.jsp");
    mv.setViewName("template_main");
    return mv;

  }

  @GetMapping("/likedtheme/delete")
  public String delete(int themeNo, HttpSession session) throws Exception {

    themeDao.deleteLikedTheme(themeNo, ((User)session.getAttribute("loginUser")).getNo());
    sqlSessionFactory.openSession().commit();

    return "redirect:list";
  }
}
