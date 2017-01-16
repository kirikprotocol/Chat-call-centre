package com.eyelinecom.whoisd.sads2.ccc.model;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 18.11.16
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
public class Service {
  public final String name;
  public final int id;

  public Service(String name, int id) {
    this.name = name;
    this.id = id;
  }

  @Override
  public String toString() {
    return "Service{" +
      "id=" + id +
      ", name='" + name + '\'' +
      '}';
  }
}
