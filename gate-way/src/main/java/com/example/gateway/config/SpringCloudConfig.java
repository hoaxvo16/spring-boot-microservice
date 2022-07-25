package com.example.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.example.gateway.filters.AuthenticationFilter;

@Configuration
public class SpringCloudConfig {

        public static final String AUTH_URL = "http://localhost:8080";

        @Autowired
        AuthenticationFilter filter;

        @Bean
        public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
                return builder.routes()
                                .route(r -> r.path("/api/v1/protected/users/**").filters(f -> f.filter(filter))
                                                .uri("http://localhost:8081/"))

                                .route(r -> r.path("/api/v1/protected/orders/**").filters(f -> f.filter(filter))
                                                .uri("http://localhost:8083/"))
                                .route(r -> r.path("/api/v1/protected/catalogs/**").filters(f -> f.filter(filter))
                                                .uri("http://localhost:8082/"))
                                .build();
        }

        @Bean
        public RestTemplate getResetTemplate() {
                return new RestTemplate();
        }

}