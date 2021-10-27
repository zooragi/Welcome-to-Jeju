package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;

public class MariadbThemeDao implements ThemeDao{
  Connection con;

  public MariadbThemeDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Theme theme) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into jeju_theme(title, public, category, user_no) values(?,?,?,?)",
        Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, theme.getTitle());
      stmt.setBoolean(2, theme.isPublic());
      stmt.setString(3, theme.getCategory());
      stmt.setInt(4,theme.getThemeOwnerNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("테마 입력 실패!");
      }

      int themeNo = 0;
      try (ResultSet pkRS = stmt.getGeneratedKeys()) {
        if(pkRS.next()) {
          themeNo = pkRS.getInt("theme_no");
        }
      }

      try(PreparedStatement stmt2 = con.prepareStatement(
          "insert into jeju_hashtag(theme_no,name) values(?,?)")) {
        for (String hashtag : theme.getHashtags()) {
          stmt2.setInt(1, themeNo);
          stmt2.setString(2, hashtag);
          //          theme.getHashtags().add(hashtag);
          stmt2.executeUpdate();
        }
      }

    }
  }

  @Override
  public List<Theme> findAll() throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select t.theme_no, t.title, t.public, t.category, h.name,"
            + " t.user_no"
            + " from jeju_theme t"
            + " left outer join jeju_hashtag h on t.theme_no = h.theme_no");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Theme> list = new ArrayList<>();
      int themeNo = 0;
      Theme theme = null;

      while(rs.next()) {
        if(themeNo != rs.getInt("theme_no")) {
          theme = new Theme();
          theme.setNo(rs.getInt("theme_no"));
          theme.setTitle(rs.getString("title"));
          theme.setPublic(rs.getBoolean("public"));
          theme.setCategory(rs.getString("category"));
          theme.setThemeOwnerNo(rs.getInt("user_no"));

          list.add(theme);
          themeNo = theme.getNo();
        }

        if(rs.getString("name") != null) {
          String name = rs.getString("name");
          theme.getHashtags().add(name);
        }
      }
      return list;          
    }
  }


  @Override
  public void update(Theme theme) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update jeju_theme set"
            + " title =?, public=?, category=?, view_cnt = ?"
            + " where theme_no =?")) {

      stmt.setString(1, theme.getTitle());
      stmt.setBoolean(2, theme.isPublic());
      stmt.setString(3, theme.getCategory());
      stmt.setInt(4, theme.getViewCount());
      stmt.setInt(5, theme.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("테마 변경 실패!");
      }

      try (PreparedStatement stmt2 = con.prepareStatement(
          "delete from jeju_hashtag where theme_no =?")) {
        stmt2.setInt(1, theme.getNo());
        stmt2.executeUpdate();
      }

      // 프로젝트 새 멤버 입력
      try(PreparedStatement stmt2 = con.prepareStatement(
          "insert into jeju_hashtag(theme_no,name) values(?,?)")) {
        for (String hashtag : theme.getHashtags()) {
          stmt2.setInt(1, theme.getNo());
          stmt2.setString(2, hashtag);
          stmt2.executeUpdate();
        }
      }
    }
  }

  @Override
  public void delete(Theme theme) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from jeju_theme where theme_no =?");
        PreparedStatement stmt2 = con.prepareStatement(
            "delete from jeju_hashtag where theme_no =?")) {

      stmt2.setInt(1,theme.getNo());
      stmt2.executeUpdate();

      stmt.setInt(1, theme.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("테마 삭제 실패!");
      }
    }
  }

  @Override
  public void placeInsert(Place place) throws Exception {


  }

  @Override
  public void placeDelete(Place place) throws Exception {


  }


  @Override
  public List<Theme> hashtagSearch(String hashtagName) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select h.theme_no, t.title, t.category, u.user_no"
            + " from jeju_hashtag h"
            + " inner join jeju_user u on h.user_no = u.user_no"
            + " inner join jeju_theme t on h.theme_no = t.theme_no"
            + " where h.name=" + hashtagName);
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Theme> list = new ArrayList<>();
      Theme theme = null;

      while(rs.next()) {
        if(hashtagName.equals(rs.getString("name"))) {
          theme = new Theme();
          theme.setNo(rs.getInt("theme_no"));
          theme.setTitle(rs.getString("title"));
          theme.setCategory(rs.getString("category"));
          theme.setThemeOwnerNo(rs.getInt("user_no"));
          list.add(theme);
        }
      }
      return list;
    }
  }

  @Override
  public void likedThemeInsert(int themeNo, int userNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into jeju_liked_theme(user_no, theme_no) values(?,?)")) {
      // user_no, theme_no 조인하기
      stmt.setInt(1, userNo);
      stmt.setInt(2, themeNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("좋아하는 테마 등록 실패!");
      }
    }
  }

  @Override
  public void likedThemeDelete(int themeNo,int userNo) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from jeju_liked_theme where theme_no =? ")) {
      // 조인하기
      stmt.setInt(1, themeNo);
      if (stmt.executeUpdate() == 0) {
        throw new Exception("좋아하는 테마 삭제 실패!");
      }
    }
  }

  @Override
  public List<Theme> likedThemeList(int userNo) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select t2.user_no, t2.theme_no, t.title, t.category"
            + " from jeju_liked_theme t2"
            + " inner join jeju_user u on t2.user_no = u.user_no"
            + " inner join jeju_theme t on t2.theme_no = t.theme_no"
            + " where t2.user_no =" + userNo);
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Theme> list = new ArrayList<>();
      Theme theme = null;

      while(rs.next()) {
        if(userNo == rs.getInt("user_no")) {
          theme = new Theme();
          theme.setNo(rs.getInt("theme_no"));
          theme.setTitle(rs.getString("title"));
          theme.setCategory(rs.getString("category"));
          list.add(theme);
        }
      }
      return list;
    }
  }

  @Override
  public Theme findByTitle(String themeTitle) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select theme_no, title, public, category,"
            + " user_no from"        
            + " jeju_theme where title = ?")) {

      stmt.setString(1,themeTitle);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        Theme theme = new Theme();
        theme.setNo(rs.getInt("theme_no"));
        theme.setTitle(rs.getString("title"));
        theme.setPublic(rs.getBoolean("public"));
        theme.setCategory(rs.getString("category"));
        theme.setThemeOwnerNo(rs.getInt("user_no"));

        return theme;
      }
    }
  }
}
