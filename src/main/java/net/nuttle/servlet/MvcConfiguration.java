package net.nuttle.servlet;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

//@Configuration
//@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

//  @Bean(name="viewResolver")
  public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager mgr) {
    List<ViewResolver> resolvers = Arrays.asList(new ThymeleafViewResolver());
    ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setViewResolvers(resolvers);
    resolver.setContentNegotiationManager(mgr);
    return resolver;
  }
  
//  @Bean(name="viewResolver")
  public ViewResolver getThymeleafViewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    return resolver;
  }
  
  
}
