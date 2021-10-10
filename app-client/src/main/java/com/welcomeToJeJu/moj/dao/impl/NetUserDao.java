package com.welcomeToJeJu.moj.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.moj.dao.UserDao;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;

// 역할
// - 게시글을 데이터를 서버를 통해 관리한다.
//
public class NetUserDao implements UserDao {

  RequestAgent requestAgent;

  public NetUserDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(User user) throws Exception {
    requestAgent.request("user.insert", user);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("회원 데이터 저장 실패!");
    }
  }

  @Override
  public List<User> findAll() throws Exception {
    requestAgent.request("user.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유저 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(User.class));
  }

  @Override
  public List<User> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("user.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유저 검색 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(User.class));
  }

  @Override
  public User findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("user.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(User.class);
  }

  @Override
  public void update(User user) throws Exception {
    requestAgent.request("user.update", user);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유저 변경 실패!");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("user.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("유저 삭제 실패!");
    }
  }

  @Override
  public User findByEmailPassword(String email, String password) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    requestAgent.request("user.selectOneByEmailPassword", params);

    if(!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      return null;
    }
    return requestAgent.getObject(User.class);
  }

  @Override
  public User findByName(String name) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("nickname", name);

    requestAgent.request("user.selectOneByName", params);
    if(!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      return null;
    }
    return requestAgent.getObject(User.class);
  }

  @Override
  public void userLikedUserInsert(String likedUser, String loginUser) throws Exception {
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("likedUser",likedUser);
    parameter.put("loginUser",loginUser);

    requestAgent.request("user.likedUser.insert", parameter);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("좋아하는 유저 등록 실패!");
    }

  }

  @Override
  public void userLikedUserDelete(String likedUser, String loginUser) throws Exception {
    HashMap<String, String> parameter = new HashMap<>();
    parameter.put("likedUser",likedUser);
    parameter.put("loginUser",loginUser);

    requestAgent.request("user.likedUser.delete", parameter);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("좋아하는 유저 등록 실패!");
    }
  }

  @Override
  public List<String> likedUserFindAll(User loginUser) throws Exception {
    requestAgent.request("user.likedUser.list", loginUser);

    if(!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      return null;
    }
    return new ArrayList<>(requestAgent.getObjects(String.class));
  }
}

