package com.eyelinecom.whoisd.sads2.ccc.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 28.10.16
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class User {

  private static final AtomicInteger idGenerator = new AtomicInteger();

  private final int id;
  private final String subscriber;
  private String userName;
  private String protocol;

  public User(String subscriber) {
    this.subscriber = subscriber;
    this.id = idGenerator.incrementAndGet();
  }

  public int id() {
    return id;
  }

  public String userName() {
    return userName;
  }

  @Override
  public String toString() {
    return "U[" + subscriber + "(" + id + "/" + userName + ")" + ']';
  }

  public String subscriber() {
    return subscriber;
  }

  public void userName(String userName) {
    this.userName = userName;
  }

  public void setProtocol(String protocol) {
    if (protocol != null) this.protocol = protocol;
  }

  public String getProtocol() {
    if (protocol == null) return "telegram";
    return protocol;
  }
}
