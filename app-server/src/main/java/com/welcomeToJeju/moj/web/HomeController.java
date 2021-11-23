package com.welcomeToJeju.moj.web;

import javax.servlet.http.HttpSession;
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
  public String home(Model model, HttpSession session) throws Exception {

    //    List<String> emojiList = new ArrayList<>();
    //    emojiList.add("🏄");
    //    emojiList.add("✈️");
    //    emojiList.add("🌴");
    //    emojiList.add("🍊");
    //    emojiList.add("🌊");
    //    emojiList.add("🥜");
    //    emojiList.add("🛵");
    //    emojiList.add("🥤");
    //    emojiList.add("🌠");
    //    Collections.shuffle(emojiList);
    //    session.setAttribute("emojiList", emojiList);
    //    session.setAttribute("emoji", emojiList.get(2));
    //    session.setAttribute("emoji2", emojiList.get(3));
    //    session.setAttribute("emoji3", emojiList.get(4));
    //    session.setAttribute("emoji4", emojiList.get(0));
    //    session.setAttribute("emoji5", emojiList.get(1));

    model.addAttribute("Top10Places",placeDao.findTop10());
    model.addAttribute("Top10Themes", themeDao.findTop10());
    model.addAttribute("latest10Theme", themeDao.latest10Theme());
    model.addAttribute("Top10User", userDao.userTop10());
    model.addAttribute("allTheme", themeDao.findAllPublicTheme());
    return "home/Home";
  }
}
