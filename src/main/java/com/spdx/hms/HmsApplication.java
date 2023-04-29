package com.spdx.hms;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HmsApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(HmsApplication.class)
                .web(WebApplicationType.SERVLET)
                .build()
                .run(args);
    }
}
