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
  <% if(operatorsMsisdns.isEmpty()) { %>
  <div>Не найдено операторов</div>
  <% } else {%>
  <div>Выберите оператора</div>
  <% } %>

  <% for (String msisdn : operatorsMsisdns) { %>
  <navigation>
    <link accesskey="1" pageId="operator_edit.jsp?operatorMsisdn=<%=msisdn%>"><%=msisdn%></link>
  </navigation>
  <% } %>
  <navigation>
    <link accesskey="0" pageId="operators.jsp">Назад</link>
  </navigation>
</page>
