<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager"%>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Operator"%>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.User"%>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.ChatHistoryEntry" %>
<%@ page import="java.util.List"%>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.utils.PageAttributes" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.OperatorApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.utils.Common" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>

<%!
  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("com.eyelinecom.whoisd.sads2.ccc.operator_jsp");
%>

<%

  log.debug("req: " + Common.toString(request));
  // TODO: check that this is really operator

  OperatorApi api = CccManager.operatorApi(request);

  AdminApi adminApi = CccManager.adminApi(request);
  String serviceOwner = request.getParameter("serviceOwner");
  if (serviceOwner != null) adminApi.updateAdmin(serviceOwner);

  // TODO: auth operator
  String subscriber = request.getParameter("subscriber");
  String operatorName = request.getParameter("operatorName");
  boolean endDialog = request.getParameter("endDialog") != null;
  boolean logout = request.getParameter("operatorLogout") != null;
  boolean afterVerification = request.getParameter("afterVerification") != null;
  String afterVerificationText = request.getParameter("afterVerificationText");
  String backUrl = request.getParameter("backUrl");

  String select = request.getParameter("select"); // operator clicked on user
  String message = request.getParameter("message"); // simple typing text
  String usersAddedString = request.getParameter("usersAdded");

  String historyString = request.getParameter("history"); // self push for history or "more" button
  int history = historyString != null ? Integer.parseInt(historyString) : -1;
  String userMessage = request.getParameter("userMessage"); // push user message
  String userId = request.getParameter("userId");

  String protocol = request.getParameter("protocol");
  if(protocol != null && !protocol.equals("telegram")) { %>
<page>
  <div>Поддерживается только клиент Telegram</div>
</page>
<%
    return;
  }

  PageAttributes pageAttributes = PageAttributes.create().keepSession(true);
  Operator operator = api.getOperator(subscriber);

  String successUrl = "ccc_base/operator.jsp?afterVerification=true";
  if (backUrl != null) successUrl = successUrl + "&backUrl=" + backUrl;
  successUrl = URLEncoder.encode(successUrl, "UTF-8");

  if (operator == null) {
    if (!subscriber.matches("\\d{10,16}")) {
      log.debug("Requesting verification for operator " + subscriber);
%>
<page>
  <attributes>
    <attribute name="msisdn-required" value="true"/>
  </attributes>
  <div>Необходимо подтверждение номера телефона</div>
  <navigation>
    <link pageId="http://plugins.miniapps.run/msisdn-verification?success_url=<%=successUrl%>">Авторизоваться по C2S</link>
    <link pageId="http://plugins.miniapps.run/msisdn-verification?success_url=<%=successUrl%>&amp;type=sms">Авторизоваться по СМС</link>
    <link pageId="http://plugins.miniapps.run/msisdn-verification?success_url=<%=successUrl%>&amp;type=ussd_dialog">Авторизоваться по USSD</link>
    <link pageId="operator.jsp">Продолжить</link>
  </navigation>
</page>
<%
      return;
    }
    log.debug("Not operator, redirecting to " + backUrl);
    response.sendRedirect(backUrl);
    return;
  }

  if (logout) {
    api.removeOperator(operator);
    log.debug("Operator logout, redirecting to " + backUrl);
    response.sendRedirect(backUrl);
    return;
  }

  if (afterVerification) {
    %>
<page version="2.0">
  <div/>
  <navigation/>
</page>
<%
    api.pushAfterVerification(operator, backUrl);
    return;
  }

  if (afterVerificationText != null) {
    if (operator.getName() == null) operatorName = afterVerificationText;
    else message = afterVerificationText;
  }

  if (operatorName != null) api.setOperatorName(operator, operatorName);

  if (operator.getName() == null) {
    %>
  <page <%=pageAttributes.print()%>>
    <attributes>
      <attribute name="ccc-operator" value="<%=subscriber%>"/>
    </attributes>
    <div>
      <div>Представьтесь, пожалуйста</div>
      <input navigationId="submit" name="operatorName"/>
    </div>
    <navigation id="submit">
      <% if (afterVerification) { %>
      <link accesskey="0" pageId="http://localhost:9380/site/ccc/operator.jsp">Ok</link>
      <% }else { %>
      <link accesskey="0" pageId="operator.jsp">Ok</link>
      <% } %>
    </navigation>
  </page>
<%
    return;
  }

  List<User> usersAdded = new ArrayList<User>();
  if (usersAddedString != null) {
    String[] tokens = usersAddedString.split(",");
    for (String token : tokens) usersAdded.add(operator.userById(Integer.parseInt(token)));
  }

  List<User> users = operator.getUsers();

  if (select != null) {
    int id = Integer.parseInt(select);
    if (operator.getSelectedUser().id() != id) {
      operator.select(id);
      history = 3;
      //api.pushForHistory(operator);
    } else {
      history = 3;
    }
  } else if (endDialog) {
    api.endDialog(operator);
    //api.pushForHistory(operator);
    history = 3;
  }
  User selectedUser = operator.getSelectedUser();
  List<String> pendingMessages = api.getPendingMessages(selectedUser);

  if (selectedUser != null && message != null) {
    // This is simple message
    api.sendMessageToUser(message, operator, selectedUser);
    pageAttributes.edit(true);
  }
%>

<page <%=pageAttributes.print()%>>

  <attributes>
    <attribute name="ccc-operator" value="<%=subscriber%>"/>
  </attributes>
<% if (users.isEmpty()) { %>
  <div>Нет пользователей
    <input navigationId="submit" name="message"/>
  </div>
<% } else { %>

<% if(!usersAdded.isEmpty()) { %>
  <div><b>System</b>: добавлены пользователи:
  <% for (User user : usersAdded) {
    if (selectedUser == user) history = 3;
  %><b><%=user.userName()%></b>, <% } %>
  </div>
<% } %>

  <div>
    <% if (history > 0) { %>
    <% for (ChatHistoryEntry entry : api.historyFor(selectedUser).entries(history)) { %>
    <b><%=entry.name()%></b>: <%=entry.text()%><br/>
    <% } %>
    <% } %>
    <%if (selectedUser != null && userMessage != null && selectedUser.id() == Integer.parseInt(userId)) {
    %><b><%=selectedUser.userName()%>: </b><%=userMessage%><% } else if (userMessage != null && userId != null) { %>
    <%-- TODO: should be show only first? should we show message itself? --%>
    <b>System: </b>Сообщение от пользователя <b><%=operator.userById(Integer.parseInt(userId)).userName()%></b>
    <% } %>
    <input navigationId="submit" name="message"/>
  </div>
<%
    if (pendingMessages != null && history == -1) { %>
  <div>
    <% for (String pendingMessage : pendingMessages) { %>
    <b><%=selectedUser.userName()%></b>: <%=pendingMessage%><br/>
    <% } %>
  </div>
<% } %>

  <navigation>
    <% if (history > 0 && api.historyFor(selectedUser).size() > history) { %>
    <link accesskey="1" pageId="operator.jsp?history=<%=history * 2%>">Больше</link>
    <% } %>
    <link accesskey="2" pageId="operator.jsp?endDialog=true">Завершить диалог</link>
  </navigation>

  <navigation>
    <% for (User user : users) { %>
    <link accesskey="<%=user.id()%>" pageId="operator.jsp?select=<%=user.id()%>">
        <%=user.userName()%><%=user == selectedUser ? "(*)" : ""%></link>
    <% } %>
  </navigation>

<% } %>

  <% if (operator.isAdmin()) { %>
  <navigation>
    <link accesskey="3" pageId="admin.jsp">Режим администратора</link>
  </navigation>
  <% } %>

  <navigation id="submit">
    <link accesskey="0" pageId="operator.jsp">Ok</link>
  </navigation>

</page>
