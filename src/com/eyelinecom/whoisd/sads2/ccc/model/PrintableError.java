package com.eyelinecom.whoisd.sads2.ccc.model;

/**
 * Created with IntelliJ IDEA.
 * User: zoldorn
 * Date: 21.11.16
 * Time: 0:54
 * To change this template use File | Settings | File Templates.
 */
public class PrintableError {
  private final String message;

  public PrintableError(Throwable e) {
    message = e.getMessage();
  }

  public String message() {
    return message;
  }
}
