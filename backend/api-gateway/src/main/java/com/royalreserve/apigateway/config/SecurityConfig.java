package com.royalreserve.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable()
            .authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/api/**").authenticated()
                .anyExchange().permitAll()
            .and()
            .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}
