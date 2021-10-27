package com.welcomeToJeju.moj.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.User;

public class MariadbUserDao implements UserDao{

  Connection con;

  public MariadbUserDao(Connection con) {
    this.con = con;
  }

  //완
  @Override
  public void insert(User user) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into jeju_user (email, password, nickname)"
            + " values(?,password(?),?)");) {

      stmt.setString(1, user.getEmail());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getNickName());

      if(stmt.executeUpdate() == 0) {
        throw new Exception("유저 데이터 저장 실패!");
      }
    }
  }


  //완
  @Override
  public void delete(int userNo) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "delete from jeju_user where user_no = ?");) {

      stmt.setInt(1, userNo);

      if(stmt.executeUpdate() == 0) {
        throw new Exception("유저 데이터 삭제 실패!");
      }
    }
  }

  //  @Override
  //  public User search(String name) throws Exception {
  //
  //
  //    return null;
  //  }

  //완
  @Override
  public void update(User user) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "update jeju_user set"
            + " email =?, password = password(?), nickname = ?"
            + " where user_no = ?");) {

      stmt.setString(1, user.getEmail());
      stmt.setString(2, user.getPassword());
      stmt.setString(3, user.getNickName());
      stmt.setInt(4, user.getNo());

      if(stmt.executeUpdate() == 0) {
        throw new Exception("유저 데이터 변경 실패!");
      }
    }
  }

  //완
  @Override
  public List<User> findAll() throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select user_no, email, nickname, created_dt from jeju_user order by user_no asc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<User> list = new ArrayList<>();

      while(rs.next()) {
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setEmail(rs.getString("email"));
        user.setNickName(rs.getString("nickname"));
        user.setRegisteredDate(rs.getDate("created_dt"));
        list.add(user);
      }
      return list;
    }
  }

  @Override
  public void userLikedUserInsert(int likedUserNo, int loginUserNo) throws Exception {

    try(PreparedStatement stmt = con.prepareStatement
        ("insert into jeju_liked_user(user_no, user_no2) values(?,?)")) {

      stmt.setInt(1, loginUserNo);
      stmt.setInt(2, likedUserNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 저장 실패!");
      }
    }
  }

  public void userLikedUserDelete(int likedUserNo, int loginUserNo) throws Exception {

    try(PreparedStatement stmt = con.prepareStatement
        ("delete from jeju_liked_user where user_no=? and user_no2=? ")) {

      stmt.setInt(1, loginUserNo);
      stmt.setInt(2, likedUserNo);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("회원 데이터 삭제 실패!");
      }
    }

  }

  @Override
  public List<User> likedUserFindAll(int loginUserNo) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select" 
            +" ju.user_no,"
            +" email,"
            +" nickname,"
            +" created_dt" 
            +" from jeju_user ju"
            +" join (select jlu.user_no2 from jeju_user ju natural join jeju_liked_user jlu where user_no=?) jlu"
            +" on jlu.user_no2 = ju.user_no")){

      stmt.setInt(1, loginUserNo);

      ArrayList<User> list = new ArrayList<>();

      try(ResultSet rs = stmt.executeQuery()){
        while(rs.next()) {
          User user = new User();
          user.setNo(rs.getInt("user_no"));
          user.setEmail(rs.getString("email"));
          user.setNickName(rs.getString("nickname"));
          user.setRegisteredDate(rs.getDate("created_dt"));
          list.add(user);
        }
      }
      return list;
    }
  }

  //완
  @Override
  public User findByEmailAndPassword(String email, String password) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select user_no, email, nickname, created_dt from jeju_user"        
            + " where email = ? and password = password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setEmail(rs.getString("email"));
        user.setNickName(rs.getString("nickname"));
        user.setRegisteredDate(rs.getDate("created_dt"));

        return user;
      }
    }
  }


  //완
  @Override
  public User findByNo(int userNo) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select user_no, email, nickname, created_dt from"
            + " jeju_user where user_no =" + userNo);
        ResultSet rs = stmt.executeQuery()) {

      if (!rs.next()) {
        return null;
      }
      User user = new User();
      user.setNo(rs.getInt("user_no"));
      user.setEmail(rs.getString("email"));
      user.setNickName(rs.getString("nickname"));
      user.setRegisteredDate(rs.getDate("created_dt"));

      return user;
    }
  }

  //완
  @Override
  public User findByName(String name) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select user_no, email, nickname, created_dt from"        
            + " jeju_user where nickname = ?")) {

      stmt.setString(1,name);

      try (ResultSet rs = stmt.executeQuery()) {
        if (!rs.next()) {
          return null;
        }
        User user = new User();
        user.setNo(rs.getInt("user_no"));
        user.setEmail(rs.getString("email"));
        user.setNickName(rs.getString("nickname"));
        user.setRegisteredDate(rs.getDate("created_dt"));

        return user;
      }
    }
  }

}
