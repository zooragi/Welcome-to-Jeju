package com.welcomeToJeju.moj.web.place;

import javax.servlet.ServletContext;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.welcomeToJeju.moj.dao.PlaceDao;

@Controller
public class PlaceCommentController {

  @Autowired PlaceDao placeDao;
  @Autowired ServletContext sc;
  @Autowired SqlSessionFactory sqlSessionFactory;


}
