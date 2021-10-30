package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {

  // 테마
  void insert(Theme theme) throws Exception;
  void insertHashtag(int themeNo, String hashTag) throws Exception;

  void update(Theme theme) throws Exception;

  void delete(int themeNo) throws Exception;
  void deleteHashtag(int themeNo) throws Exception;

  List<Theme> findAll() throws Exception;
  List<Category> findAllCategory() throws Exception;

  Theme findByTitle(String themeTitle) throws Exception;
  List<Theme> findByUserNo(int userNo) throws Exception;
  List<Theme> findByHashtag(String hashtag) throws Exception;

  void updateViewCount(int viewCount, int themeNo) throws Exception;
  void updateReportedCount(int reportedCount, int themeNo) throws Exception;

  // 좋아하는 테마
  void insertLikedTheme(int themeNo, int userNo) throws Exception;
  void deleteLikedTheme(int themeNo, int userNo) throws Exception;
  List<Theme> deleteAllLikedTheme(int userNo) throws Exception;
  List<Theme> findAllLikedTheme(int userNo) throws Exception;


}
