package net.nuttle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.nuttle.bean.TestBean2;

@Configuration
public class BeanConfiguration {

  @Bean
  public TestBean2 getTestBean2() {
    return new TestBean2("abc");
  }
}
