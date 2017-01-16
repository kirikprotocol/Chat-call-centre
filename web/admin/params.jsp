<%@ page language="java" contentType="text/xml; charset=UTF-8"
  %><?xml version="1.0" encoding="UTF-8"?>

<%@ page import="com.eyelinecom.whoisd.sads2.ccc.core.CccManager" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.api.AdminApi" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Admin" %>
<%@ page import="java.util.List" %>
<%@ page import="com.eyelinecom.whoisd.sads2.ccc.model.Parameter" %>

<%
  // TODO: check admin rights? look into DB?
  AdminApi api = CccManager.adminApi(request);
  List<Parameter> parameters = api.getParameters();
%>

<page>
  <div>Выберите параметр</div>
  <navigation>
    <link accesskey="1" pageId="operators.jsp">Управление операторами</link>
  </navigation>
  <% for (Parameter p : parameters) { %>
  <navigation>
    <link accesskey="1" pageId="param_edit.jsp?paramName=<%=p.name()%>"> <%=p.description()%> (<%=p.value()%>)</link>
  </navigation>
  <% } %>

  <navigation>
    <link accesskey="0" pageId="../admin.jsp">Назад</link>
  </navigation>
</page>
