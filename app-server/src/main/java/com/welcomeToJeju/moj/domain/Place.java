package com.welcomeToJeju.moj.domain;

import java.util.ArrayList;
import java.util.List;

public class Place {

  private String id;
  private String place_name;
  private String address_name;

  private List<Photo> photos = new ArrayList<>();
  private List<Comment> comments = new ArrayList<>();
  private List<Theme> themes = new ArrayList<>();

  private String x;
  private String y;

  @Override
	public String toString() {
		return "Place [id=" + id + ", place_name=" + place_name + ", address_name=" + address_name + ", photos=" + photos
				+ ", comments=" + comments + ", themes=" + themes + ", x=" + x + ", y=" + y + "]";
	}

	public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  
  public String getPlace_name() {
		return place_name;
	}

	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public List<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Photo> photos) {
    this.photos = photos;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

}
