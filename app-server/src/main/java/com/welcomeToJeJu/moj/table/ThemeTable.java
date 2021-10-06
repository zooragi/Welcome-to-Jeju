package com.welcomeToJeJu.moj.table;

import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.server.DataProcessor;
import com.welcomeToJeJu.server.Request;
import com.welcomeToJeJu.server.Response;

// 역할
// 게시글 데이터를 저장하고 조회하는 일을 한다.
// 게시글 데이터를 파일에 저장하고 파일로부터 로딩하는 일을 한다.
public class ThemeTable extends JsonDataTable<Theme> implements DataProcessor {


  public ThemeTable() {
    super("theme.json", Theme.class);
  }

  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "theme.insert" : insert(request, response); break;
      case "theme.selectList" : selectList(request, response); break;
      case "theme.selectLoginUserList" : selectLoginUserList(request, response); break;
      case "theme.selectOne" : selectOne(request, response); break;
      case "theme.selectOneByTitle" : selectOneByTitle(request, response); break;
      case "theme.selectListByKeyword": selectListByKeyword(request, response); break;
      case "theme.update" : update(request, response); break;
      case "theme.delete" : delete(request, response); break;
      case "theme.place.insert" : placeInsert(request, response); break;
      case "theme.place.delete" : placeDelete(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void placeDelete(Request request, Response response) {
    Place place = request.getObject(Place.class);
    Place deletePlace = placeSelectOne(place.getStoreName());
    Theme theme = findByTitle(deletePlace.getTheme().getTitle());
    int index = indexOfPlaceTitle(deletePlace.getTheme().getTitle());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 장소 없음!");
      return;
    }
    theme.getPlaceList().remove(index);
    response.setStatus(Response.SUCCESS);

  }

  private Place placeSelectOne(String storeName) {
    for(Theme theme : list) {
      for(Place p : theme.getPlaceList()) {
        if(p.getStoreName().equals(storeName)) {
          return p;
        }
      }
    }
    return null;
  }


  private void placeInsert(Request request, Response response) {
    Place place = request.getObject(Place.class);
    Theme theme = findByTitle(place.getTheme().getTitle());
    theme.getPlaceList().add(place);
    response.setStatus(Response.SUCCESS);
  }



  private void selectListByKeyword(Request request, Response response) {
    //    String keyword = request.getParameter("keyword");
    //
    //    ArrayList<Theme> searchResult = new ArrayList<>();
    //    for (Theme theme : list) {
    //      if (!theme.getTitle().contains(keyword) &&
    //          !theme.getContent().contains(keyword) &&
    //          !theme.getWriter().getName().contains(keyword)) {
    //        continue;
    //      }
    //      searchResult.add(theme);
    //    }

    response.setStatus(Response.SUCCESS);
    //    response.setValue(searchResult);

  }

  private void delete(Request request, Response response) {
    //    int no = Integer.parseInt(request.getParameter("no"));
    String title = request.getParameter("title");
    int index = indexOfTitle(title);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 테마 없음!");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);

  }

  private void update(Request request, Response response) {
    Theme theme = request.getObject(Theme.class);
    int index = indexOf(theme.getNo());
    if(index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 테마 없음!");
      return;
    }

    list.set(index, theme);
    response.setStatus(Response.SUCCESS);

  }

  private void selectList(Request request, Response response) {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectLoginUserList(Request request, Response response) {
    List<Theme> loginUserThemeList = new ArrayList<>();
    User loginUser = request.getObject(User.class);
    for(Theme theme : list) {
      if(theme.getThemeOwnerName().equals(loginUser.getNickName())) {
        loginUserThemeList.add(theme);
      }
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(loginUserThemeList);
  }

  private void insert(Request request, Response response) throws Exception {
    Theme theme = request.getObject(Theme.class);
    list.add(theme);

    response.setStatus(Response.SUCCESS);
  }


  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Theme t = findByNo(no);

    if (t != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(t);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 테마 없음!");
    }

  }

  private void selectOneByTitle(Request request, Response response) throws Exception {
    String title = request.getObject(String.class);
    Theme t = findByTitle(title);

    if (t != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(t);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 테마 없음!");
    }

  }

  private Theme findByNo(int no) {
    for(Theme t : list) {
      if(t.getNo() == no) {
        return t;
      }
    }
    return null;
  }

  private Theme findByTitle(String title) {
    for(Theme t : list) {
      if(t.getTitle().equals(title)) {
        return t;
      }
    }
    return null;
  }

  private int indexOf(int themeNo) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == themeNo) {
        return i;
      }
    }
    return -1;
  }

  private int indexOfTitle(String title) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getTitle().equals(title)) {
        return i;
      }
    }
    return -1;
  }


  private int indexOfPlaceTitle(String title) {
    for(int i = 0; i < list.size(); i++) {
      for(int j = 0; j < list.size(); j++) {
        if(list.get(i).getPlaceList().get(j).getStoreName().equals(title)) {
          return j;
        }
      }
    }
    return -1;
  }
}
