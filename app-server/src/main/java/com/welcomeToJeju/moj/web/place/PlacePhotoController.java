package com.welcomeToJeju.moj.web.place;

import java.util.HashMap;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Photo;
import com.welcomeToJeju.moj.domain.User;
import net.coobird.thumbnailator.ThumbnailParameter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;

@Controller
public class PlacePhotoController {

  @Autowired PlaceDao placeDao;
  @Autowired ServletContext sc;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @PostMapping("/photo/add")
  public ModelAndView add(Photo photo, Part photoFile,
      HttpSession session, /* Place place */String id) throws Exception {
    User user = (User) session.getAttribute("loginUser");

    if (photoFile.getSize() > 0) {
      String filename = UUID.randomUUID().toString();
      photoFile.write(sc.getRealPath("/upload/place") + "/" + filename);
      photo.setFilePath(filename);

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
    }

    HashMap<String,Object> param = new HashMap<>();
    param.put("photo", photo);
    param.put("userNo", user.getNo());
    //    param.put("placeId", place.getId());
    param.put("placeId", id);

    placeDao.insertPhoto(param);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../place/detail");
    return mv;
  }


}
