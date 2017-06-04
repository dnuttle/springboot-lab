package net.nuttle.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import net.nuttle.model.FeedMessage;
import net.nuttle.model.Message;

@Service
public class MessageService implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);
  private Queue<Message> queue = new LinkedList<>();
  private int id = 100;
  
  @Override
  public void run(ApplicationArguments args) {
    while (true) {
      double r = Math.random();
      if (r > 0.55 && queue.size() < 1000) {
        FeedMessage msg = new FeedMessage("J" + id, "F" + id, "/path");
        queue.add(msg);
        id++;
        LOG.debug("Added message, queue now has " + queue.size() + " messages");
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        
      }
    }
  }
  
  public List<Message> getMessages() {
    List<Message> messages = new ArrayList<>();
    while (!queue.isEmpty()) {
      messages.add(queue.poll());
    }
    return messages;
  }
}
