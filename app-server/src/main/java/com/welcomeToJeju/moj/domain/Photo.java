package com.welcomeToJeju.moj.domain;

public class Photo {

  int no;
  String filePath;

  int userNo;
  String placeId;

  @Override
  public String toString() {
    return "Photo [no=" + no + ", filePath=" + filePath + ", userNo=" + userNo + ", placeId="
        + placeId + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public int getUserNo() {
    return userNo;
  }

  public void setUserNo(int userNo) {
    this.userNo = userNo;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }


}
