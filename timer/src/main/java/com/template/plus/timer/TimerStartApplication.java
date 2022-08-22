package com.template.plus.timer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lp
 * @date 2022/6/8 14:20
 */
@SpringBootApplication(scanBasePackages = {"com.template.plus"},exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EnableConfigurationProperties
@EnableScheduling
public class TimerStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(TimerStartApplication.class, args);
    }
}
