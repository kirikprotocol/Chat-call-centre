package com.eyelinecom.whoisd.sads2.ccc.model;

/**
 * Created with IntelliJ IDEA.
 * User: zoldorn
 * Date: 20.11.16
 * Time: 23:46
 * To change this template use File | Settings | File Templates.
 */
public class Parameter {
  private final String name;
  private final String value;
  private final String description;

  public Parameter(String name, String value) {
    this.name = name;
    this.value = value;
    ParameterId parameterId = ParameterId.get(name);
    this.description = parameterId != null ? parameterId.description : name;
  }

  public Parameter(ParameterId parameterId) {
    this.name = parameterId.key;
    this.value = parameterId.defaultValue.toString();
    this.description = parameterId.description;
  }

  public String name() {
    return name;
  }

  public String value() {
    return value;
  }

  public String description() {
    return description;
  }
}
