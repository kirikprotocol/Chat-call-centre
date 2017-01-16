<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>

<%
  String action = request.getParameter("action");
  String operatorMsisdn = request.getParameter("operatorMsisdn");
  AdminApi api = CccManager.adminApi(request);
%>

<page>
  <% if ("delete".equals(action)) {
    api.removeOperatorRecord(operatorMsisdn);
  %>
    <div>Оператор <%=operatorMsisdn%> успешно удалён</div>
  <% } else { %>
  <div>Выберите действие</div>
  <navigation>
    <link accesskey="1" pageId="operator_edit.jsp?operatorMsisdn=<%=operatorMsisdn%>&amp;action=delete">Удалить</link>
  </navigation>
  <% } %>
  <navigation>
    <link accesskey="0" pageId="operator_list.jsp">Назад</link>
  </navigation>

</page>
