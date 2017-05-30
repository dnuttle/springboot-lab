package net.nuttle.servlet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.nuttle.bean.TestBean;

//RestController is annotated with both Controller and ResponseBody
@RestController
public class HelloController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
  
  @Autowired
  TestBean bean;
  
  @RequestMapping(value="/hello", method=RequestMethod.GET)
  public String hello(@RequestParam Map<String, String> allRequestParams, ModelMap map) {
    LOG.debug("hello");
    allRequestParams.forEach((key, val) -> {
      LOG.debug(key + "=" + val);
    });
    map.addAttribute("myAttr", "An attr value");
    //Now ${myAttr} can be read by a view...does this work in Thymeleaf?
    return "Greetings from Spring Boot " + bean.toString();
  }
}
