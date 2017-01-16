package com.eyelinecom.whoisd.sads2.ccc.api;

import com.eyelinecom.whoisd.sads2.ccc.model.Operator;
import com.eyelinecom.whoisd.sads2.ccc.model.User;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 17.11.16
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
public interface UserApi {

  User getUser(String subscriber);
  Operator getAssignedOperator(User user);
  void sendMessageToOperator(String message, User user);
  void setUserName(User user, String userName);
}
