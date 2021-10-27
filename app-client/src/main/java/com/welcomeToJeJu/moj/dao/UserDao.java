package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.User;

public interface UserDao {
  void insert(User user) throws Exception;
  //  User search(String name) throws Exception;
  void delete(int userNo) throws Exception;
  void update(User user) throws Exception;
  List<User> findAll() throws Exception;
  void insertLikedUser(int loginUserNo, int likedUserNo) throws Exception;
  void deleteLikedUser(int loginUserNo, int likedUserNo) throws Exception;
  List<User> findAllLikedUser(int loginUserNo) throws Exception;
  User findByEmailAndPassword(String email, String password) throws Exception;
  User findByNo(int userNo) throws Exception;
  User findByName(String name) throws Exception;
}
