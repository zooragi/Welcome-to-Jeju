package com.welcomeToJeJu.moj.dao;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;

public interface UserDao {
  void insert(User user) throws Exception;
  List<User> findAll() throws Exception;
  User findByNo(int no) throws Exception;
  void update(User board) throws Exception;
  void delete(int no) throws Exception;
  List<User> findByKeyword(String keyword) throws Exception;
  void themeInsert(Theme theme) throws Exception;
}   
