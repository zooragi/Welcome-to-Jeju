package com.welcomeToJeju.moj.handler.likedTheme;


import java.util.ArrayList;
import java.util.List;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.UserPrompt;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;

public class LikedThemeListHandler implements Command{

  ThemeDao themeDao;
  UserPrompt userPrompt;

  public LikedThemeListHandler(ThemeDao themeDao,UserPrompt userPrompt) {
    this.themeDao = themeDao;
    this.userPrompt = userPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[좋아요 목록보기]");
    
    ArrayList<Theme> list = (ArrayList<Theme>) themeDao.likedThemeList(AuthLoginHandler.getLoginUser().getNo());
    printList(list);
    
  }
	private void printList(List<Theme> themeList) throws Exception {
		for (Theme theme : themeList) {
			System.out.printf("[%s] 테마명 > %s\n", userPrompt.getByUserNo(theme.getThemeOwnerNo()) , theme.getTitle());
		}
	}
}