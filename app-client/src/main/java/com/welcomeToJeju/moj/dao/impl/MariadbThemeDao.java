package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

public class MariadbThemeDao implements ThemeDao {

  Connection con;

  public MariadbThemeDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Theme theme) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into jeju_theme(public,title,category,user_no) values(?,?,?,?)")) {

      stmt.setBoolean(1, theme.isPublic());
      stmt.setString(2, theme.getTitle()); //
      stmt.setString(3, theme.getCategory());
      stmt.setString(4, theme.getThemeOwner().getNickName());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("테마 데이터 입력 실패!");
      }
    }
  }

  @Override
  public List<Theme> findAll() throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "")) {

    }
    //    requestAgent.request("theme.list", null);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
    //
    //    return new ArrayList<>(requestAgent.getObjects(Theme.class));
    return null;
  }

  @Override
  public void update(Theme theme) throws Exception {
    //    requestAgent.request("theme.update", theme);		
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
  }

  @Override
  public void delete(String themeTitle) throws Exception {
    //    requestAgent.request("theme.delete", themeTitle);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
    //    return requestAgent.getObject(String.class);
  }

  @Override
  public void placeInsert(Place place) throws Exception {
    //    requestAgent.request("theme.place.insert", place);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
  }

  @Override
  public void placeDelete(Place place) throws Exception {
    //    requestAgent.request("theme.place.delete", place);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
    //    return requestAgent.getObject(String.class);
  }

  @Override
  public Theme search(String title) throws Exception {
    //    requestAgent.request("theme.search", title);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
    //    return requestAgent.getObject(Theme.class);
    return null;
  }

  @Override
  public List<Theme> hashtagSearch(String hashtagName) throws Exception {
    //    requestAgent.request("theme.hashtag.search", hashtagName);
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
    //
    //    return new ArrayList<>(requestAgent.getObjects(Theme.class));
    return null;
  }

  @Override
  public void likedThemeInsert(int themeNo, int userNo) throws Exception{
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("themeNo",Integer.toString(themeNo));
    //    params.put("userNo",Integer.toString(userNo));
    //    requestAgent.request("theme.liked.insert", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
  }

  @Override
  public void likedThemeDelete(int themeNo,int userNo) throws Exception{
    //    HashMap<String, String> params = new HashMap<>();
    //    params.put("themeNo",Integer.toString(themeNo));
    //    params.put("userNo",Integer.toString(userNo));
    //    requestAgent.request("theme.liked.delete", params);
    //    if(requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
  }

  @Override
  public List<Theme> likedThemeList(int userNo) throws Exception{
    //    requestAgent.request("theme.liked.list", Integer.toString(userNo));
    //
    //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    //      throw new Exception(requestAgent.getObject(String.class));
    //    }
    //
    //    return new ArrayList<>(requestAgent.getObjects(Theme.class));
    return null;
  }

}
