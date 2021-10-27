
package com.welcomeToJeju.moj.dao;

import java.util.List;
import com.welcomeToJeju.moj.domain.Place;

public interface PlaceDao {
  void insert(Place place) throws Exception;
  //	void commentInsert(HashMap<String,Object> param) throws Exception;
  void insertComment(int placeNo, int userNo, String comment) throws Exception;
  //	void photoInsert(HashMap<String,Object> param) throws Exception;
  void insertPhoto(int placeNo, int userNo, String filepath) throws Exception;
  List<Place> findByThemeNo(int themeNo) throws Exception;
  void delete(int placeNo) throws Exception;
  void deleteComment(int placeNo) throws Exception;
  void deletePhoto(int placeNo) throws Exception;
}