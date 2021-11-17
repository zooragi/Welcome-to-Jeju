package com.welcomeToJeju.moj.web;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class RankingController {

  @Autowired ThemeDao themeDao;
  @Autowired UserDao userDao;

  @GetMapping("/ranking")
  public ModelAndView ranking() throws Exception {

    Collection<Theme> themeList = themeDao.themeRankingByViewCount();
    Collection<User> userList = userDao.userRankingByViewCount();

    ModelAndView mv = new ModelAndView();
    mv.addObject("themeList", themeList);
    mv.addObject("userList", userList);
    mv.addObject("pageTitle", "순위 보기");
    mv.addObject("contentUrl", "ranking/Ranking.jsp");
    mv.setViewName("template_main");
    return mv;
  }
}
