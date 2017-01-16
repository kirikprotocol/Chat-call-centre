package com.eyelinecom.whoisd.sads2.ccc.api;

import com.eyelinecom.whoisd.sads2.ccc.core.ChatHistory;
import com.eyelinecom.whoisd.sads2.ccc.model.Operator;
import com.eyelinecom.whoisd.sads2.ccc.model.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 17.11.16
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public interface OperatorApi {

  /**
   * Get or create operator object for given subscriber number.
   * If subscriber is not in operator list -
   */
  Operator getOperator(String subscriber);

  /**
   * Send message from operator to user
   */
  void sendMessageToUser(String message, Operator operator, User user);

  void pushForHistory(Operator operator);

  void pushAfterVerification(Operator operator, String backUrl);

  /**
   * Get chat history for user
   */
  ChatHistory historyFor(User user);

  void endDialog(Operator operator);

  void removeOperator(Operator operator);

  List<String> getPendingMessages(User selectedUser);

  boolean isActiveOperator(String subscriber);

  void setOperatorName(Operator operator, String name);

}
