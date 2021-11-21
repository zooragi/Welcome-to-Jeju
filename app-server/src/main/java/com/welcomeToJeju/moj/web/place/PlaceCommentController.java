package com.welcomeToJeju.moj.web.place;

import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.welcomeToJeju.moj.dao.PlaceDao;
import com.welcomeToJeju.moj.domain.Comment;
import com.welcomeToJeju.moj.domain.User;

@Controller
public class PlaceCommentController {

  @Autowired PlaceDao placeDao;
  @Autowired ServletContext sc;
  @Autowired SqlSessionFactory sqlSessionFactory;

  @PostMapping("/comment/add")
  public ModelAndView add(Comment comment,
      HttpSession session, /* Place place */String id) throws Exception {
    User user = (User) session.getAttribute("loginUser");

    HashMap<String,Object> param = new HashMap<>();
    param.put("comment", comment);
    param.put("userNo", user.getNo());
    //    param.put("placeId", place.getId());
    param.put("placeId", id);

    placeDao.insertComment(param);
    sqlSessionFactory.openSession().commit();

    ModelAndView mv = new ModelAndView();
    mv.setViewName("redirect:../place/detail");
    return mv;
  }


}
