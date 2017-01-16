package com.eyelinecom.whoisd.sads2.ccc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 21.11.16
 * Time: 14:32
 * To change this template use File | Settings | File Templates.
 */
public class ParameterId<T> {

  public final String key;
  public final T defaultValue;
  public final String description;
  private final Convertor<T> convertor;
  private static final List<ParameterId<?>> all = new ArrayList<ParameterId<?>>();

  private static final Convertor<Integer> intConvertor = new Convertor<Integer>() {
    @Override
    public Integer convert(String value) {
      return Integer.parseInt(value);
    }
  };

  private static final Convertor<String> stringConvertor = new Convertor<String>() {
    @Override
    public String convert(String value) {
      return value;
    }
  };

  private static final Convertor<Boolean> booleanConvertor = new Convertor<Boolean>() {
    @Override
    public Boolean convert(String value) {
      return Boolean.parseBoolean(value);
    }
  };

  // Количество активных разговоров на одного Оператора
  public static final ParameterId<Integer> activeChatCount = new ParameterId<>("activeChatCount", 5, intConvertor, "Количество активных разговоров на одного Оператора");
  // Таймаут, после которого диалог с Потребителем завершается по неответу
  public static final ParameterId<Integer> noanswerTimeout = new ParameterId<>("noanswerTimeout", 20, intConvertor, "Время ожидания ответа пользователя, мин.");

  private ParameterId(String key, T defaultValue, Convertor<T> convertor, String description) {
    this.key = key;
    this.defaultValue = defaultValue;
    this.convertor = convertor;
    this.description = description;
    MapHolder.map.put(key, this);
    all.add(this);
  }

  private ParameterId(String key, Convertor<T> convertor) {
    this(key, null, convertor, null);
  }

  public T convert(String value) {
    if (value == null) return defaultValue;
    return convertor.convert(value);
  }

  public static ParameterId get(String name) {
    return MapHolder.map.get(name);
  }

  public static Map<String, ParameterId> map() {
    return MapHolder.map;
  }

  private interface Convertor<T> {
    T convert(String value);
  }

  public static List<ParameterId<?>> all() {
    return all;
  }

  private static class MapHolder {
    public static final Map<String, ParameterId> map = new HashMap<String, ParameterId>();
  }

}
