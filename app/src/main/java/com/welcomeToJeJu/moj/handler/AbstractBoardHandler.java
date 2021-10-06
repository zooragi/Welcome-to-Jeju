package com.welcomeToJeJu.moj.handler;

import java.util.ArrayList;
import java.util.List;
import com.welcomeToJeJu.moj.domain.Report;

public abstract class AbstractBoardHandler implements Command {

  List<Report> boardList = new ArrayList<>();

  public AbstractBoardHandler (List<Report> boardList) {
    this.boardList = boardList;
  }
}
