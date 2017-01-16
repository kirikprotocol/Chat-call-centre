package com.eyelinecom.whoisd.sads2.ccc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: gev
 * Date: 28.10.16
 * Time: 16:39
 * To change this template use File | Settings | File Templates.
 */
public class Operator {
  private final String subscriber;
  private boolean admin;
  private final List<User> users = new ArrayList<>();
  private final Map<Integer, User> userMap = new HashMap<>();
  private User selectedUser;
  private String name;

  public Operator(String subscriber, boolean admin) {
    this.subscriber = subscriber;
    this.admin = admin;
  }

  public List<User> getUsers() {
    return users;
  }

  public User getSelectedUser() {
    return selectedUser;
  }

  public void add(User user) {
    if (users.isEmpty()) selectedUser = user;
    users.add(user);
    userMap.put(user.id(), user);
  }

  public void remove(User user) {
    users.remove(user);
    userMap.remove(user.id());
    if (selectedUser == user) {
      selectedUser = users.isEmpty() ? null : users.get(0);
    }
  }

  public void select(int userId) {
    selectedUser = userMap.get(userId);
  }

  @Override
  public String toString() {
    return "Operator{" + subscriber + '}';
  }

  public String subscriber() {
    return subscriber;
  }

  public boolean isAdmin() {
    return admin;
  }

  public User userById(int id) {
    for (User user : users) if (user.id() == id) return user;
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
