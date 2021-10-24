package com.welcomeToJeju.moj.handler;

import java.util.List;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;

public class AllThemeListHandler extends AbstractSearchHandler{

  public AllThemeListHandler(List<User> userList) {
    super(userList);
  }
  public void execute(CommandRequest request) {
    System.out.println("[전체 테마 목록보기]");
    int i = 1;
    for(User user : userList) {
      for(Theme theme : user.getThemeList()) {
        if(!theme.isPublic()) continue;
        System.out.printf("<%d>\n",i++);
        System.out.println("테마 이름 > " + theme.getTitle());
        System.out.println("해시 태그 > " + theme.getHashtags().toString());
      }
    }
  }

}
