package com.eyelinecom.whoisd.sads2.ccc.core;

import com.eyelinecom.whoisd.sads2.ccc.api.AdminApi;
import com.eyelinecom.whoisd.sads2.ccc.api.OperatorApi;
import com.eyelinecom.whoisd.sads2.ccc.api.UserApi;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 28.10.16
 * Time: 16:32
 * To change this template use File | Settings | File Templates.
 */
public class CccManager {

  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CccManager.class);

  private ConcurrentHashMap<String, CccApiImpl> apiServiceMap = new ConcurrentHashMap<>();
  private DbManager dbManager;
  private String mobilizerUrl;

  public CccManager(DbManager dbManager, String mobilizerUrl) {
    this.dbManager = dbManager;
    this.mobilizerUrl = mobilizerUrl;
  }

  public void shutdown() {
    log.debug("Shutting down");
  }

  public void store(ServletContext context) {
    context.setAttribute(CccManager.class.getName(), this);
  }

  private static CccManager get(HttpServletRequest request) {
    return (CccManager) request.getSession().getServletContext().getAttribute(CccManager.class.getName());
  }

  private CccApiImpl apiImpl(HttpServletRequest request) {
    String service = request.getParameter("service");
    CccApiImpl api = apiServiceMap.get(service);
    if (api == null) {
      log.debug("Creating CccApiImpl for service " + service);
      api = new CccApiImpl(service, dbManager, mobilizerUrl);
      CccApiImpl prevApi = apiServiceMap.putIfAbsent(service, api);
      if (prevApi != null) api = prevApi;
    }
    return api;
  }

  public static OperatorApi operatorApi(HttpServletRequest request) {
    return get(request).apiImpl(request);
  }

  public static UserApi userApi(HttpServletRequest request) {
    return get(request).apiImpl(request);
  }

  public static AdminApi adminApi(HttpServletRequest request) {
    return get(request).apiImpl(request);
  }
}
