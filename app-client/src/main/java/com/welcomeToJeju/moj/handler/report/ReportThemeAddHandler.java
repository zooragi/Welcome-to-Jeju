package com.welcomeToJeju.moj.handler.report;

import java.sql.Date;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.ThemeDao;
import com.welcomeToJeju.moj.domain.ReportStatus;
import com.welcomeToJeju.moj.domain.ReportTheme;
import com.welcomeToJeju.moj.domain.Theme;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class ReportThemeAddHandler implements Command {

  ReportDao reportDao;
  ThemeDao themeDao;
  SqlSession sqlSession;

  public ReportThemeAddHandler(ReportDao reportDao, ThemeDao themeDao, SqlSession sqlSession) {
    this.reportDao = reportDao;
    this.themeDao = themeDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[테마 신고하기]");

    ReportTheme reportTheme = new ReportTheme();

    String themeTitle = Prompt.inputString("테마 이름(취소 : 엔터) > ");

    if (themeTitle.equals("") || themeTitle.length() == 0) {
      System.out.println("테마 신고하기 취소!");
      return;
    }

    Theme theme = themeDao.findByTitle(themeTitle);

    if(theme == null) {
      System.out.println("테마 없음!");
      return;
    }

    if(theme.getOwner().getNo() == AuthLoginHandler.getLoginUser().getNo()) {
      System.out.println("자기의 테마를 신고할 수 없음!");
      return;
    }

    reportTheme.setReportedTheme(theme);

    ReportStatus reportStatus = new ReportStatus();
    reportStatus.setNo(100);

    reportTheme.setWriter(AuthLoginHandler.getLoginUser());

    String content = Prompt.inputString("신고 내용 > ");
    reportTheme.setContent(content);

    reportTheme.setRegisteredDate(new Date(System.currentTimeMillis()));

    reportTheme.setStatus(reportStatus);

    reportDao.insertReportTheme(reportTheme);

    int count = theme.getReportedCount() + 1;
    HashMap<String,Object> params = new HashMap<>();
    params.put("themeNo", theme.getNo());
    params.put("reportedCnt", count);
    themeDao.updateReportedCount(params);
    sqlSession.commit();

    System.out.println("테마 신고하기 성공!");
  }


}
