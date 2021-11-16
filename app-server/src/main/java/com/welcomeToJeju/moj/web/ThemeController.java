package com.welcomeToJeju.moj.web;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;

@Controller
public class ThemeController {

	@Autowired ThemeDao themeDao;
	
	@GetMapping("/theme/list")
	public ModelAndView list() throws Exception {
		Collection<Theme> themeList = themeDao.findAll();
		ModelAndView mv = new ModelAndView();
		mv.addObject("themeList",themeList);
    mv.addObject("pageTitle", "전체테마목록");
    mv.addObject("contentUrl", "theme/AllThemeList.jsp");
    mv.setViewName("template_main");
    return mv;
	}
}
