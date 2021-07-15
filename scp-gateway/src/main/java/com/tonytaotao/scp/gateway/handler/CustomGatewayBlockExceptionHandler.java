package com.tonytaotao.scp.gateway.handler;

import com.alibaba.fastjson.JSON;
import com.tonytaotao.scp.common.base.GlobalResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * 自定义网关限流异常处理器
 * @author tonytaotao
 */
public class CustomGatewayBlockExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse serverHttpResponse = serverWebExchange.getResponse();
        serverHttpResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String resultString = JSON.toJSONString(GlobalResult.DefaultFailure("服务开启限流保护,请稍后再试!"));
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(resultString.getBytes());
        return serverHttpResponse.writeWith(Mono.just(buffer));
    }
}
