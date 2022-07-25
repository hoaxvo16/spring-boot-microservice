package com.example.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.server.ServerWebExchange;

import com.example.gateway.config.SpringCloudConfig;
import com.example.gateway.utils.RestTemplateUtil;

import reactor.core.publisher.Mono;

@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        if (this.isAuthMissing(request))
            return this.onError(exchange, "Authorization header is missing in request", HttpStatus.UNAUTHORIZED);

        final String token = this.getAuthHeader(request);

        try {
            restTemplateUtil.authVerify(token,
                    SpringCloudConfig.AUTH_URL + "/verify/admin");

        } catch (HttpClientErrorException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return this.onError(exchange, e.getMessage(), e.getStatusCode());
        }

        return chain.filter(exchange);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

}