package com.tonytaotao.scp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 * @author tonytaotao
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {

        System.setProperty("csp.sentinel.app.type", "1");

        SpringApplication.run(GatewayApplication.class, args);
    }

}
