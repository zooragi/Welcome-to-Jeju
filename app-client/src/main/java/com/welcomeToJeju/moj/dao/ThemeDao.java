package com.welcomeToJeju.moj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {

  void insert(Theme theme) throws Exception;
  //  void update(Theme theme) throws Exception;
  //  void delete(int no) throws Exception;
  List<Theme> findAll() throws Exception;

  //  Theme findByTitle(String title) throws Exception;
  //  List<Theme> findByHashTag(String hashTag) throws Exception;

  //  void placeInsert(Place place) throws Exception;
  //  void placeDelete(Place place) throws Exception;

  //  void likedThemeInsert(int themeNo, int userNo) throws Exception;
  //  void likedThemeDelete(int themeNo, int userNo) throws Exception;
  //  List<Theme> likedThemeList(int userNo) throws Exception;

  List<Category> findAllCategory() throws Exception;

  void insertHashTag(@Param("themeNo") int themeNo, @Param("hashTag") String hashTag) throws Exception;
  //  void deleteHashTag(int themeNo) throws Exception;


}
