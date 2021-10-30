package com.welcomeToJeju.moj.handler.theme;

import java.util.Collection;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;

public class AllThemeListHandler implements Command {

  ThemeDao themeDao;

  public AllThemeListHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[전체 테마 보기]");

    Collection<Theme> themeList = themeDao.findAll();

    if (themeList.size() == 0) {
      System.out.println("테마 없음!");
      return;
    }

    int no = 1;
    for (Theme theme : themeList) {
      if (theme.getIsPublic() == 0) {
        continue;
      }

      System.out.printf("<%d>\n", no++);
      System.out.printf("제목 > %s\n", theme.getTitle());
      System.out.printf("닉네임 > %s\n", theme.getOwner().getNickName());
      System.out.printf("카테고리 > %s\n", theme.getCategory().getName());
      System.out.printf("해시태그 > %s\n", theme.getHashtags().toString());
    }
  }


}
