package net.nuttle.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;

import net.nuttle.bean.TestBean;
import net.nuttle.servlet.HelloController;

//SpringBootApplication annotation is an umbrella that includes:
//Configuration
//AutoConfigurationEnabled
//ComponentScan
@SpringBootApplication

//ComponentScan is a meta-annotation for SpringBootApplication,
//but I want to specify a particular package to be scanned.
@ComponentScan(basePackages={"net.nuttle.servlet"}, 
  includeFilters={@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=HelloController.class)})
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
  
  @Bean
  @Order(1)
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      /*
      String[] beanNames = ctx.getBeanDefinitionNames();
      Arrays.sort(beanNames);
      for (String bean : beanNames) {
        System.out.println(bean);
      }
      */
    };
  }
  
  //This can now be Autowired in any servlet
  @Bean
  public TestBean getTestBean() {
    return new TestBean();
  }
}
