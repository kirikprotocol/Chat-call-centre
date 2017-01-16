package com.eyelinecom.whoisd.sads2.ccc.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 18.11.16
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
public class Admin {
  private final Set<String> subscribers;
  public final String serviceOwnerList;

  public Admin(String serviceOwnerList) {
    this.serviceOwnerList = serviceOwnerList;
    subscribers = new HashSet<String>(Arrays.asList(serviceOwnerList.split("[ ,]+")));
  }

  public boolean is(String subscriber) {
    return subscribers.contains(subscriber);
  }
}
