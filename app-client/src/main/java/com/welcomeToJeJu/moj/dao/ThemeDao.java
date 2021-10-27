package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.Category;
import com.welcomeToJeju.moj.domain.Theme;

public interface ThemeDao {
  void insert(Theme theme) throws Exception; //
  List<Theme> findAll() throws Exception; //
  Theme findByTitle(String themeTitle) throws Exception; //
  void update(Theme theme) throws Exception; //
  void delete(int themeNo) throws Exception;//
  void insertHashtags(int themeNo, String hashtag) throws Exception; //
  void deleteHashtags(int themeNo) throws Exception; //
  List<Theme> searchHashtags(String hashtagName) throws Exception; //
  void insertLikedTheme(int themeNo,int userNo) throws Exception;
  void deleteLikedTheme(int themeNo,int userNo) throws Exception;
  List<Theme> likedThemeList(int userNo) throws Exception;
  List<Category> findAllCategory() throws Exception; //

  //  
  //  List<Theme> findByUserNo(int userNo) throws Exception;
  void updateReportedCount(int reportedCount, int themeNo) throws Exception;
  //  public void likedThemeAllDelete(int themeNo) throws Exception;
}
