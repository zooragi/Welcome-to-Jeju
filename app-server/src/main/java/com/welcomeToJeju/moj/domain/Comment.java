package com.welcomeToJeju.moj.domain;

public class Comment {

  int no;
  String comment;

  int userNo;
  String placeId;

  @Override
  public String toString() {
    return "Comment [no=" + no + ", comment=" + comment + ", userNo=" + userNo + ", placeId="
        + placeId + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
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
