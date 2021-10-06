package com.welcomeToJeJu.moj.dao;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;

public interface ThemeDao {
  void insert(Theme theme) throws Exception;
  List<Theme> findAll() throws Exception;
  Theme findByNo(int no) throws Exception;
  Theme selectOneByTitle(String title) throws Exception;
  void update(Theme theme) throws Exception;
  void delete(int no) throws Exception;
  List<Theme> findByKeyword(String keyword) throws Exception;
  List<Theme> findLoginUserAll(User loginUser) throws Exception;
  void placeInsert(Place place) throws Exception;
  void placeDelete(Place place) throws Exception;
}
