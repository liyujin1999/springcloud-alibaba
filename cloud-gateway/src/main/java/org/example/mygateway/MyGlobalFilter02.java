package org.example.mygateway;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Component
@Slf4j
public class MyGlobalFilter02 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("MyGlobalFilter02");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("我是MyGlobalFilter02");
            System.out.println();
        }));
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
