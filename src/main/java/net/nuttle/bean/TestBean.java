package net.nuttle.bean;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
  

  @Override
  public String toString() {
    return "a bean";
  }
  
  public String getValue() {
    return "bean value";
  }
}
