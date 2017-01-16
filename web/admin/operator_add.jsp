<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.PrintableError" %>

<%
  String operatorMsisdn = request.getParameter("operatorMsisdn");
  AdminApi api = CccManager.adminApi(request);
%>

<page>
  <% if (operatorMsisdn != null) {
    while (operatorMsisdn.startsWith("+")) operatorMsisdn = operatorMsisdn.substring(1);
    if (operatorMsisdn.startsWith("8")) operatorMsisdn = "7" + operatorMsisdn.substring(1);
      api.addOperatorRecord(operatorMsisdn); %>
  <div>Оператор <%=operatorMsisdn%> успешно добавлен.</div>
  <% } %>
  <div>Для добавления оператора введите его номер
    <input navigationId="submit" name="operatorMsisdn"/>
  </div>

  <navigation>
    <link accesskey="0" pageId="operators.jsp">Назад</link>
  </navigation>

  <navigation id="submit">
    <link accesskey="9" pageId="operator_add.jsp">Ok</link>
  </navigation>

</page>
