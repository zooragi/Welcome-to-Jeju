package com.welcomeToJeju.moj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.welcomeToJeju.context.UserContextListener;
import com.welcomeToJeju.menu.Menu;
import com.welcomeToJeju.menu.MenuFilter;
import com.welcomeToJeju.menu.MenuGroup;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.ThemePrompt;
import com.welcomeToJeju.moj.handler.UserPrompt;
import com.welcomeToJeju.moj.handler.admin.AdminUserDeleteHandler;
import com.welcomeToJeju.moj.handler.admin.AdminUserDetailHandler;
import com.welcomeToJeju.moj.handler.admin.AdminUserListHandler;
import com.welcomeToJeju.moj.handler.admin.AdminUserUpdateHandler;
import com.welcomeToJeju.moj.handler.likedUser.LikedUserAddHandler;
import com.welcomeToJeju.moj.handler.likedUser.LikedUserDeleteHandler;
import com.welcomeToJeju.moj.handler.likedUser.LikedUserListHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeAddHandler;
import com.welcomeToJeju.moj.handler.theme.myTheme.MyThemeListHandler;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.moj.handler.user.AuthLogoutHandler;
import com.welcomeToJeju.moj.handler.user.AuthUserInfoHandler;
import com.welcomeToJeju.moj.handler.user.UserAddHandler;
import com.welcomeToJeju.moj.handler.user.UserDeleteHandler;
import com.welcomeToJeju.moj.handler.user.UserUpdateHandler;
import com.welcomeToJeju.moj.listener.LoginListener;
import com.welcomeToJeju.request.RequestAgent;
import com.welcomeToJeju.util.Prompt;

public class ClientApp {

  //  Connection con;
  SqlSession sqlSession;

  RequestAgent requestAgent;

  HashMap<String,Command> commandMap = new HashMap<>();

  class MenuItem extends Menu {
    String menuId;

    public MenuItem(String title, String menuId) {
      super(title);
      this.menuId = menuId;
    }

    public MenuItem(String title, int accessScope, String menuId) {
      super(title, accessScope);
      this.menuId = menuId;
    }

    @Override
    public void execute() {
      Command command = commandMap.get(menuId);
      try {
        command.execute(new CommandRequest(commandMap));
      } catch (Exception e) {
        System.out.println("═════════•°• ⚠ •°•═════════");
        System.out.printf("%s 중 오류!\n", menuId);
        e.printStackTrace();
        System.out.println("═════════•°• ⚠ •°•═════════");
      }
    }
  }

  // 옵저버
  List<UserContextListener> userListeners = new ArrayList<>();

  public void addUserContextListener(UserContextListener userListener) {
    this.userListeners.add(userListener);
  }

  public void removeUserContextListener(UserContextListener userListener) {
    this.userListeners.remove(userListener);
  }
  //

  public ClientApp() throws Exception{
    // 서버와 통신을 담당할 객체 준비
    //    requestAgent = new RequestAgent("127.0.0.1",8888);
    requestAgent = null;

    // DMBS와 연결
    //    con = DriverManager.getConnection(
    //        "jdbc:mysql://localhost:3306/jejudb?user=jeju&password=1111");

    // Mybatis의 SqlSession 객체 준비
    sqlSession = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(
        "com/welcomeToJeju/moj/conf/mybatis-config.xml")).openSession();

    // 데이터 관리를 담당할 DAO 객체 준비
    UserDao userDao = sqlSession.getMapper(UserDao.class);
    ThemeDao themeDao = sqlSession.getMapper(ThemeDao.class);
    ReportDao reportDao = sqlSession.getMapper(ReportDao.class);

    //    PlaceDao placeDao = sqlSession.getMapper(PlaceDao.class);

    UserPrompt userPrompt = new UserPrompt(userDao);
    ThemePrompt themePrompt = new ThemePrompt(themeDao);

    // Command 객체 준비
    // 회원
    commandMap.put("/user/add", new UserAddHandler(userDao, sqlSession));
    commandMap.put("/auth/userinfo", new AuthUserInfoHandler());
    commandMap.put("/user/update", new UserUpdateHandler(userDao, sqlSession));
    commandMap.put("/user/delete", new UserDeleteHandler(userDao, sqlSession));

    commandMap.put("/auth/login", new AuthLoginHandler(userDao, userListeners));
    commandMap.put("/auth/logout", new AuthLogoutHandler(userListeners));

    commandMap.put("/myTheme/add", new MyThemeAddHandler(themeDao, sqlSession));
    commandMap.put("/myTheme/list", new MyThemeListHandler(themeDao));
    //    commandMap.put("/myTheme/detail", new MyThemeDetailHandler(themeDao));
    //    commandMap.put("/myTheme/update", new MyThemeUpdateHandler(themeDao, sqlSession));
    //    commandMap.put("/myTheme/delete", new MyThemeDeleteHandler(themeDao, sqlSession));

    // 전체 테마 보기
    //    commandMap.put("/theme/list", new AllThemeListHandler(themeDao));

    //    commandMap.put("/place/add", new PlaceAddHandler(themeDao, sqlSession));
    //    commandMap.put("/place/list", new PlaceListHandler(themeDao));
    //    commandMap.put("/place/delete", new PlaceDeleteHandler(themeDao, sqlSession));
    // 장소 상세 보기!

    //    commandMap.put("/likedTheme/add", new LikedThemeAddHandler(themeDao, sqlSession));
    //    commandMap.put("/likedTheme/list", new LikedThemeListHandler(themeDao, userPrompt));
    //    commandMap.put("/likedTheme/delete", new LikedThemeDeleteHandler(themeDao, sqlSession));

    commandMap.put("/likedUser/add", new LikedUserAddHandler(userDao, userPrompt, sqlSession));
    commandMap.put("/likedUser/list", new LikedUserListHandler(userDao));
    commandMap.put("/likedUser/delete", new LikedUserDeleteHandler(userDao, userPrompt, sqlSession));

    //    commandMap.put("/search/user", new SearchUserHandler(userDao, themePrompt));
    //    commandMap.put("/search/theme", new SearchThemeHandler(themeDao));
    //    commandMap.put("/search/hashTag", new SearchHashTagHandler(themeDao, userPrompt));

    //    commandMap.put("/rank/theme", new ThemeRankHandler(themePrompt));
    //    commandMap.put("/rank/user", new UserRankHandler(userPrompt));

    //    commandMap.put("/report/theme", new ReportThemeAddHandler(reportDao, themePrompt, sqlSession));
    //    commandMap.put("/report/user", new ReportUserAddHandler(reportDao, userPrompt, sqlSession));
    //    commandMap.put("/report/list", new ReportListHandler(reportDao));

    // 관리자: 신고 관리
    //    commandMap.put("/admin/reportThemeProcess", new AdminReportThemeProcessHandler(reportDao, themePrompt, userPrompt));
    //    commandMap.put("/admin/reportUserProcess", new AdminReportUserProcessHandler(reportDao, themePrompt, userPrompt));

    // 관리자: 회원 관리
    commandMap.put("/admin/userList", new AdminUserListHandler(userDao));
    commandMap.put("/admin/userDetail", new AdminUserDetailHandler(userDao));
    commandMap.put("/admin/userUpdate", new AdminUserUpdateHandler(userDao, sqlSession));
    commandMap.put("/admin/userDelete", new AdminUserDeleteHandler(userDao, sqlSession));
  }

  MenuFilter menuFilter = menu -> (menu.getAccessScope() & AuthLoginHandler.getUserAccessLevel()) > 0;

  Menu createMenu() {
    MenuGroup mg = new MenuGroup("🛩️ 메인 ⛰️");
    mg.setPrevMenuTitle("종료");

    mg.setMenuFilter(menuFilter);

    mg.add(new MenuItem("로그인", Menu.ACCESS_LOGOUT, "/auth/login"));
    mg.add(new MenuItem("회원 가입", Menu.ACCESS_LOGOUT, "/user/add"));

    mg.add(new MenuItem("내 정보", Menu.ACCESS_GENERAL, "/auth/userinfo"));
    mg.add(new MenuItem("로그아웃", Menu.ACCESS_GENERAL, "/auth/logout"));

    mg.add(new MenuItem("전체 테마 보기", "/theme/all"));

    createMyThemeMenu(mg);
    createLikedThemeMenu(mg);
    createLikedUserMenu(mg);
    createSearchMenu(mg);
    createRankMenu(mg);
    createReportMenu(mg);
    createAdminUserMenu(mg);    // 관리자: 회원 관리

    return mg;
  }

  private void createMyThemeMenu(MenuGroup mg) {
    MenuGroup myTheme = new MenuGroup("나의 테마", Menu.ACCESS_GENERAL);
    myTheme.setMenuFilter(menuFilter);

    myTheme.add(new MenuItem("나의 테마 만들기", "/myTheme/add"));
    myTheme.add(new MenuItem("나의 테마 목록 보기", "/myTheme/list"));
    myTheme.add(new MenuItem("나의 테마 상세 보기", "/myTheme/detail"));

    mg.add(myTheme);
  }

  private void createLikedThemeMenu(MenuGroup mg) {
    MenuGroup likedTheme = new MenuGroup("좋아하는 테마", Menu.ACCESS_GENERAL);
    likedTheme.setMenuFilter(menuFilter);

    likedTheme.add(new MenuItem("테마 좋아요 누르기", "/likedTheme/add"));
    likedTheme.add(new MenuItem("테마 좋아요 목록 보기", "/likedTheme/list"));
    likedTheme.add(new MenuItem("테마 좋아요 취소하기", "/likedTheme/delete"));

    mg.add(likedTheme);
  }

  private void createLikedUserMenu(MenuGroup mg) {
    MenuGroup likedUser = new MenuGroup("좋아하는 유저", Menu.ACCESS_GENERAL);
    likedUser.setMenuFilter(menuFilter);

    likedUser.add(new MenuItem("유저 좋아요 누르기", "/likedUser/add"));
    likedUser.add(new MenuItem("유저 좋아요 목록 보기", "/likedUser/list"));
    likedUser.add(new MenuItem("유저 좋아요 취소하기", "/likedUser/delete"));

    mg.add(likedUser);
  }

  private void createSearchMenu(MenuGroup mg) {
    MenuGroup search = new MenuGroup("검색");
    search.setMenuFilter(menuFilter);

    search.add(new MenuItem("테마 검색하기", "/search/theme"));
    search.add(new MenuItem("유저 검색하기", "/search/user"));
    search.add(new MenuItem("해시 태그 검색하기", "/search/hashTag"));

    mg.add(search);
  }

  private void createRankMenu(MenuGroup mg) {
    MenuGroup rank = new MenuGroup("순위");
    rank.setMenuFilter(menuFilter);

    rank.add(new MenuItem("테마 순위 보기", "/rank/theme"));  // 전체 테마 검색 기준 조회 수 증가
    rank.add(new MenuItem("유저 순위 보기", "/rank/user"));   // 유저 검색 기준 조회 수 증가

    mg.add(rank);
  }

  private void createReportMenu(MenuGroup mg) {
    MenuGroup report = new MenuGroup("신고", Menu.ACCESS_GENERAL);

    report.setMenuFilter(menuFilter);

    report.add(new MenuItem("테마 신고하기", "/report/theme"));
    report.add(new MenuItem("유저 신고하기", "/report/user"));
    report.add(new MenuItem("신고 목록 보기", "/report/list"));

    report.add(new MenuItem("테마 신고 처리하기", Menu.ACCESS_ADMIN, "/admin/reportThemeProcess")); //
    report.add(new MenuItem("유저 신고 처리하기", Menu.ACCESS_ADMIN, "admin/reportUserProcess"));   //

    mg.add(report);
  }

  private Menu createAdminUserMenu(MenuGroup mg) {
    MenuGroup adminUser = new MenuGroup("회원 관리", Menu.ACCESS_ADMIN);
    adminUser.setMenuFilter(menuFilter);
    adminUser.add(new MenuItem("회원 목록 보기", "/admin/userList"));
    adminUser.add(new MenuItem("회원 상세 보기", "/admin/userDetail"));

    mg.add(adminUser);

    return adminUser;
  }

  public void service() throws Exception{
    createMenu().execute();

    Prompt.close();

    // DBMS와 연결을 끊음
    //    con.close();

    // SqlSession 객체의 자원 해제
    sqlSession.close();
  }

  public static void main(String[] args) throws Exception{
    ClientApp app = new ClientApp(); 
    app.addUserContextListener(new LoginListener());
    app.service();

    Prompt.close();
  }

}
