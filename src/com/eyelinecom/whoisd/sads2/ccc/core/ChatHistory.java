package com.eyelinecom.whoisd.sads2.ccc.core;

import com.eyelinecom.whoisd.sads2.ccc.model.Operator;
import com.eyelinecom.whoisd.sads2.ccc.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 28.10.16
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class ChatHistory {

  private final List<ChatHistoryEntry> entries = new ArrayList<>();

  public List<ChatHistoryEntry> entries(int count) {
    if (count >= entries.size()) count = entries.size();
    ArrayList<ChatHistoryEntry> res = new ArrayList<>();
    for (int i = entries.size() - count; i < entries.size(); i++) res.add(entries.get(i));
    return res;
  }

  public void add(String message, User user) {
    entries.add(new ChatHistoryEntry(message, user));
  }

  public void add(String message, Operator operator) {
    entries.add(new ChatHistoryEntry(message, operator));
  }

  public int size() {
    return entries.size();
  }
}
