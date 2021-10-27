package com.welcomeToJeju.moj.handler;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.util.Prompt;

public class MyThemeUpdateHandler implements Command {

  ThemeDao themeDao;
  SqlSession sqlSession;

  public MyThemeUpdateHandler(ThemeDao themeDao, SqlSession sqlSession) {
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 수정하기]");

    Theme theme = (Theme) request.getAttribute("theme");

    if (theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }
    int categoryNum = theme.getCategory().getNo();

    String newTitle = Prompt.inputString("테마 제목 > ");

    List<String> hashtagList = new ArrayList<>();

    while (true) {
      String input = Prompt.inputString("해시 태그(완료: 엔터) > ");
      if (input.length() == 0)
        break;
      hashtagList.add(input);
    }

    boolean isPublic = false;

    String publicOption = Prompt.inputString("공개 설정(Y/n) > ");
    if (publicOption.equalsIgnoreCase("y") || publicOption.equals("")) {
      isPublic = true;
    }

    String input = Prompt.inputString("수정하기(y/N) > ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("테마 수정 취소!");
      return;
    }

    theme.setTitle(newTitle);
    theme.setHashtags(hashtagList);
    theme.setCategory(new ThemeHandlerHelper(themeDao).promptCategory(categoryNum));
    theme.setPublic(isPublic);

    themeDao.deleteHashtags(theme.getNo());
    for(String h : theme.getHashtags()) {
      themeDao.insertHashtags(theme.getNo(),h);
    }
    themeDao.update(theme);
    sqlSession.commit();


    System.out.println();
    System.out.println("테마 수정 완료!");
  }

}
