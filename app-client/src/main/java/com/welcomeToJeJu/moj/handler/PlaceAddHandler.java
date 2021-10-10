package com.welcomeToJeJu.moj.handler;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.welcomeToJeJu.moj.dao.ThemeDao;
import com.welcomeToJeJu.moj.domain.Place;
import com.welcomeToJeJu.moj.domain.Theme;
import com.welcomeToJeJu.util.KakaoMapApi;
import com.welcomeToJeJu.util.KakaoVo;
import com.welcomeToJeJu.util.Prompt;

public class PlaceAddHandler implements Command {

  ThemeDao themeDao;

  public PlaceAddHandler(ThemeDao themeDao) {
    this.themeDao = themeDao;
  }


  @Override
  public void execute(CommandRequest request) throws Exception {
    Gson gson = new Gson();
    KakaoMapApi kakao = new KakaoMapApi();

    System.out.println("[장소 등록하기]");

    String themeName = (String) request.getAttribute("themeTitle");

    Theme theme = themeDao.findByTitle(themeName);

    if(theme == null) {
      System.out.println("등록된 테마 없음!");
      return;
    }

    Place place = new Place();
    KakaoVo selectedPlace = new KakaoVo();

    ArrayList<KakaoVo> filterPlace = new ArrayList<>();
    while (true) {
      Object[] SearchedPlaces = kakao.searchPlace(Prompt.inputString("장소 이름 > "));

      for(int i = 0 ; i < SearchedPlaces.length ; i++) {
        selectedPlace = gson.fromJson(gson.toJson(SearchedPlaces[i]),KakaoVo.class);

        if(selectedPlace.getAddress_name().contains("제주")) {
          filterPlace.add(selectedPlace);
        }
      }

      int i = 1;
      for(KakaoVo kakaoVo : filterPlace) {
        System.out.printf("%d. %s, %s, %s, %s\n",i++,kakaoVo.getPlace_name(),
            kakaoVo.getAddress_name(),
            kakaoVo.getX(),
            kakaoVo.getY()
            );
      }

      int num = Prompt.inputInt("번호 > ");
      if ( num == 0 ) continue;
      if(num > filterPlace.size()) {
        System.out.println("잘못된 번호!");
        continue;
      } 

      selectedPlace = gson.fromJson(gson.toJson(filterPlace.get(num-1)),KakaoVo.class);
      place.setStoreAddress(selectedPlace.getAddress_name());
      place.setStoreName(selectedPlace.getPlace_name());
      place.setxCoord(selectedPlace.getX());
      place.setyCoord(selectedPlace.getY());
      place.setTheme(theme/*.getTitle()*/);

      break;
    }

    place.getComments().add(Prompt.inputString("장소 후기 > "));

    themeDao.placeInsert(place);
  }
}