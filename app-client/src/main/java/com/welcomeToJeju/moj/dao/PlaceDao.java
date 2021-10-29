package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.Place;

public interface PlaceDao {

  void insert(Place place) throws Exception;
  void delete(Place place) throws Exception;
  List<Place> findAll() throws Exception;

  //  List<Place> findByThemeTitle(String themeTitle) throws Exception;
  List<Place> findByThemeNo(int themeNo) throws Exception;


}
