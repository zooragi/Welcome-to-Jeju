package com.welcomeToJeju.moj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.welcomeToJeju.moj.dao.PlaceDao;

@Controller
public class PlaceController {

	@Autowired
	PlaceDao placeDao;
	int themeNo = 0;
	
	@GetMapping("/place/list")
	public ModelAndView list(String no) throws Exception{
		ModelAndView mv = new ModelAndView();
		themeNo = Integer.parseInt(no);
		mv.setViewName("place/PlaceList2");
		return mv;
	}
	
	@GetMapping(value="/place/list01", produces="application/json;charset=UTF-8")
  @ResponseBody
	public String list_get() throws Exception{
		return new Gson().toJson(placeDao.findAllByThemeNo(themeNo));
	}
	
	@GetMapping("/place/search")
	public String search(Model model, String keyword) throws Exception{
		System.out.println(keyword);
		model.addAttribute("searchedPlace", keyword);
		return "place/PlaceSearch";
	}
}
