package io.metadata.apigateway.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class MutateRequestHeaderFilter  implements GlobalFilter, Ordered {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = jwtTokenProvider.resolveToken(exchange);
        if (jwtTokenProvider.validateToken(token)) {
            TokenClaims tokenClaims = jwtTokenProvider.getTokenClaims(jwtTokenProvider.resolveToken(exchange));
            ServerHttpRequest request = exchange.getRequest()
                    .mutate()
                    .header("x-angularpay-username", tokenClaims.getUserName())
                    .header("x-angularpay-user-reference", tokenClaims.getUserReference())
                    .header("x-angularpay-user-roles", tokenClaims.getAuthorities().toString())
                    .build();
            ServerWebExchange mutated = exchange.mutate().request(request).build();
            return chain.filter(mutated);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
