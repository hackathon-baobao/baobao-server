package com.baobao.baobaoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BaobaoServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaobaoServerApplication.class, args);
    }

}
