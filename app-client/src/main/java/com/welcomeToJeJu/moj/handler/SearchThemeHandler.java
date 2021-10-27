package com.welcomeToJeju.moj.handler;

import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class SearchThemeHandler implements Command {

  ThemeDao themeDao;
  SqlSession sqlSession;

  public SearchThemeHandler(ThemeDao themeDao,SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {

    System.out.println("[테마 검색하기]");

    while (true) {
      String input = Prompt.inputString("테마 이름(취소 : 엔터) > ");
      if(input.equals("")) {
        System.out.println("테마 검색 취소!");
        return;
      }
      Theme theme = themeDao.findByTitle(input);

      if(theme == null) {
        System.out.println("검색된 테마가 없음");
        continue;
      }

      int currentCount = theme.getViewCount();
      theme.setViewCount(currentCount+1);
      PlaceHandlerHelper.printPlaceInfo(theme);

      themeDao.update(theme);
      sqlSession.commit();
      return;
    }
  }
}