<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Admin" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.utils.Common" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.OperatorApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Operator" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.utils.ConfApi" %>
<%@ page import="java.net.URLEncoder" %>

<%!
  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("com.eyelinecom.whoisd.sads2.ccc.admin_jsp");
%>
<%
  log.debug("req: " + Common.toString(request));
  // if we are here - we know that this is service admin
  AdminApi api = CccManager.adminApi(request);
  String subscriber = request.getParameter("subscriber");
  String service = request.getParameter("service");
  String serviceOwner = request.getParameter("serviceOwner");
  String action = request.getParameter("action");
  String backUrl = request.getParameter("backUrl");

  String successUrl = "ccc_base/admin.jsp";
    if (backUrl != null) successUrl = successUrl + "?backUrl=" + backUrl;
    successUrl = URLEncoder.encode(successUrl, "UTF-8");

  if (serviceOwner != null) {
    api.updateAdmin(serviceOwner);
    if (!api.isAdmin(subscriber)) {
      log.debug("Requesting verification for admin " + subscriber);
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
  } else {
    if (!api.isAdmin(subscriber)) {
      if (backUrl != null) {
        log.debug("Not admin, redirecting to " + backUrl);
        response.sendRedirect(backUrl);
      } else {
%>
  <page>
    <div>Доступ запрещён</div>
  </page>
<%
      }
    }
  }

  ConfApi confApi = ConfApi.get("eyeline", "Jh650CV9eE5HcazJDQDK0mqNvU6BHM3HX9L5OdOEz6qflG5sJ8KKojVjMEezJ1BF");
  Boolean enabled = confApi.isEnabled(service);


  // if we returned from operator page - remove operator
  OperatorApi operatorApi = CccManager.operatorApi(request);
  if (operatorApi.isActiveOperator(subscriber)) {
    Operator operator = operatorApi.getOperator(subscriber);
    operatorApi.removeOperator(operator);
  }

%>
<page>

  <%
    if (action != null) {
      if ("enable".equals(action)) {
        boolean success = confApi.setEnabled(service, true);
        if (success) enabled = true;
        if (success) { %>
  <div>Функционал CCC включён</div>
  <% } else { %>
  <div>Не удалось включить функционал CCC</div>
  <% }
      } else if ("disable".equals(action)) {
        boolean success = confApi.setEnabled(service, false);
        if (success) enabled = false;
    if(success) { %>
   <div>Функционал CCC отключён</div>
   <% } else { %>
   <div>Не удалось отколючить функционал CCC</div>
   <% }
    }
    }
  %>

  <% if(service != null) { %>
  <div>Конфигурация Call Chat Center сервиса <%=service%></div>
  <% } %>
  <navigation>
    <link accesskey="1" pageId="operator.jsp">Режим оператора</link>
  </navigation>
  <navigation>
    <link accesskey="2" pageId="admin/params.jsp">Параметры</link>
    <% if (enabled != null) {
      if(enabled) {%>
    <link accesskey="3" pageId="admin.jsp?action=disable">Отключить CCC</link>
    <%} else {%>
    <link accesskey="3" pageId="admin.jsp?action=enable">Включить CCC</link>
    <% }
    } %>
  </navigation>
</page>

<%--
  abonent: [3d956354-de7b-45c7-b04c-d3d4431ac774]
  protocol: [telegram]
  serviceId: [alfa.test]
  event.type: [text]
  event: [message]
  event.order: [189704274]
  event.text: [/connect_ccc]
  service: [alfa.test]
  user_id: [3d956354-de7b-45c7-b04c-d3d4431ac774]
  subscriber: [3d956354-de7b-45c7-b04c-d3d4431ac774]
--%>
