package com.happy.research.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;

import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
//@Component
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 处理请求
        String blockFirst = request.getBody().map(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.readableByteCount()];
            dataBuffer.read(bytes);
            String reqBody = new String(bytes, StandardCharsets.UTF_8);

            return reqBody;
        }).blockFirst();


        // 继续执行过滤器链
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {

            DataBufferFactory bufferFactory = response.bufferFactory();

             response.writeWith(Mono.just(bufferFactory.wrap("Modified".getBytes())));


        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
