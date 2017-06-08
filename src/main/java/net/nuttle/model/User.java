package net.nuttle.model;

public class User {

  private String name;
  private String login;

  public void setName(String name) {
    this.name = name;
  }
  
  public void setLogin(String login) {
    this.login = login;
  }
  
  @Override
  public String toString() {
    return "name: " + name + ", login: " + login;
  }
}
