package com.welcomeToJeJu.moj.handler;

public interface Command {
  void execute(CommandRequest request) throws Exception;
}
