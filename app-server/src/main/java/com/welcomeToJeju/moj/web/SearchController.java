package com.welcomeToJeju.moj.web;

import java.util.Collection;
import javax.servlet.ServletContext;
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
public class SearchController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired UserDao userDao;
  @Autowired ServletContext sc;
  @Autowired ThemeDao themeDao;

  @GetMapping("/search/hashtag")
  public ModelAndView searchHashtag(String keyword) throws Exception {

    Collection<Theme> themeList = themeDao.findByHashtag(keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("themeList", themeList);
    mv.addObject("keyword", keyword);
    mv.addObject("pageTitle", "해시태그 검색 목록보기");
    mv.addObject("contentUrl", "search/SearchHashtag.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @GetMapping("/search/theme")
  public ModelAndView searchTheme(String keyword) throws Exception {

    Collection<Theme> themeList = themeDao.findByKeyword(keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("themeList", themeList);
    mv.addObject("keyword", keyword);
    mv.addObject("pageTitle", "테마 검색 목록보기");
    mv.addObject("contentUrl", "search/SearchTheme.jsp");
    mv.setViewName("template_main");
    return mv;

  }

  @GetMapping("/search/user")
  public ModelAndView searchUser(String keyword) throws Exception {

    Collection<User> userList = userDao.findByKeyword(keyword);

    ModelAndView mv = new ModelAndView();
    mv.addObject("userList", userList);
    mv.addObject("keyword", keyword);
    mv.addObject("pageTitle", "유저 검색 목록보기");
    mv.addObject("contentUrl", "search/SearchUser.jsp");
    mv.setViewName("template_main");
    return mv;

  }
}


