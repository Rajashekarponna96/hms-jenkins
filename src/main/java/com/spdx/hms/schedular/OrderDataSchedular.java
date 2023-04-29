package com.spdx.hms.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderDataSchedular {
	
	
	@Scheduled(cron = "${process.orders.cron.job}")
    public void run(){
        log.info("Start OrderDataSchedular");
       // orderDataPullService.process();
        log.info("End OrderDataSchedular");
    }

}
