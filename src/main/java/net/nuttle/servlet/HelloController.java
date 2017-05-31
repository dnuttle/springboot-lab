package net.nuttle.servlet;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.nuttle.bean.TestBean;

//RestController is annotated with both Controller and ResponseBody
@Controller
public class HelloController {

  private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
  
  @Autowired
  TestBean bean;
  
  @RequestMapping(value="/hello", method=RequestMethod.GET)
  @ResponseBody
  public String hello(@RequestParam Map<String, String> allRequestParams, ModelMap map) {
    LOG.debug("hello");
    allRequestParams.forEach((key, val) -> {
      LOG.debug(key + "=" + val);
    });
    map.addAttribute("myAttr", "An attr value");
    //Now ${myAttr} can be read by a view...does this work in Thymeleaf?
    return "Greetings from Spring Boot " + bean.toString();
  }
  
  /*
   * @ResponseBody isn't required when class has @RestController.
   * Its purpose is to indicate that response should be written directly into http response body,
   * not placed into a model or interpreted as a view name.
   * To return a model and view when RestController is in place, and therefore the method has
   * an implicate ResponseBody, return ModelAndView instead.
   * Otheriwse use @Controller on the class and @ResponseBody only on methods where that is needed.
   */
  @RequestMapping(value="/bean", method=RequestMethod.GET)
  @ResponseBody
  public TestBean bean(@RequestParam Map<String, String> allRequestParams, ModelMap map) {
    return bean;
  }
  
  @RequestMapping(value="/template", method=RequestMethod.GET)
  @ResponseBody
  public ModelAndView template() {
    ModelAndView mv = new ModelAndView();
    //Template name defaults to the mapping, but can be changed
    mv.setViewName("login");
    mv.addObject("name", "A name value");
    mv.addObject("login", "A login name");
    return mv;
  }
}
