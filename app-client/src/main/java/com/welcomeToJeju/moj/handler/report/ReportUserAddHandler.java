package com.welcomeToJeju.moj.handler.report;

import java.sql.Date;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.welcomeToJeju.moj.dao.ReportDao;
import com.welcomeToJeju.moj.dao.UserDao;
import com.welcomeToJeju.moj.domain.ReportStatus;
import com.welcomeToJeju.moj.domain.ReportUser;
import com.welcomeToJeju.moj.domain.User;
import com.welcomeToJeju.moj.handler.Command;
import com.welcomeToJeju.moj.handler.CommandRequest;
import com.welcomeToJeju.moj.handler.user.AuthLoginHandler;
import com.welcomeToJeju.util.Prompt;

public class ReportUserAddHandler implements Command {

  ReportDao reportDao;
  UserDao userDao;
  SqlSession sqlSession;

  public ReportUserAddHandler(ReportDao reportDao, UserDao userDao, SqlSession sqlSession) {
    this.reportDao = reportDao;
    this.userDao = userDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유저 신고하기]");

    ReportUser reportUser = new ReportUser();

    String nickName = Prompt.inputString("닉네임(취소 : 엔터) > ");

    if (nickName.equals("") || nickName.length() == 0) {
      System.out.println("유저 신고하기 취소!");
      return;
    }

    User user = userDao.findByNickName(nickName);

    if (user == null) {
      System.out.println("유저 없음!");
      return;
    }

    // 번호로 비교? 닉네임으로 비교??
    if(nickName.equals(AuthLoginHandler.getLoginUser().getNickName())) {
      System.out.println("자기를 신고할 수 없음!");
      return;
    }

    reportUser.setReportedUser(user);

    ReportStatus reportStatus = new ReportStatus();
    reportStatus.setNo(100);

    reportUser.setWriter(AuthLoginHandler.getLoginUser());

    String content = Prompt.inputString("신고 내용 > ");
    reportUser.setContent(content);

    reportUser.setStatus(reportStatus);

    reportDao.insertReportUser(reportUser);

    reportUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    int count = user.getReportedCount() + 1;
    HashMap<String,Object> params = new HashMap<>();
    params.put("userNo", user.getNo());
    params.put("reportedCnt", count);
    userDao.updateReportedCount(params);
    sqlSession.commit();

    System.out.println("유저 신고 완료!");
  }


}
