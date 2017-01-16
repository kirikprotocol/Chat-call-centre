package com.eyelinecom.whoisd.sads2.ccc.listeners;
/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 28.10.16
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */

import com.eyelinecom.whoisd.sads2.ccc.core.CccManager;
import com.eyelinecom.whoisd.sads2.ccc.core.DbManager;import com.eyelinecom.whoisd.sads2.ccc.utils.Common;

import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class CccInitializer implements ServletContextListener {

  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CccInitializer.class);

  private static final String DB_SOURCE_NAME = "java:/comp/env/jdbc/CCC";

  public CccManager manager;

  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Initializing CCC site");
    try {
      InitialContext cxt = new InitialContext();
      DataSource ds = (DataSource) cxt.lookup(DB_SOURCE_NAME);

      DbManager dbManager = new DbManager(ds);

      String mobilizerUrl = sce.getServletContext().getInitParameter("mobilizer-url");
      log.debug("mobilizerUrl: " + mobilizerUrl);

      // TODO: for test, create tables if they don't exist
      dbManager.execute("CREATE TABLE IF NOT EXISTS ccc_services (id INTEGER PRIMARY KEY, name TEXT, adminMsisdn TEXT)");
      dbManager.execute("CREATE TABLE IF NOT EXISTS ccc_parameters (service_id INTEGER, name TEXT, value TEXT)");
      dbManager.execute("CREATE TABLE IF NOT EXISTS ccc_operators (service_id INTEGER, msisdn TEXT)");

      manager = new CccManager(dbManager, mobilizerUrl);
      manager.store(sce.getServletContext());
    } catch (Exception e) {
      log.error("Initialization failed", e);
      throw new RuntimeException(e);
    }

  }

  public void contextDestroyed(ServletContextEvent sce) {
    manager.shutdown();
  }

}
