package com.welcomeToJeju.moj.handler.place;

import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.Place;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.PlaceHandlerHelper;

public class PlaceListHandler implements Command {

	ThemeDao themeDao;

	public PlaceListHandler(ThemeDao themeDao) {
		this.themeDao = themeDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[장소 목록보기]");

		Theme theme = (Theme) request.getAttribute("theme");
		theme = themeDao.search(theme.getTitle());
		
		System.out.printf("[%s] 테마 제목 > %s\n", theme.getCategory(), theme.getTitle());

		PlaceHandlerHelper.printPlaceInfo(theme);

	}

}