package com.welcomeToJeju.moj.web;

import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
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
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/place")
public class PlaceController {

  @Autowired SqlSessionFactory sqlSessionFactory;
  @Autowired ServletContext sc;
  @Autowired PlaceDao placeDao;
  @Autowired ThemeDao themeDao;  
  int themeNo = 0;

  @GetMapping("list")
  public ModelAndView list(String no) throws Exception{
    ModelAndView mv = new ModelAndView();
    themeNo = Integer.parseInt(no);
    Theme theme = themeDao.findByNo(themeNo);
    mv.addObject(theme);
    mv.setViewName("place/PlaceList3");
    return mv;
  }

  @GetMapping(value="list01", produces="application/json;charset=UTF-8")
  @ResponseBody
  public String list_get() throws Exception{
    return new Gson().toJson(placeDao.findAllByThemeNo(themeNo));
  }

  @GetMapping("search")
  public String search(Model model, String keyword) throws Exception{
    model.addAttribute("searchedPlace", keyword);
    return "place/PlaceSearch";
  }

  @PostMapping(value="add")
  public String add(Place place, Part[] photoFile, HttpSession session,
      String comment) throws Exception{

    placeDao.insert(place);

    User user = (User) session.getAttribute("loginUser");

    HashMap<String,Object> param1 = new HashMap<>();
    HashMap<String,Object> param2 = new HashMap<>();
    HashMap<String,Object> param3 = new HashMap<>();

    param3.put("themeNo", themeNo);
    param3.put("placeId", place.getId());
    param3.put("userNo", user.getNo());
    placeDao.insertPlaceUserTheme(param3);

    param1.put("placeId", place.getId());
    param1.put("userNo", user.getNo());
    param1.put("comment", comment);
    placeDao.insertComment(param1);


    for(Part p : photoFile) {
      param2.put("placeId", place.getId());
      param2.put("userNo", user.getNo());
      System.out.println(p);
      String filename = UUID.randomUUID().toString();
      System.out.println(filename);
      p.write(sc.getRealPath("/upload/place") + "/" + filename);

      Thumbnails.of(sc.getRealPath("/upload/place") + "/" + filename)
      .size(20, 20)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_20x20";
        }
      });

      Thumbnails.of(sc.getRealPath("/upload/place") + "/" + filename)
      .size(100, 100)
      .outputFormat("jpg")
      .crop(Positions.CENTER)
      .toFiles(new Rename() {
        @Override
        public String apply(String name, ThumbnailParameter param) {
          return name + "_100x100";
        }
      });
      param2.put("filePath", filename);


      placeDao.insertPhoto(param2);
    }
    sqlSessionFactory.openSession().commit();

    return "redirect:list?no="+themeNo;

  }
}
