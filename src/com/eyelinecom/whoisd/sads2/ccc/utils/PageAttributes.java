package com.eyelinecom.whoisd.sads2.ccc.utils;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: zoldorn
 * Date: 31.10.16
 * Time: 4:52
 * To change this template use File | Settings | File Templates.
 */
public class PageAttributes {
  private boolean edit;
  private boolean keepSession;
  private Integer messageId;

  private PageAttributes() {

  }

  public String print() {
     // telegram.message.id: 42; telegram.keep.session: true; telegram.message.edit: true
    ArrayList<String> attributes = new ArrayList<>();
    if (messageId != null) attributes.add("telegram.message.id: " + messageId);
    if (keepSession) attributes.add("telegram.keep.session: true");
    if (edit) attributes.add("telegram.message.edit: true");
    if (attributes.isEmpty()) return "";
    return "attributes=\"" + join(attributes, "; ") + "\"";
  }

  private String join(Iterable<String> tokens, String delim) {
    StringBuilder sb = new StringBuilder();
    String loopDelim = "";
    for (String token : tokens) {
      sb.append(loopDelim).append(token);
      loopDelim = delim;
    }
    return sb.toString();
  }

  public PageAttributes edit(boolean edit) {
    this.edit = edit;
    return this;
  }

  public static PageAttributes create() {
    return new PageAttributes();
  }

  public PageAttributes keepSession(boolean keepSession) {
    this.keepSession = keepSession;
    return this;
  }

  public PageAttributes messageId(Integer messageId) {
    this.messageId = messageId;
    return this;
  }


}
