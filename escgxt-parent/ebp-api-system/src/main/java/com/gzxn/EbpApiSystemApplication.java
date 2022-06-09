package com.gzxn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@PropertySource({ "classpath:druid.properties" })
@SpringBootApplication
public class EbpApiSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbpApiSystemApplication.class, args);
    }

}
