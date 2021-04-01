package com.tonytaotao.sca.gateway.config;

import com.tonytaotao.sca.gateway.handler.CustomGatewayBlockExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 自定义网关配置
 * @author tonytaotao
 */
@Configuration
public class CustomGatewayConfig {

    /**
     * 注入自定义异常处理器
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public CustomGatewayBlockExceptionHandler customGatewayBlockExceptionHandler() {
        return new CustomGatewayBlockExceptionHandler();
    }

}
