package com.welcomeToJeJu.moj.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.request.RequestAgent;

// 역할
// - 게시글을 데이터를 서버를 통해 관리한다.
//
public class NetThemeDao implements ThemeDao {

  RequestAgent requestAgent;

  public NetThemeDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Theme theme) throws Exception {
    requestAgent.request("theme.insert", theme);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 데이터 저장 실패!");
    }
  }

  @Override
  public List<Theme> findAll() throws Exception {
    requestAgent.request("theme.selectList", null);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Theme.class));
  }

  public List<Theme> findLoginUserAll(User loginUser) throws Exception {
    requestAgent.request("theme.selectLoginUserList", loginUser);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Theme.class));
  }

  @Override
  public List<Theme> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("theme.selectListByKeyword", params);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 검색 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Theme.class));
  }

  @Override
  public Theme findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("theme.selectOne", params);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      return null;
    }

    return requestAgent.getObject(Theme.class);
  }

  public void update(Theme theme) throws Exception {
    requestAgent.request("theme.update", theme);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 변경 실패!");
    }
  }

  @Override
  public void delete(String title) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("title", title);

    requestAgent.request("theme.delete", params);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 삭제 실패!");
    }
  }

  @Override
  public void placeInsert(Place place) throws Exception {
    requestAgent.request("theme.place.insert", place);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 삭제 실패!");
    }
  }


  @Override
  public void placeDelete(Theme theme, Place place) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("themeNo", String.valueOf(theme.getNo()));
    params.put("placeName", place.getStoreName());
    requestAgent.request("theme.place.delete", params);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("장소 삭제 실패!");
    }
  }

  @Override
  public void likedThemeInsert(Theme theme, String userName) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("themeNo", String.valueOf(theme.getNo()));
    params.put("userName", userName);

    requestAgent.request("theme.likedTheme.insert", params);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("좋아요 등록 실패!");
    }

  }

  @Override
  public void likedThemeDelete(Theme theme, String userName) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("themeNo", String.valueOf(theme.getNo()));
    params.put("userName", userName);

    requestAgent.request("theme.likedTheme.delete", params);
    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("좋아요 삭제 실패!");
    }


  }

  @Override
  public List<Theme> findByTitle(String title) throws Exception {
    requestAgent.request("theme.selectListByTitle", title);

    if (!requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      throw new Exception("테마 검색 실패!");
    }
    return new ArrayList<>(requestAgent.getObjects(Theme.class));
  }
}

