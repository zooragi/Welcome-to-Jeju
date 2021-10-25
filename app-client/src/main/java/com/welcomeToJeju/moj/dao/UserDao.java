package com.welcomeToJeju.moj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.welcomeToJeju.moj.domain.User;

public interface UserDao {

  void insert(User user) throws Exception;
  void update(User user) throws Exception;
  void delete(int No) throws Exception;
  List<User> findAll() throws Exception;
  User findByNickName(String nickName) throws Exception;

  User findByEmailAndPassword(@Param("email") String email, @Param("password") String password) throws Exception;

  void insertLikedUser(@Param("likedUserNo") int likedUserNo, @Param("loginUserNo") int loginUserNo) throws Exception;
  void deleteLikedUser(@Param("likedUserNo") int likedUserNo, @Param("loginUserNo") int loginUserNo) throws Exception;
  List<User> findAllLikedUser(int loginUserNo) throws Exception;


}
