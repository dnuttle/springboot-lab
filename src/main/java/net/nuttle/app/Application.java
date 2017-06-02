package net.nuttle.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//SpringBootApplication annotation is an umbrella that includes:
//Configuration
//AutoConfigurationEnabled
//ComponentScan
@SpringBootApplication

//ComponentScan is a meta-annotation for SpringBootApplication,
//but I want to override.  In this example, only Controllers, Components and Services
//will be scanned in the specified packages.  @Repository would be ignored.
@ComponentScan(basePackages={"net.nuttle"}, 
  useDefaultFilters=false,
  includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, 
    value={Component.class, Controller.class, Service.class})}
)
//This says to ignore all of the default filters, but @Controller and @Component are still scanned;
//apparently if there are multiple ComponentScan tags, the result is an OR of all of them, not an AND.
//@ComponentScan(basePackages={"net.nuttle.servlet", "net.nuttle.bean"}, useDefaultFilters=false)

/* Notes on ComponentScan filtering
 * 
 * It is possible to do filtering like this, which effectively says that @Controller elements are in one package,
 * @Service in another and so on.  The question is whether it's worth the effort to do so.
@ComponentScan(
  basePackages={"net.nuttle.service"},
  userDefaultFilters=false, 
  includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value={Service.class})}
)
@ComponentScan(
  basePackages={"net.nuttle.controller"},
  userDefaultFilters=false, 
  includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value={Controller.class})}
)
@ComponentScan(
  basePackages={"net.nuttle.bean"},
  userDefaultFilters=false, 
  includeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value={Component.class})}
)
 * 
 * You can have several ComponentScan instances; behind the scenes, a "@ComponentScans" is created
 * that contains them all
 * 
 * The following seems to indicate that ComponentScan would do nothing; you would have to add
 * an includeFilters to get it to scan anything
@ComponentScan(useDefaultFilters=false)

 * FilterType.ASSIGNABLE_TYPE indicates that value param will have a list of class names to be included or excluded
@ComponentScan(basePackages={"net.nuttle.servlet", "net.nuttle.bean"}, 
  includeFilters={@ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value=HelloController.class)})

The following excludes any class annotated as @Service in the scan of the specified package
 * Note that FilterType.ANNOTATION does not support "pattern", you  have to specify value (list of annotation classes)
@ComponentScan(basePackages={"net.nuttle.servlet"}),
  excludeFilters={@ComponentScan.Filter(type=FilterType.ANNOTATION, value=org.springframework.stereotype.Service.class)})
*/
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
  //If you  don't register something here, you must instead mark it with
  //something like @Component, @Service, @Repository or @Controller
  /*
  @Bean
  public TestBean getTestBean() {
    return new TestBean();
  }
  */
}
