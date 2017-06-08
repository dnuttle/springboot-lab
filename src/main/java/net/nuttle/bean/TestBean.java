package net.nuttle.bean;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
  private String value;

  @Override
  public String toString() {
    return "a bean with value: " + value;
  }
  
  public String getValue() {
    return value;
  }
}
