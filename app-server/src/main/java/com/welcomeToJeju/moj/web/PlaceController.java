package com.welcomeToJeju.moj.web;

import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Place;

@Controller
@RequestMapping("/place")
public class PlaceController {

  @Autowired
  PlaceDao placeDao;
  int themeNo = 0;

  @GetMapping("list")
  public ModelAndView list(String no) throws Exception{
    ModelAndView mv = new ModelAndView();
    themeNo = Integer.parseInt(no);
    mv.setViewName("place/PlaceList2");
    return mv;
  }

  @GetMapping(value="list01", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list_get() throws Exception{
    return new Gson().toJson(placeDao.findAllByThemeNo(themeNo));
  }

  @GetMapping("search")
  public String search(Model model, String keyword) throws Exception{
    System.out.println(keyword);
    model.addAttribute("searchedPlace", keyword);
    return "place/PlaceSearch";
  }
  
  @PostMapping(value="add", consumes = "application/json")
  public String add(Place place,Part photoFile,String comment) throws Exception{
  	System.out.println(place);
  	return "redirect:list"+"?no="+themeNo;
  }
  
}
