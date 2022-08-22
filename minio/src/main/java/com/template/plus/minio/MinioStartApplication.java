package com.template.plus.minio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lp
 * @date 2022/5/18 10:37
 */
@SpringBootApplication(scanBasePackages = {"com.template.plus"},exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
@EnableConfigurationProperties
@EnableScheduling
public class MinioStartApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinioStartApplication.class, args);
    }

}

