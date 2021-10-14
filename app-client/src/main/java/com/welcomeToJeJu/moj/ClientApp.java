package com.welcomeToJeJu.moj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.welcomeToJeJu.context.UserContextListener;
import com.welcomeToJeJu.menu.Menu;
import com.welcomeToJeJu.menu.MenuFilter;
import com.welcomeToJeJu.menu.MenuGroup;
import com.welcomeToJeJu.moj.dao.impl.NetThemeDao;
import com.welcomeToJeJu.moj.dao.impl.NetUserDao;
import com.welcomeToJeJu.moj.domain.Report;
import com.welcomeToJeJu.moj.domain.ReportTheme;
import com.welcomeToJeJu.moj.domain.ReportUser;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.moj.domain.User;
import com.welcomeToJeJu.moj.handler.AllThemeListHandler;
import com.welcomeToJeJu.moj.handler.AuthDisplayLoginUserHandler;
import com.welcomeToJeJu.moj.handler.AuthLoginHandler;
import com.welcomeToJeJu.moj.handler.AuthLogoutHandler;
import com.welcomeToJeJu.moj.handler.Command;
import com.welcomeToJeJu.moj.handler.CommandRequest;
import com.welcomeToJeJu.moj.handler.LikedThemeAddHandler;
import com.welcomeToJeJu.moj.handler.LikedThemeDeleteHandler;
import com.welcomeToJeJu.moj.handler.LikedThemeListHandler;
import com.welcomeToJeJu.moj.handler.LikedUserAddHandler;
import com.welcomeToJeJu.moj.handler.LikedUserDeleteHandler;
import com.welcomeToJeJu.moj.handler.LikedUserListHandler;
import com.welcomeToJeJu.moj.handler.MyThemeAddHandler;
import com.welcomeToJeJu.moj.handler.MyThemeDeleteHandler;
import com.welcomeToJeJu.moj.handler.MyThemeDetailHandler;
import com.welcomeToJeJu.moj.handler.MyThemeListHandler;
import com.welcomeToJeJu.moj.handler.MyThemeUpdateHandler;
import com.welcomeToJeJu.moj.handler.PlaceAddHandler;
import com.welcomeToJeJu.moj.handler.PlaceDeleteHandler;
import com.welcomeToJeJu.moj.handler.PlaceListHandler;
import com.welcomeToJeJu.moj.handler.UserAddHandler;
import com.welcomeToJeJu.moj.handler.UserDeleteHandler;
import com.welcomeToJeJu.moj.handler.UserDetailHandler;
import com.welcomeToJeJu.moj.handler.UserEditHandler;
import com.welcomeToJeJu.moj.handler.UserListHandler;
import com.welcomeToJeJu.moj.handler.UserUnregisterHandler;
import com.welcomeToJeJu.moj.handler.UserUpdateHandler;
import com.welcomeToJeJu.moj.listener.LoginListener;
import com.welcomeToJeJu.request.RequestAgent;
import com.welcomeToJeJu.util.Prompt;

public class ClientApp {

  RequestAgent requestAgent;

  List<User> userList = new ArrayList<>();
  public static List<Report> reportList = new ArrayList<>();
  List<ReportTheme> reportThemeList = new ArrayList<>();
  List<ReportUser> reportUserList = new ArrayList<>();
  HashMap<String, Command> commandMap = new HashMap<>();
  List<Theme> themeList = new ArrayList<>();
  List<UserContextListener> userListeners = new ArrayList<>();


  public void addUserContextListener(UserContextListener userListener) {
    this.userListeners.add(userListener);
  }

  public void removeUserContextListener(UserContextListener userListener) {
    this.userListeners.remove(userListener);
  }

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int enableState, String menuId) {
      super(title, enableState);
      this.menuId = menuId;
    }

    @Override

    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.printf("%s 명령을 실행하는 중 오류 발생!", menuId);
        e.printStackTrace();
      } 
    }

  }

  public ClientApp() throws Exception {

    requestAgent = new RequestAgent("127.0.0.1", 8888);
    NetThemeDao themeDao = new NetThemeDao(requestAgent);
    NetUserDao userDao = new NetUserDao(requestAgent);

    commandMap.put("/auth/login", new AuthLoginHandler(userDao,userListeners));
    commandMap.put("/auth/logout", new AuthLogoutHandler(userListeners));
    commandMap.put("/auth/displayLoginUser", new AuthDisplayLoginUserHandler());
    commandMap.put("/theme/all", new AllThemeListHandler(themeDao));
    commandMap.put("/auth/unregistered", new UserUnregisterHandler(userDao));
    commandMap.put("/auth/edit", new UserEditHandler(userDao));

    commandMap.put("/user/add", new UserAddHandler(userDao));
    commandMap.put("/user/delete", new UserDeleteHandler(userDao));
    commandMap.put("/user/detail", new UserDetailHandler(userDao));
    commandMap.put("/user/list", new UserListHandler(userDao));
    commandMap.put("/user/update", new UserUpdateHandler(userDao));

    commandMap.put("/myTheme/add", new MyThemeAddHandler(themeDao));
    commandMap.put("/myTheme/delete", new MyThemeDeleteHandler(themeDao));
    commandMap.put("/myTheme/list", new MyThemeListHandler(themeDao));
    commandMap.put("/myTheme/detail", new MyThemeDetailHandler(requestAgent));
    commandMap.put("/myTheme/update", new MyThemeUpdateHandler(themeDao));

    commandMap.put("/likedTheme/add", new LikedThemeAddHandler(themeDao));
    commandMap.put("/likedTheme/delete", new LikedThemeDeleteHandler(themeDao));
    commandMap.put("/likedTheme/list", new LikedThemeListHandler(themeDao));

    commandMap.put("/place/add", new PlaceAddHandler(themeDao));
    commandMap.put("/place/delete", new PlaceDeleteHandler(themeDao));
    commandMap.put("/place/list", new PlaceListHandler(themeDao));


    //    commandMap.put("/search/searchTheme", new SearchThemeHandler(userList, themeList));
    //    commandMap.put("/search/searchUser", new SearchUserHandler(userList));
    //    commandMap.put("/search/searchHashtag", new SearchHashtagHandler(userList, themeList));

    commandMap.put("/likedUser/add", new LikedUserAddHandler(userDao));
    commandMap.put("/likedUser/list", new LikedUserListHandler(userDao));
    commandMap.put("/likedUser/delete", new LikedUserDeleteHandler(userDao));

    //    commandMap.put("/rank/themeRank", new RealTimeRankHandler(userList));
    //    commandMap.put("/rank/userRank", new UserRankHandler(userList));

    //    commandMap.put("/report/theme", new ReportAddThemeHandler(userList,reportThemeList));
    //    commandMap.put("/report/user", new ReportAddUserHandler(userList,reportUserList));
    //    commandMap.put("/report/list", new ReportMyListHandler(reportList));
    //    commandMap.put("/report/themeProcess", new ReportThemeProcessingHandler(userList,reportThemeList));
    //    commandMap.put("/report/userProcess", new ReportUserProcessingHandler(userList,reportUserList));
  }

  MenuFilter menuFilter = menu ->
  (menu.getAccessScope() & AuthLoginHandler.getUserAccessLevel()) > 0 ;


  public static void main(String[] args) throws Exception {
    ClientApp clientApp = new ClientApp();
    clientApp.addUserContextListener(new LoginListener());
    clientApp.service();
    Prompt.close();


  }


  void service() throws Exception{

    createMenu().execute();

    requestAgent.request("quit", null);

    Prompt.close();
  }


  Menu createMenu() {
    MenuGroup mg = new MenuGroup("메인");
    mg.setMenuFilter(menuFilter);
    mg.setPrevMenuTitle("종료");

    mg.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mg.add(new MenuItem("회원 가입하기", Menu.ACCESS_LOGOUT, "/user/add"));
    mg.add(new MenuItem("내 정보", Menu.ACCESS_GENERAL, "/auth/displayLoginUser"));
    //    mg.add(new MenuItem(""))
    mg.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/logout"));
    mg.add(new MenuItem("전체 테마 보기", "/theme/all"));

    createUserMenu(mg);
    createMyMapMenu(mg);
    //    createPlaceMenu(mg);
    createSearchMenu(mg);
    createLikedThemeMenu(mg);
    createLikedUserMenu(mg);
    createRankMenu(mg);
    createReportMenu(mg);


    return mg;
  }


  private Menu createUserMenu(MenuGroup mg) {
    MenuGroup user = new MenuGroup("회원관리",Menu.ACCESS_ADMIN);
    user.setMenuFilter(menuFilter);
    user.add(new MenuItem("회원 목록보기", Menu.ACCESS_ADMIN, "/user/list"));
    user.add(new MenuItem("회원 상세보기", Menu.ACCESS_ADMIN, "/user/detail"));
    user.add(new MenuItem("회원 수정하기", Menu.ACCESS_ADMIN, "/user/update"));
    user.add(new MenuItem("회원 삭제하기", Menu.ACCESS_ADMIN, "/user/delete"));

    mg.add(user);
    return user;
  }

  private void createMyMapMenu(MenuGroup mg) {
    MenuGroup myMap = new MenuGroup("나의 테마", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
    myMap.setMenuFilter(menuFilter);
    myMap.add(new MenuItem("테마 만들기", "/myTheme/add"));
    myMap.add(new MenuItem("테마 목록보기", "/myTheme/list"));
    myMap.add(new MenuItem("테마 상세보기", "/myTheme/detail"));
    //    myMap.add(new MenuItem("테마 수정하기", "/myTheme/update"));
    myMap.add(new MenuItem("테마 삭제하기", "/myTheme/delete"));

    mg.add(myMap);
  }

  //  private void createPlaceMenu(MenuGroup mg) {
  //    MenuGroup savePlaceInTheme = new MenuGroup("테마에 장소 추가", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
  //
  //    savePlaceInTheme.add(new MenuItem("장소 등록", "/place/add"));
  //    savePlaceInTheme.add(new MenuItem("장소 목록", "/place/list"));
  //    savePlaceInTheme.add(new MenuItem("장소 삭제", "/place/delete"));
  //
  //    mg.add(savePlaceInTheme);
  //  }

  private void createSearchMenu(MenuGroup mg) {
    MenuGroup search = new MenuGroup("검색하기");
    search.setMenuFilter(menuFilter);

    search.add(new MenuItem("테마 검색하기", "/search/searchTheme"));
    // 장소 이쁘게 출력하기 수정필요
    search.add(new MenuItem("유저 검색하기", "/search/searchUser"));
    search.add(new MenuItem("해시태그 검색하기", "/search/searchHashtag"));


    mg.add(search);
  }

  private void createLikedThemeMenu(MenuGroup mg) {
    MenuGroup like = new MenuGroup("좋아하는 테마", Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL);
    like.setMenuFilter(menuFilter);
    like.add(new MenuItem("좋아요 등록하기", "/likedTheme/add"));
    like.add(new MenuItem("좋아요 목록보기", "/likedTheme/list"));
    like.add(new MenuItem("좋아요 삭제하기", "/likedTheme/delete"));

    mg.add(like);
  }

  private void createRankMenu(MenuGroup mg) {
    MenuGroup rank = new MenuGroup("순위보기");
    rank.setMenuFilter(menuFilter);
    rank.add(new MenuItem("테마 순위보기", "/rank/themeRank")); // 전체 테마 검색 기준 조횟수 증가
    rank.add(new MenuItem("유저 순위보기", "/rank/userRank")); // 유저 검색 기준 조횟수 증가

    mg.add(rank);
  }

  private void createLikedUserMenu(MenuGroup mg) {
    MenuGroup follow = new MenuGroup("좋아하는 유저", Menu.ACCESS_GENERAL);
    follow.setMenuFilter(menuFilter);
    follow.add(new MenuItem("좋아하는 유저 등록하기", "/likedUser/add"));
    follow.add(new MenuItem("좋아하는 유저 목록보기", "/likedUser/list"));
    follow.add(new MenuItem("좋아하는 유저 삭제하기", "/likedUser/delete"));

    mg.add(follow);
  }

  private void createReportMenu(MenuGroup mg) {
    MenuGroup report = new MenuGroup("신고하기", Menu.ACCESS_GENERAL);
    report.setMenuFilter(menuFilter);
    report.add(new MenuItem("테마 신고", "/report/theme"));
    report.add(new MenuItem("유저 신고", "/report/user"));
    report.add(new MenuItem("나의 신고 목록", "/report/list"));
    report.add(new MenuItem("테마 신고 처리", Menu.ACCESS_ADMIN,"/report/themeProcess"));
    report.add(new MenuItem("유저 신고 처리", Menu.ACCESS_ADMIN,"/report/userProcess"));
    mg.add(report);
  }


}