package com.airlineticket.microservices.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.
                routes().
                route( p -> p.path("/airlines/**")
                        .uri("lb://airlines")).
                route(p -> p.path("/airplanes/**")
                        .uri("lb://airlines")).
                route(p -> p.path("/seats/**")
                        .uri("lb://airlines")).
                route( p -> p.path("/flights/**")
                        .uri("lb://flights")).
                build();
    }
}
