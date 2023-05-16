package com.apiRegion.springjwt.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class GestionNotification {

    @Scheduled(fixedDelay = 1000) // CECI NOUS PERMET D'EXECUTER UNE TACHE A UN MOMENT PRECISE
    public void scheduleFixedDelayTask() {
      ///  System.out.println(
         //       "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }
}
