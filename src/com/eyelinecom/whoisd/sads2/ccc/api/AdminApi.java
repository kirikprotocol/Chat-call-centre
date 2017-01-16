package com.eyelinecom.whoisd.sads2.ccc.api;

import com.eyelinecom.whoisd.sads2.ccc.model.Admin;
import com.eyelinecom.whoisd.sads2.ccc.model.Parameter;
import com.eyelinecom.whoisd.sads2.ccc.model.PrintableError;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 18.11.16
 * Time: 14:23
 * To change this template use File | Settings | File Templates.
 */
public interface AdminApi {
  Admin updateAdmin(String subscriber);
  boolean isAdmin(String subscriber);
  List<String> getOperatorMsisdns();
  List<Parameter> getParameters();
  void addOperatorRecord(String msisdn);
  void removeOperatorRecord(String operatorMsisdn);
  Parameter getParameter(String name);
  Parameter setParameter(String name, String value);
}
