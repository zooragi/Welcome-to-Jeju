package com.welcomeToJeju.moj.dao;

import java.util.HashMap;
import java.util.List;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {

  // 테마
  void insert(Theme theme) throws Exception;
  void insertHashtag(int themeNo, String hashtag) throws Exception;

  void update(Theme theme) throws Exception;

  void delete(int themeNo) throws Exception;
  void deleteHashtag(int themeNo) throws Exception;
  void deletePlaceUserTheme(int themeNo) throws Exception;

  List<Theme> findAll() throws Exception;
  List<Theme> findTop10() throws Exception;
  List<Theme> latest10Theme() throws Exception;
  List<Theme> findAllPublicTheme() throws Exception;
  List<Theme> findAllPublicThemeByUserNo(int userNo) throws Exception;
  List<Category> findAllCategory() throws Exception;
  List<Theme> findAllByPlaceId(String placeId) throws Exception;

  // 테마 검색
  Theme findByTitle(String title) throws Exception;
  Theme findByNo(int no) throws Exception;
  List<Theme> findByUserNo(int userNo) throws Exception;

  List<Theme> findByHashtag(String hashtag) throws Exception;
  List<Theme> findByKeyword(String keyword) throws Exception;

  Category findCategoryByNo(int no) throws Exception;

  void updateViewCount(int themeNo) throws Exception;
  void updateReportedCount(HashMap<String,Object> params) throws Exception;

  List<Theme> themeRankingByViewCount() throws Exception;
  List<Theme> findAllReportedTheme() throws Exception;

  // 좋아하는 테마
  void insertLikedTheme(int themeNo, int userNo) throws Exception;
  void deleteLikedTheme(int themeNo, int userNo) throws Exception;
  void deleteAllLikedThemeByThemeNo(int themeNo) throws Exception;
  void deleteAllLikedThemeByUserNo(int userNo) throws Exception;
  List<Theme> findAllLikedTheme(int userNo) throws Exception;


}
