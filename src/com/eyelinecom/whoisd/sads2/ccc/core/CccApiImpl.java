package com.eyelinecom.whoisd.sads2.ccc.core;

import com.eyelinecom.whoisd.sads2.ccc.api.AdminApi;
import com.eyelinecom.whoisd.sads2.ccc.api.OperatorApi;
import com.eyelinecom.whoisd.sads2.ccc.api.UserApi;
import com.eyelinecom.whoisd.sads2.ccc.model.Admin;
import com.eyelinecom.whoisd.sads2.ccc.model.Operator;
import com.eyelinecom.whoisd.sads2.ccc.model.Parameter;
import com.eyelinecom.whoisd.sads2.ccc.model.ParameterId;
import com.eyelinecom.whoisd.sads2.ccc.model.Service;
import com.eyelinecom.whoisd.sads2.ccc.model.User;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 17.11.16
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class CccApiImpl implements OperatorApi, UserApi, AdminApi {

  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CccApiImpl.class);

  private final Service service;
  private DbManager dbManager;
  private String mobilizerUrl;
  private final List<Operator> operators = new ArrayList<>();
  private final Map<String, Operator> operatorMap = new HashMap<>();
  private final Map<User, Operator> assignedMap = new HashMap<>();
  private final Map<String, User> userMap = new HashMap<>();

  private final Map<User, ChatHistory> historyMap = new HashMap<>();
  private final LinkedList<User> pendingUsers = new LinkedList<>();
  private final Map<User, List<String>> pendingMessages = new HashMap<>();
  private final Map<String, String> userNameCache = new HashMap<>();
  private Admin admin;
  private Map<String, String> operatorNameCache = new HashMap<>();

  public CccApiImpl(String service, DbManager dbManager, String mobilizerUrl) {
    this.dbManager = dbManager;
    this.mobilizerUrl = mobilizerUrl;
    this.service = dbManager.getService(service);
  }

  @Override
  public User getUser(String subscriber) {
    User user = userMap.get(subscriber);
    if (user == null) {
      user = createUser(subscriber);
      String userName = userNameCache.get(subscriber);
      if (userName != null) setUserName(user, userName);
    }
    return user;
  }

  @Override
  public void setUserName(User user, String userName) {
    log.debug("Setting user name \"" + userName + "\" for user " + user);
    userNameCache.put(user.subscriber(), userName);
    user.userName(userName);

    // assign operator after we know
    Operator operator = findFreeOperator(user);
    if (operator == null) pendingUsers.add(user);
  }

  private User createUser(String subscriber) {
    User user = new User(subscriber);
    log.debug("Created user " + user);
    userMap.put(subscriber, user);
    return user;
  }

  @Override
  public Operator getOperator(String subscriber) {

    boolean isAdmin = isAdmin(subscriber);
    if (!dbManager.isOperator(service, subscriber) && !isAdmin) return null;
    Operator operator = operatorMap.get(subscriber);
    if (operator == null) {
      operator = new Operator(subscriber, isAdmin);
      String name = operatorNameCache.get(subscriber);
      if (name != null) operator.setName(name);
      log.debug("created operator: " + operator);
      operatorMap.put(subscriber, operator);
      operators.add(operator);
      // TODO: check all

      while (!pendingUsers.isEmpty()) {
        User user = pendingUsers.peekLast();
        Operator op = findFreeOperator(user);
        if (op == null) break;
        pendingUsers.removeLast();
      }
    }
    return operator;
  }

  @Override
  public boolean isActiveOperator(String subscriber) {
    Operator operator = operatorMap.get(subscriber);
    return operator != null;
  }

  @Override
  public void setOperatorName(Operator operator, String name) {
    operatorNameCache.put(operator.subscriber(), name);
    operator.setName(name);
  }

  @Override
  public Operator getAssignedOperator(User user) {
    return assignedMap.get(user);
  }

  @Override
  public void sendMessageToUser(String message, Operator operator, User user) {
    log.debug("Sending message from " + operator + " to " + user + ": " + message);
    historyFor(user).add(message, operator);
    push(user.subscriber(), user.getProtocol(), "ccc_push=user", "operatorMessage=" + message);
  }

  @Override
  public void sendMessageToOperator(String message, User user) {
    Operator operator = assignedMap.get(user);
    if (operator == null) {
      log.debug("Putting message from " + user + " to pending queue: " + message);
      List<String> messages = pendingMessages.get(user);
      if (messages == null) pendingMessages.put(user, messages = new ArrayList<>());
      messages.add(message);
    } else {
      log.debug("Sending message from " + user + " to " + operator + ": " + message);
      historyFor(user).add(message, user);
      if (operator.getSelectedUser() == user) {
        // make push for operator
        push(operator.subscriber(), "telegram", "ccc_push=operator", "userMessage=" + message, "userId=" + user.id());
      } else {
        // change counter of messages
        // TODO:
        push(operator.subscriber(), "telegram", "ccc_push=operator", "userMessage=" + message, "userId=" + user.id());
      }
    }
  }

  @Override
  public void pushForHistory(Operator operator) {
    push(operator.subscriber(), "telegram", "ccc_push=operator", "history=3");
  }

  @Override
  public void pushAfterVerification(Operator operator, String backUrl) {
    push(operator.subscriber(), "telegram", "ccc_push=operator", "backUrl=" + backUrl);
  }

  @Override
  public ChatHistory historyFor(User user) {
    ChatHistory history = historyMap.get(user);
    if (history == null) historyMap.put(user, history = new ChatHistory());
    return history;
  }

  @Override
  public void endDialog(Operator operator) {
    User user = operator.getSelectedUser();
    log.debug("ending dialog, removing user " + user);
    operator.remove(user);
    assignedMap.remove(user);
    userMap.remove(user.subscriber());
    // TODO: send push message to user about dialog ending

  }

  @Override
  public void removeOperator(Operator operator) {
    log.debug("removing operator " + operator);
    // TODO: redirect all users to other operator or add to pending
    operatorMap.remove(operator.subscriber());
    operators.remove(operator);
    List<User> users = operator.getUsers();
    for (User user : users) {
      assignedMap.remove(user);
      operator = findFreeOperator(user);
      if (operator != null) {
        // TODO: if there are several users for one operator - join
        push(operator.subscriber(), "telegram", "ccc_push=operator", "usersAdded=" + user.id());
      } else {
        pendingUsers.add(user);
      }
    }

  }

  @Override
  public List<String> getPendingMessages(User user) {
    List<String> messages = pendingMessages.remove(user);
    if (messages == null || messages.isEmpty()) return null;
    log.debug("Retieved " + messages.size() + " pending message from user " + user);
    for (String message : messages) historyFor(user).add(message, user);

    return messages;
  }

  private Operator findFreeOperator(User user) {
    // TODO: make here some more complex stratagy?
    Operator assignedOperator = assignedMap.get(user);
    if (assignedOperator != null) {
      log.warn("User " + user + " already has operator assigned: " + assignedOperator);
      return assignedOperator;
    }
    log.debug("Searching for operator for user " + user + " (" + operators.size() + " operators)");
    int maxActive = getParameter(ParameterId.activeChatCount);
    for (Operator operator : operators) {
      List<User> users = operator.getUsers();
      if (users.size() < maxActive) {
        log.debug("Assigning user " + user + " to operator " + operator);
        operator.add(user);
        assignedMap.put(user, operator);
        // TODO: rename user if there are duplicates
        renameUserIfDuplicate(user, operator);
        return operator;
      }
    }
    log.debug("Not found free operator for " + user);
    return null;
  }

  private void renameUserIfDuplicate(User user, Operator operator) {
    for (User u : operator.getUsers()) {

    }
  }

  private void push(String subscriber, String protocol, String... params) {
    try {
      // TODO: where to take service from?
      //log.debug();
      log.debug("Making push, subscriber: " + subscriber + ", params: " + Arrays.toString(params));
      StringBuilder sb = new StringBuilder();
      sb.append(mobilizerUrl).append("push?service=").append(service.name).append("&subscriber=").append(subscriber).append("&protocol=").append(protocol).append("&scenario=default");
      for(String param : params) sb.append("&").append(urlEncode(param));
      String url = sb.toString();
      final HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
      con.setRequestMethod("GET");
      con.getInputStream().close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String urlEncode(String param) {
    int ind = param.indexOf('=');
    try {
      return param.substring(0, ind + 1) + URLEncoder.encode(param.substring(ind + 1), "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Admin updateAdmin(String serviceOwnerList) {
    if(admin == null || !admin.serviceOwnerList.equals(serviceOwnerList)) {
      log.debug("");
      dbManager.updateAdminMsisdn(service, serviceOwnerList);
      admin = new Admin(serviceOwnerList);
    }
    return admin;
  }

  @Override
  public boolean isAdmin(String subscriber) {
    if (admin == null) admin = new Admin(dbManager.getAdmin(service));
    return admin.is(subscriber);
  }

  @Override
  public List<String> getOperatorMsisdns() {
//  TODO: cache list
    return dbManager.getOperatorMsisdns(service);
  }

  @Override
  public List<Parameter> getParameters() {
    // TODO: default parameters?
    List<Parameter> dbParameters = dbManager.getParameters(service);
    HashMap<String, ParameterId> defaultParameters = new HashMap<>(ParameterId.map());
    for (Parameter parameter : dbParameters) defaultParameters.remove(parameter.name());
    for (Map.Entry<String, ParameterId> en : defaultParameters.entrySet()) {
      dbParameters.add(new Parameter(en.getValue()));
    }
    return dbParameters;
  }

  @Override
  public void addOperatorRecord(String msisdn) {
    try {
      dbManager.addOperator(service, msisdn);
    } catch (Throwable e) {
      log.error("", e);
    }
  }

  @Override
  public void removeOperatorRecord(String msisdn) {
    try {
      dbManager.removeOperator(service, msisdn);
    } catch (Throwable e) {
      log.error("", e);
    }
  }

  @Override
  public Parameter getParameter(String name) {
    Parameter parameter = dbManager.getParameter(service, name);
    if (parameter != null) return parameter;
    return new Parameter(ParameterId.get(name));
  }

  private <T> T getParameter(ParameterId<T> parameterId) {
    return parameterId.convert(getParameter(parameterId.key).value());
  }

  @Override
  public Parameter setParameter(String name, String value) {
    Parameter parameter = dbManager.setParameter(service, name, value);
    if (parameter != null) return parameter;
    return new Parameter(ParameterId.get(name));
  }
}
