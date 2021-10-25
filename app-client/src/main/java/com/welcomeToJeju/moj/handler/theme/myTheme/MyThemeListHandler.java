package com.welcomeToJeju.moj.handler.theme.myTheme;

import java.util.ArrayList;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;

public class MyThemeListHandler implements Command {

  ThemeDao themeDao;

  public MyThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[나의 테마 목록 보기]");

    ArrayList<Theme> themeList = (ArrayList<Theme>) themeDao.findAll();

    if (themeList.size() == 0) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    int i = 1 ;
    for (Theme theme : themeList) {
      if(AuthLoginHandler.getLoginUser().getNo() == theme.getThemeOwner().getNo()) {
        System.out.printf("<%d>\n", i++);
        System.out.printf("%s 테마\n", theme.isPublic() ? "공개" : "비공개");
        System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());
        System.out.printf("해시 태그 > %s\n", theme.getHashtags().toString());
        if (theme.isPublic()) {
          System.out.printf("조회 수 > %d\n", theme.getViewCount());
        }
        System.out.println();
      }
    }
  }


}
