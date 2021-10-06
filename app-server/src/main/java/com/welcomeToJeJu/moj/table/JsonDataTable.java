package com.welcomeToJeJu.moj.table;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

// 역할
// 데이터를 처리하는 클래스가 공통으로 가져야할 필드나 메서드를 정의한다.

public abstract class JsonDataTable<T> {

  protected List<T> list = new ArrayList<>();
  protected Class<T> elementType;
  private String filename;

  public JsonDataTable(String filename, Class<T> elementType) {
    this.filename = filename;
    this.elementType = elementType;
    loadObjects();
  }

  public void save() {
    // 데이터를 JSON 형식으로 저장한다.
    saveObjects();
  }

  private <E> void loadObjects() {

    try (BufferedReader in = new BufferedReader(
        new FileReader(filename))){


      StringBuilder strBuilder = new StringBuilder();
      String str;
      while((str = in.readLine()) != null) { // 파일 전체를 읽는다.
        strBuilder.append(str);
      }

      //StringBuilder로 읽어 온 JSON 문자열을 객체로 바꾼다.

      Type type = TypeToken.getParameterized(Collection.class, elementType).getType();
      Collection<T> collection =  new Gson().fromJson(strBuilder.toString(), type);
      list.addAll(collection);


      System.out.printf("%s 데이터 로딩 완료!\n", filename);
    } catch (Exception e) {
      System.out.printf("%s 데이터 로딩 오류!\n", filename);
    }

  }

  // 객체를 JSON 형식으로 저장한다.
  private void saveObjects() {
    try (PrintWriter out = new PrintWriter(
        new BufferedWriter(
            new FileWriter(filename)))){

      out.println(new Gson().toJson(list));
      System.out.printf("%s 데이터 출력 완료!\n",filename);

    } catch (Exception e) {
      System.out.printf("%s 데이터 출력 오류!\n",filename);
      e.printStackTrace();
    }
  }



}