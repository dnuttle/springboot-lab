package net.nuttle.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import net.nuttle.service.TimeService;

@Component
public class HealthCheck implements HealthIndicator {

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
    return 0;
  }
}
