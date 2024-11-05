package com.odinese.cerpdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(exclude = RabbitAutoConfiguration.class)
public class CerpDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CerpDashboardApplication.class, args);
    }

}
