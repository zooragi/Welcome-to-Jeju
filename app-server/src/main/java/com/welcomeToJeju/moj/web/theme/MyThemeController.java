package com.welcomeToJeju.moj.web.theme;

import java.util.Collection;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class MyThemeController {

  @Autowired ThemeDao themeDao;
  @Autowired UserDao userDao;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @GetMapping("/mytheme/addform")
  public ModelAndView addform() {
    ModelAndView mv = new ModelAndView();
    mv.addObject("pageTitle", "나의 테마 만들기");
    mv.addObject("contentUrl", "theme/myTheme/MyThemeAddForm.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @PostMapping("/mytheme/add")
  public ModelAndView add(HttpSession session, Theme theme, int category) throws Exception {
    User user = (User) session.getAttribute("loginUser");
    theme.setOwner(user);

    Category c = themeDao.findCategoryByNo(category);
    theme.setCategory(c);

    themeDao.insert(theme);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.addObject("theme", theme);
    mv.addObject("refresh", "2;url=list?no=" + user.getNo());
    mv.addObject("pageTitle", "나의 테마 만들기");
    mv.addObject("contentUrl", "theme/myTheme/MyThemeAdd.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  @GetMapping("/mytheme/list")
  public ModelAndView list(int no) throws Exception {
    Collection<Theme> themeList = themeDao.findByUserNo(no);

    ModelAndView mv = new ModelAndView();
    mv.addObject("themeList", themeList);
    mv.addObject("pageTitle", "나의 테마 목록 보기");
    mv.addObject("contentUrl", "theme/myTheme/MyThemeList.jsp");
    mv.setViewName("template_main");
    return mv;
  }

  // 테스트!!
  @PostMapping("/mytheme/update")
  public ModelAndView update(Theme theme, int category) throws Exception {
    Theme oldTheme = themeDao.findByNo(theme.getNo());

    if (oldTheme == null) {
      throw new Exception("..");
    }

    Category c = themeDao.findCategoryByNo(category);
    theme.setCategory(c);

    themeDao.update(theme);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:detail?no=" + theme.getNo());
    return mv;
  }


}
