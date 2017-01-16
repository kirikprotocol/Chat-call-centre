package com.eyelinecom.whoisd.sads2.ccc.core;

import com.eyelinecom.whoisd.sads2.ccc.model.Parameter;
import com.eyelinecom.whoisd.sads2.ccc.model.ParameterId;
import com.eyelinecom.whoisd.sads2.ccc.model.Service;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 18.11.16
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class DbManager {

  private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(DbManager.class);

  private DataSource dataSource;

  public DbManager(DataSource dataSource) {

    this.dataSource = dataSource;
  }

  public Service getService(String serviceName) {
    int id = getServiceId(serviceName);
    if (id == -1) id = addService(serviceName);
    return new Service(serviceName, id);
  }

  private int addService(String serviceName) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("inserting service to DB: " + serviceName);
    try {
      con = getConnection();
      st = con.prepareStatement("INSERT INTO ccc_services (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
      st.setString(1, serviceName);
      int affectedRows = st.executeUpdate();
      if (affectedRows == 0) throw new SQLException("Creating service failed, no rows affected");
      rs = st.getGeneratedKeys();
      if (!rs.next()) throw new SQLException("Creating service failed, no ID obtained");
      int id = rs.getInt(1);
      log.debug("inserted service to DB, generated id: " + id);
      return id;
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  private int getServiceId(String serviceName) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("getting service from DB: " + serviceName);
    try {
      con = getConnection();
      st = con.prepareStatement("SELECT id FROM ccc_services WHERE name=?");
      st.setString(1, serviceName);
      rs = st.executeQuery();
      int id = rs.next() ? rs.getInt(1) : -1;
      log.debug("getting service from DB result id: " + id);
      return id;
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  private static void close(Connection con, PreparedStatement st, ResultSet rs, Logger logger) {
    if (logger == null) logger = log;
    if (rs != null)
      try {
        rs.close();
      } catch (SQLException e) {
        logger.error("error while closing resultset");
      }
    if (st != null)
      try {
        st.close();
      } catch (SQLException e) {
        logger.error("error while closing statament");
      }
    if (con != null)
      try {
        con.close();
      } catch (SQLException e) {
        logger.error("error while closing connection");
      }
  }

  public Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }

  public ArrayList<String> getOperatorMsisdns(Service service) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("getting msisdn list from DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("SELECT msisdn FROM ccc_operators WHERE service_id=?");
      st.setInt(1, service.id);
      rs = st.executeQuery();
      ArrayList<String> msisdns = new ArrayList<>();
      while (rs.next()) msisdns.add(rs.getString(1));
      log.debug("got msisdn list from DB for service " + service + ": " + msisdns);
      return msisdns;
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  public void execute(String query) {
    Connection con = null;
    PreparedStatement st = null;
    log.debug("executing query \"" + query + "\"");
    try {
      con = getConnection();
      st = con.prepareStatement(query);
      st.execute();
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, null, log);
    }
  }

  public List<Parameter> getParameters(Service service) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("getting parameter list from DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("SELECT name, value FROM ccc_parameters WHERE service_id=?");
      st.setInt(1, service.id);
      rs = st.executeQuery();
      ArrayList<Parameter> parameters = new ArrayList<>();
      while (rs.next()) parameters.add(new Parameter(rs.getString(1), rs.getString(2)));
      log.debug("got parameter list from DB for service " + service + ": " + parameters);
      return parameters;
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  public void addOperator(Service service, String msisdn) throws SQLException {
    // TODO: check is msisdn unique
    Connection con = null;
    PreparedStatement st = null;
    log.debug("adding oprator " + msisdn + " to DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("INSERT INTO ccc_operators (service_id, msisdn) VALUES (?, ?)");
      st.setInt(1, service.id);
      st.setString(2, msisdn);
      int count = st.executeUpdate();
      if (count != 1) throw new SQLException("Failed to insert operator " + msisdn);
      log.debug("added oprator " + msisdn + " to DB for service " + service);
    } finally {
      close(con, st, null, log);
    }
  }

  public void removeOperator(Service service, String msisdn) throws SQLException {
    // TODO: check is msisdn unique
    Connection con = null;
    PreparedStatement st = null;
    log.debug("removing oprator " + msisdn + " to DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("DELETE FROM ccc_operators WHERE service_id=? AND msisdn=?");
      st.setInt(1, service.id);
      st.setString(2, msisdn);
      int count = st.executeUpdate();
      if (count != 1) throw new SQLException("Failed to remove operator " + msisdn);
      log.debug("removed oprator " + msisdn + " to DB for service " + service);
    } finally {
      close(con, st, null, log);
    }
  }

  public boolean isOperator(Service service, String msisdn) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("getting " + msisdn + " operator from DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("SELECT msisdn FROM ccc_operators WHERE service_id=? AND msisdn=?");
      st.setInt(1, service.id);
      st.setString(2, msisdn);
      rs = st.executeQuery();
      boolean result = rs.next();
      log.debug("got " + msisdn + " operator from DB for service " + service + ": " + result);
      return result;
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  public void updateAdminMsisdn(Service service, String msisdn) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("updating adminMsisdn " + msisdn + " in DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("UPDATE ccc_services SET adminMsisdn=? WHERE id=?");
      st.setString(1, msisdn);
      st.setInt(2, service.id);
      int count = st.executeUpdate();
      if (count != 1) throw new SQLException("Updating adminMsisdn failed");
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  public String getAdmin(Service service) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("getting admin from DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("SELECT adminMsisdn FROM ccc_services WHERE id=?");
      st.setInt(1, service.id);
      rs = st.executeQuery();
      if (!rs.next()) return null;
      String adminMsisdn = rs.getString(1);
      log.debug("got admin from DB for service " + service + ": " + adminMsisdn);
      return adminMsisdn;
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  public Parameter getParameter(Service service, String name) {
    Connection con = null;
    PreparedStatement st = null;
    ResultSet rs = null;
    log.debug("getting parameter " + name + " from DB for service " + service);
    try {
      con = getConnection();
      st = con.prepareStatement("SELECT value FROM ccc_parameters WHERE service_id=? AND name=?");
      st.setInt(1, service.id);
      st.setString(2, name);
      rs = st.executeQuery();
      if (!rs.next()) return null;
      log.debug("got parameter " + name + " from DB for service " + service);
      return new Parameter(name, rs.getString(1));
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, rs, log);
    }
  }

  public Parameter setParameter(Service service, String name, String value) {
    Connection con = null;
    PreparedStatement st = null;
    log.debug("updating parameter " + name + " from DB for service " + service + ", new value: " + value);
    try {
      con = getConnection();
      st = con.prepareStatement("INSERT OR REPLACE INTO ccc_parameters (service_id, name, value) values (?, ?, ?)");
      st.setInt(1, service.id);
      st.setString(2, name);
      st.setString(3, value);
      int count = st.executeUpdate();
      log.debug("updated parameter " + name + " from DB for service " + service + ", count: " + count);
      return new Parameter(name, value);
    } catch (Throwable e) {
      log.error("", e);
      throw new RuntimeException(e);
    } finally {
      close(con, st, null, log);
    }
  }
}
