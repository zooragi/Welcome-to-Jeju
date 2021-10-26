package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Place {

  private int no;
  private User owner;
  private Theme theme;
  private String storeName;
  private String storeAddress;

  private List<String> photos = new ArrayList<>();
  private List<String> comments = new ArrayList<>();

  private String xCoord;
  private String yCoord;

  @Override
  public String toString() {
    return "Place [no=" + no + ", owner=" + owner + ", theme=" + theme + ", storeName=" + storeName
        + ", storeAddress=" + storeAddress + ", photos=" + photos + ", comments=" + comments
        + ", xCoord=" + xCoord + ", yCoord=" + yCoord + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Theme getTheme() {
    return theme;
  }

  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public String getStoreAddress() {
    return storeAddress;
  }

  public void setStoreAddress(String storeAddress) {
    this.storeAddress = storeAddress;
  }

  public List<String> getPhotos() {
    return photos;
  }

  public void setPhotos(List<String> photos) {
    this.photos = photos;
  }

  public List<String> getComments() {
    return comments;
  }

  public void setComments(List<String> comments) {
    this.comments = comments;
  }

  public String getxCoord() {
    return xCoord;
  }

  public void setxCoord(String xCoord) {
    this.xCoord = xCoord;
  }

  public String getyCoord() {
    return yCoord;
  }

  public void setyCoord(String yCoord) {
    this.yCoord = yCoord;
  }


}