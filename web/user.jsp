<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.UserApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Operator" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.User" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.utils.Common" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.utils.PageAttributes" %>

<%!
  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("com.eyelinecom.whoisd.sads2.ccc.user_jsp");
%>

<%

  log.debug("req: " + Common.toString(request));

  UserApi api = CccManager.userApi(request);
  PageAttributes pageAttributes = PageAttributes.create().keepSession(true);

  String subscriber = request.getParameter("subscriber");
  boolean initial = request.getParameter("initial") != null;
  String message = request.getParameter("message");

  User user = api.getUser(subscriber);
  user.setProtocol(request.getParameter("protocol"));
  if (initial) {
    // this is first redirect from SADS when going to "User" state
    String badCommand = request.getParameter("bad_command"); // if it's initial redirect we have message in "bad_command"
    log.debug("Initial request, first message: " + badCommand);
    if (user.userName() == null) {
      log.debug("user name is unknown, requesting name");
%>
<page>
  <attributes>
    <attribute name="ccc-user" value="<%=subscriber%>"/>
  </attributes>
  <div>Представьтесь, пожалуйста</div>
  <input navigationId="submit" name="userName"/>
  <input navigationId="submit" name="message" type="hidden" value="<%=badCommand%>" />
  <navigation id="submit">
    <link accesskey="1" pageId="user.jsp">Ok</link>
  </navigation>
</page>
<%
      return;
    }
    log.debug("User name is known: \"" + user.userName() + "\", processding");
    if (message == null) message = badCommand;
  }

  String userName = request.getParameter("userName");
  // This is right after introduce page - set user name
  if (userName != null) api.setUserName(user, userName);

  boolean isPush = request.getParameter("ccc_push") != null;

  Operator operator = api.getAssignedOperator(user);

  String content;
  if (isPush) {
    String operatorMessage = request.getParameter("operatorMessage"); // this is in case of push with operator message
    if (operatorMessage != null) {
      log.debug("Showing to user " + user + " message from operator: " + operatorMessage);
      content = "<b>Оператор " + operator.getName() + ": </b>" + operatorMessage;
    } else {
      log.warn("Unkown type of push");
      content = null;
    }
  } else {

    if (operator != null) {
      if (message != null) {
        api.sendMessageToOperator(message, user);
        content = "Сообщение отправлено оператору";
      } else {
        log.warn("Unkown state, not push and not message");
        content = null;
      }
    } else {
      if (message != null) {
        api.sendMessageToOperator(message, user);
        content = "Нет свобоных операторов. Пожалуйста, подождите...";
      } else {
        log.warn("Unkown state, not push and not message");
        content = null;
      }
    }
  }
  log.debug("Show message for user: " + content);
%>
  <page <%=pageAttributes.print()%>>
    <attributes>
      <attribute name="ccc-user" value="<%=subscriber%>"/>
    </attributes>
  <div>
    <% if (content != null) { %><%=content%><% } %>
    <input navigationId="submit" name="message"/>
  </div>
  <navigation id="submit">
    <link accesskey="1" pageId="user.jsp">Ok</link>
  </navigation>
</page>
