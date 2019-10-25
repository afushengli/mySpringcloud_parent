package com.fsl.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Auther: chenrj
 * @Date: 2019/10/25 13:41
 * @Description:
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
