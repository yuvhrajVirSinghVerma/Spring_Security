package com.example.demo.customEndpoints;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class DbHealth implements HealthIndicator {

    boolean isHealthGood(){
        return true;

    }
    @Override
    public Health health() {
        if(isHealthGood())return Health.up().withDetail("Db Url"," Service working fine").build();
        return Health.down().withDetail("Db Url","Service is Down").build();
    }
}
