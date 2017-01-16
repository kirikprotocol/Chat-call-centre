package com.eyelinecom.whoisd.sads2.ccc.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 18.11.16
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */
public class Common {

  @SuppressWarnings("unchecked")
  public static String toString(HttpServletRequest request) {
    StringBuilder sb = new StringBuilder();
    sb.append("HttpRequest{");
    sb.append("method: ").append(request.getMethod());
    sb.append(", requestURI: ").append(request.getRequestURI());
    sb.append(", parameters: [");
    Map<String, String[]> parameterMap = request.getParameterMap();
    Set<Map.Entry<String, String[]>> set = parameterMap.entrySet();
    String delim = "";
    for (Map.Entry<String, String[]> en : set) {
      String name = en.getKey();
      String value = en.getValue()[0];
      sb.append(delim).append(name).append(": ").append(value);
      delim = ", ";
    }
    sb.append("]");
    sb.append(", remoteHost: ").append(request.getRemoteHost());
    sb.append("}");
    return sb.toString();
  }
}
