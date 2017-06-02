package net.nuttle.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import net.nuttle.service.TimeService;

@Component
public class HealthCheck implements HealthIndicator {

  private long slow = 30000;
  
  @Autowired
  TimeService timeService;
  
  @Override
  public Health health() {
    int errorCode = check();
    if (errorCode != 0) {
      return Health.down().withDetail("Error Code", errorCode).build();
    }
    return Health.up().withDetail("lastChecked", timeService.getLastChecked()).build();
  }
  
  private int check() {
    //Customize later; want to show a status of SLOW rather than DOWN.
    if (timeService.getLastChecked() < System.currentTimeMillis() - slow) {
      return 1;
    }
    return 0;
  }
}
