package com.welcomeToJeju.moj.handler.rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.ThemePrompt;

public class ThemeRankHandler implements Command{
	
	ThemePrompt themePrompt;
	
  public ThemeRankHandler(ThemePrompt themePrompt) {
  	this.themePrompt = themePrompt;
  }

  public void execute(CommandRequest request) throws Exception {
    int i = 1;
    System.out.println("[실시간 테마 순위 보기]");
    for(Theme theme : themePrompt.rank()) {
      System.out.printf("%d위. %s (조회수 : %d) > \n",i,theme.getTitle(),theme.getViewCount());
      i++;
    }
  }

}
