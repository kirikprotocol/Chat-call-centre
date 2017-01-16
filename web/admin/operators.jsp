<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.PrintableError" %>

<%
  AdminApi api = CccManager.adminApi(request);
  List<String> operatorsMsisdns = api.getOperatorMsisdns();
%>

<page>
  <div>Выберите действие</div>
  <navigation>
    <link accesskey="1" pageId="operator_add.jsp">Добавть оператора</link>
  </navigation>

  <navigation>
    <link accesskey="2" pageId="operator_list.jsp">Список (<%=operatorsMsisdns.size()%>)</link>
  </navigation>

  <navigation>
    <link accesskey="0" pageId="params.jsp">Назад</link>
  </navigation>

</page>

<%--
Количество активных разговоров на одного Оператора (по умолчанию - 5).
MSISDNы операторов (по умолчанию отсутствуют. Задаются Продавцом в роли Администратора).
Таймаут, после которого диалог с Потребителем завершается по неответу (по умолчанию - 20 мин).
--%>
