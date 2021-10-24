package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {

  void insert(Theme theme) throws Exception;
  void update(Theme theme) throws Exception;
  void delete(String themeTitle) throws Exception;
  List<Theme> findAll() throws Exception;

  Theme search(String themeTitle) throws Exception;
  List<Theme> hashtagSearch(String hashtag) throws Exception;

  void placeInsert(Place place) throws Exception;
  void placeDelete(Place place) throws Exception;

  void likedThemeInsert(int themeNo, int userNo) throws Exception;
  void likedThemeDelete(int themeNo, int userNo) throws Exception;
  List<Theme> likedThemeList(int userNo) throws Exception;


}
