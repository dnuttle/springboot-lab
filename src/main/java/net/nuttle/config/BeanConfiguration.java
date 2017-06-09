package net.nuttle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.nuttle.bean.TestBean2;

@Configuration
public class BeanConfiguration {

  //Configure bean with a static factory method; in this case, a builder
  //obtained from a static factory method.
  @Bean
  public TestBean2 getTestBean2() {
    return TestBean2.Builder.instance()
      .setArg("abc")
      .build();
  }
}
