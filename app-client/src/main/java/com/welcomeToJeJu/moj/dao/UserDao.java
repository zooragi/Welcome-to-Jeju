package com.welcomeToJeJu.moj.dao;

import java.util.List;
import com.welcomeToJeJu.moj.domain.User;

public interface UserDao {
  void insert(User user) throws Exception;
  List<User> findAll() throws Exception;
  User findByNo(int no) throws Exception;
  void update(User board) throws Exception;
  void delete(int no) throws Exception;
  List<User> findByKeyword(String keyword) throws Exception;
  User findByEmailPassword (String email, String password) throws Exception;
  User findByName(String name) throws Exception;
  void userLikedUserInsert(String likedUser, String loginUser) throws Exception;
  void userLikedUserDelete(String likedUser, String loginUser) throws Exception;
  List<String> likedUserFindAll(User user) throws Exception;
}   
