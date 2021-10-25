package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Theme implements Comparable<Theme> {

  private int no;
  private String title;
  private User owner;
  private Category category;
  private boolean isPublic;
  private boolean isShare;

  private int viewCount;
  private int reportedCount;

  private List<Place> placeList = new ArrayList<>();
  private List<String> hashTags = new ArrayList<>();

  @Override
  public String toString() {
    return "Theme [no=" + no + ", title=" + title + ", owner=" + owner + ", isPublic=" + isPublic
        + ", isShare=" + isShare + ", viewCount=" + viewCount + ", reportedCount=" + reportedCount
        + ", placeList=" + placeList + ", hashTags=" + hashTags + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public boolean isPublic() {
    return isPublic;
  }

  public void setPublic(boolean isPublic) {
    this.isPublic = isPublic;
  }

  public boolean isShare() {
    return isShare;
  }

  public void setShare(boolean isShare) {
    this.isShare = isShare;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getReportedCount() {
    return reportedCount;
  }

  public void setReportedCount(int reportedCount) {
    this.reportedCount = reportedCount;
  }

  public List<Place> getPlaceList() {
    return placeList;
  }

  public void setPlaceList(List<Place> placeList) {
    this.placeList = placeList;
  }

  public List<String> getHashTags() {
    return hashTags;
  }

  public void setHashTags(List<String> hashTags) {
    this.hashTags = hashTags;
  }

  @Override
  public int compareTo(Theme theme) {
    return theme.viewCount - this.viewCount;
  }


}
