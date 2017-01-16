package com.eyelinecom.whoisd.sads2.ccc.core;

import com.eyelinecom.whoisd.sads2.ccc.model.Operator;
import com.eyelinecom.whoisd.sads2.ccc.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 28.10.16
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class ChatHistoryEntry {
  private final String name;
  private final String text;

  public ChatHistoryEntry(String message, User user) {
    name = user.userName();
    text = message;
  }

  public ChatHistoryEntry(String message, Operator operator) {
    name = "Оператор";
    text = message;
  }

  public String name() {
    return name;
  }

  public String text() {
    return text;
  }
}
