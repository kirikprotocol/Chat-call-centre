<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Parameter" %>

<%
  String action = request.getParameter("action");
  String paramName = request.getParameter("paramName");
//  if (paramName == null) paramName = (String) session.getAttribute("paramName");
//  else session.setAttribute("paramName", paramName);
  String newValue = request.getParameter("newValue");

  AdminApi api = CccManager.adminApi(request);
  Parameter p = api.getParameter(paramName);
%>

<page>
  <% if (newValue != null) {
    p = api.setParameter(paramName, newValue);
  %>
  <div>Параметр успешно изменён.</div>
  <% } %>
  <div>
    <%=p.description()%>: <%=p.value()%><br/>
    Введите новое значение параметра
    <input navigationId="submit" name="newValue"/>
  </div>

  <navigation>
    <link accesskey="0" pageId="params.jsp">Назад</link>
  </navigation>

  <navigation id="submit">
    <link accesskey="9" pageId="param_edit.jsp?paramName=<%=paramName%>">Ok</link>
  </navigation>
</page>
