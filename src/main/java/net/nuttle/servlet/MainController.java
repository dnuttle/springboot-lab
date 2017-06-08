package net.nuttle.servlet;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import net.nuttle.bean.TestBean;
import net.nuttle.model.Message;
import net.nuttle.model.User;
import net.nuttle.service.MessageService;

//RestController is annotated with both Controller and ResponseBody
@Controller
public class MainController {

  private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
  
  @Autowired
  TestBean bean;
  
  @Autowired
  MessageService messageService;
  
  @Autowired
  ApplicationContext appContext;
  
  @RequestMapping(value="/hello", method=RequestMethod.GET)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
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
    ModelAndView mv = new ModelAndView("template");
    //If zero-arg ctor used, view name defaults to mapping name but can be overridden
    //mv.setViewName("login");
    //If model has only one element, its name and value can be passed as args 2 and 3
    //mv = new ModelAndView("login", "login", "A login name");
    return mv;
  }
  
  @RequestMapping(value="/login", method=RequestMethod.GET)
  @ResponseBody
  public ModelAndView login() {
    ModelAndView mv = new ModelAndView("login", "login", "A login name");
    LOG.info("login() method called");
    return mv;
  }
  
  /**
   * Demonstration of forwarding using ModelAndView.
   * The ModelAndView returned depends on a condition, in this case
   * whether or not a "forward" parameter is sent.
   * @param allReqParams
   * @return
   */
  @RequestMapping(value="/condition", method=RequestMethod.GET)
  public ModelAndView condition(@RequestParam Map<String, String> allReqParams) {
    ModelAndView mv;
    /*
     * Note that when forwarding is used, the other mapping is not triggered.
     * E.g., if the "login" ModelAndView is returned, that's it; the model and view
     * contained in that instance is paired with the actual view (the template).
     * There is no called to the login() method above.
     * But as per example below, this logic can <em>call</em> that method
     * instead of returning a ModelAndView.
     */
    LOG.info("condition() method  called");
    if (allReqParams.containsKey("forward")) {
      mv = new ModelAndView("template");
    } else {
      //mv = new ModelAndView("login", "login", "Forwarded here");
      //In this case, we don't create the ModelAndView directly, but delegate
      //to another method/mapping.
      return login();
    }
    return mv;
  }
  
  @RequestMapping(value="/monitor", method=RequestMethod.GET)
  public ModelAndView monitor() {
    ModelAndView mv = new ModelAndView("servicelog");
    return mv;
  }
  
  @RequestMapping(value="/messages", method=RequestMethod.GET, produces="application/json")
  @ResponseBody
  //A @RequestParam can specify a name and a default value; note that "name" and "value" are aliases for parameter name!
  public ModelMap messages(@RequestParam(name="maxMessages", defaultValue="20") int maxMessages) {
    ModelMap map = new ModelMap();
    map.addAttribute("type", "FEED");
    List<Message> messages = messageService.getMessages();
    int start = messages.size() - maxMessages;
    if (start < 0) start = 0;
    int end = start + maxMessages;
    if (messages.size() < end) end = messages.size();
    map.addAttribute("data", messages.subList(start, end));
    return map;
  }
  
  @RequestMapping(value="/generatemessage", method=RequestMethod.GET)
  @ResponseBody
  public String generate() {
    messageService.generateMessage();
    return "generated";
  }
  
  @RequestMapping(value="/posttest", method=RequestMethod.POST)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public String postTest() {
    return "posttest";
  }
  
  @RequestMapping(value="/puttest", method=RequestMethod.PUT)
  @ResponseBody
  @ResponseStatus(HttpStatus.ACCEPTED)
  public String putTest() {
    return "puttest";
  }
  
  /**
   * Attempt to create User from parameters
   */
  @RequestMapping(value="/user", method=RequestMethod.POST)
  @ResponseBody
  public String user(@RequestBody User user) {
    return user.toString();
  }
}
