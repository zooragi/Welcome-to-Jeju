package com.welcomeToJeJu.moj;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import com.welcomeToJeJu.moj.table.ThemeTable;
import com.welcomeToJeJu.moj.table.UserTable;
import com.welcomeToJeJu.server.DataProcessor;
import com.welcomeToJeJu.server.RequestProcessor;

public class ServerApp {

  public static void main(String[] args) throws Exception {

    System.out.println("서버 실행중");
    @SuppressWarnings("resource")
    ServerSocket serverSocket = new ServerSocket(8888);

    // RequestProcessor 가 사용할 DataProcessor 맵 준비
    HashMap<String,DataProcessor> dataProcessorMap = new HashMap<String,DataProcessor>();

    // => 데이터 처리 담당자를 등록한다.
    dataProcessorMap.put("user.", new UserTable());
    dataProcessorMap.put("theme.", new ThemeTable());
    //    dataProcessorMap.put("project.", new ProjectTable());

    while(true) {
      Socket socket = serverSocket.accept();
      System.out.println("클라이언트와 접속");


      RequestProcessor requestProcessor = new RequestProcessor(socket, dataProcessorMap);
      requestProcessor.start();



    }


    //    System.out.println("서버 종료");
    //    serverSocket.close();
  }
}












