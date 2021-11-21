package com.welcomeToJeju.moj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;

@Controller
public class HomeController {

  @Autowired UserDao userDao;
  @Autowired ThemeDao themeDao;
  @Autowired PlaceDao placeDao;

  @GetMapping("/home")
  public String home(Model model) throws Exception {
    model.addAttribute("Top10Places",placeDao.findTop10());
    model.addAttribute("Top10Themes", themeDao.findTop10());
    model.addAttribute("latest10Theme", themeDao.latest10Theme());
    model.addAttribute("Top10User", userDao.userTop10());
    model.addAttribute("allTheme", themeDao.findAllPublicTheme());
    return "home/Home";
  }
}
