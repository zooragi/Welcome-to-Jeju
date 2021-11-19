package com.welcomeToJeju.moj.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.welcomeToJeju.moj.dao.PlaceDao;

@Controller
public class PlaceController {

	@Autowired
	PlaceDao placeDao;
	
	@GetMapping("/place/list")
	public ModelAndView list_get() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.addObject("placeList", new Gson().toJson(placeDao.findAllByThemeNo(16)));
		
		mv.setViewName("place/PlaceList2");
		return mv;
	}
	
	@GetMapping(value="/place/list01", produces="text/plain;charset=UTF-8")
  @ResponseBody
	public String list_post() throws Exception{
		return new Gson().toJson(placeDao.findAllByThemeNo(16));
	}
	
}
