package net.nuttle.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * Simple microservice example.  All this does is run a continuous loop that
 * updates a timestamp every x seconds.  The loop might be a monitor of a queue.
 * Could add Kafka later on maybe.
 * This class could also implement CommandLineRunner, the only real difference is that
 * CommandLineRunner provides String...args and ApplicationRunner provides
 * ApplicationArguments, has convenience methods.
 * @author Dan
 *
 */
@Service
public class TimeService implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(TimeService.class);
  private long lastChecked;
  
  @Override
  public void run(ApplicationArguments args) {
    LOG.info("Starting TimeService with option args {} and non-option args {}", args.getOptionNames(), args.getNonOptionArgs());
    while (true) {
      try {
        lastChecked = System.currentTimeMillis();
        LOG.info("Service is awake");
        Thread.sleep(60000);
      } catch (InterruptedException e) {
        LOG.info("Thread interrupted");
        return;
      }
    }
  }
  
  public long getLastChecked() {
    return lastChecked;
  }
}
