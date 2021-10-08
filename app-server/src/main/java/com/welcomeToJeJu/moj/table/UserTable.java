package com.welcomeToJeJu.moj.table;

import java.util.List;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.server.DataProcessor;
import com.welcomeToJeJu.server.Request;
import com.welcomeToJeJu.server.Response;

// 역할
// 회원 데이터를 저장하고 조회하는 일을 한다.
// 회원 데이터를 파일에 저장하고 파일로부터 로딩하는 일을 한다.
public class UserTable extends JsonDataTable<User> implements DataProcessor{

  public UserTable() {
    super("user.json", User.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch(request.getCommand()) {
      case "user.insert" : insert(request, response); break;
      case "user.selectList" : selectList(request, response); break;
      case "user.selectOne" : selectOne(request, response); break;
      case "user.selectOneByEmailPassword" : selectOneByEmailPassword(request, response); break;
      case "user.selectOneByName": selectOneByName(request, response); break;
      case "user.update" : update(request, response); break;
      case "user.delete" : delete(request, response); break;
      case "user.likedUser.insert" : likedUserInsert(request, response); break;
      case "user.likedUser.list" : likedUserList(request, response); break;
      case "user.likedUser.delete" : likedUserDelete(request, response); break;
      case "user.theme.insert" : themeInsert(request, response); break;
      case "user.theme.delete" : themeDelete(request, response); break;
      case "user.theme.update" : themeUpdate(request, response); break;
      default :
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }




  private void themeUpdate(Request request, Response response) {
    Theme theme = request.getObject(Theme.class);
    User user = findByName(theme.getThemeOwnerName());
    int index = indexOf(user.getNo());
    if(index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
      return;
    }

    user.getThemeList().set(index, theme);
    response.setStatus(Response.SUCCESS);
  }

  private void themeDelete(Request request, Response response) {
    Theme theme = request.getObject(Theme.class);
    User user = findByName(theme.getThemeOwnerName());
    int index = indexOfTitle(user,theme.getTitle());
    user.getThemeList().remove(index);

    response.setStatus(Response.SUCCESS);

  }

  private void themeInsert(Request request, Response response) {
    Theme theme = request.getObject(Theme.class);
    User user = findByName(theme.getThemeOwnerName());
    user.getThemeList().add(theme);

    response.setStatus(Response.SUCCESS);
  }

  private void likedUserDelete(Request request, Response response) {
    String loginUserName = request.getParameter("loginUser");
    String likedUserName = request.getParameter("likedUser");
    User loginUser = null;
    User likedUser = null;
    for(User u : list) {
      if(u.getNickName().equals(loginUserName)) {
        loginUser = u;
        break;
      }
    }

    for(User u : list) {
      if(u.getNickName().equals(likedUserName)) {
        likedUser = u;
        break;
      }
    }
    loginUser.getLikedUsers().remove(likedUser);
    response.setStatus(Response.SUCCESS);

  }

  private void likedUserList(Request request, Response response) {
    User user = request.getObject(User.class);
    User loginUser =findByNo(user.getNo());
    List<User> likedUserList = loginUser.getLikedUsers();
    response.setStatus(Response.SUCCESS);
    response.setValue(likedUserList);
  }

  private void likedUserInsert(Request request, Response response) {
    String loginUserName = request.getParameter("loginUser");
    String likedUserName = request.getParameter("likedUser");
    User loginUser = null;
    User likedUser = null;
    for(User u : list) {
      if(u.getNickName().equals(loginUserName)) {
        loginUser = u;
        break;
      }
    }

    for(User u : list) {
      if(u.getNickName().equals(likedUserName)) {
        likedUser = u;
        break;
      }
    }

    if(likedUser != null) {
      loginUser.getLikedUsers().add(likedUser);
      response.setStatus(Response.SUCCESS);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
    }
  }

  private void selectOneByEmailPassword(Request request, Response response) {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = null;
    for(User u : list) {
      if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
        user = u;
        break;
      }
    }
    if(user != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(user);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
    }
  }


  private void selectOneByName(Request request, Response response) throws Exception {
    String name = request.getParameter("nickname");
    System.out.println("-----> " + name);
    User user = findByName(name);
    if (user != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(user);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
    }
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private void insert(Request request, Response response) throws Exception {
    User user = request.getObject(User.class);
    list.add(user);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.valueOf(request.getParameter("no"));
    User user = findByNo(no);
    if(user != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(user);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
    }
  }

  private void update(Request request, Response response) {
    User user = request.getObject(User.class);
    int index = indexOf(user.getNo());
    if(index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("등록된 유저 없음!");
      return;
    }

    list.set(index, user);
    response.setStatus(Response.SUCCESS);
  }

  private User findByNo(int no) {
    for(User u : list) {
      if(u.getNo() == no) {
        return u;
      }
    }
    return null;
  }

  private User findByName(String name) {
    for(User u : list) {
      if(u.getNickName().equals(name)) {
        return u;
      }
    }
    return null;
  }


  private int indexOf(int userNo) {
    for(int i = 0; i < list.size(); i++) {
      if(list.get(i).getNo() == userNo) {
        return i;
      }
    }
    return -1;
  }

  @SuppressWarnings("unlikely-arg-type")
  private int indexOfTitle(User user, String themeName) {
    for(int i = 0; i < list.size(); i++) {
      if(user.getThemeList().get(i).equals(themeName)) {
        return i;
      }
    }
    return -1;
  }

}
