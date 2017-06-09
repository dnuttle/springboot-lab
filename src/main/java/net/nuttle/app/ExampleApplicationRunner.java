package net.nuttle.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import net.nuttle.bean.TestBean2;

/**
 * Instances of ApplicationRunner are executed at startup.
 * The @Component tag (or something equivalent) is needed.
 * @author Dan
 *
 */
@Component
@Order(1) //Call this one first
public class ExampleApplicationRunner implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleApplicationRunner.class);
  @Autowired
  ApplicationContext ctx;
  
  @Override
  public void run(ApplicationArguments args) {
    LOG.debug("Started an app runner");
    TestBean2 bean = (TestBean2) ctx.getBean(TestBean2.class);
    LOG.debug("TestBean2 value: " + bean.getArg());
  }
}
