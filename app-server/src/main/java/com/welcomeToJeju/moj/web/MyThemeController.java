package com.welcomeToJeju.moj.web;

import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

@Controller
public class MyThemeController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ThemeDao themeDao;
  @Autowired ServletContext sc;

  @GetMapping("/mytheme/addform")
  public ModelAndView addform() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "나의 테마 만들기");
    mv.addObject("contentUrl", "theme/myTheme/MyThemeAddForm.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @PostMapping("/mytheme/add")
  public ModelAndView add(Theme theme) {
    ModelAndView mv = new ModelAndView();
    //    mv.addObject("refresh", "2;url=list");
    mv.addObject("pageTitle", "나의 테마 만들기");
    mv.addObject("contentUrl", "theme/myTheme/MyThemeAdd.jsp");
    mv.setViewName("template_main");
    return mv;
  }


}
