package com.royalreserve.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ApiGatewayIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private RouteDefinitionLocator locator;

    @Test
    void routesAreConfigured() {
        Flux<RouteDefinition> defs = locator.getRouteDefinitions();
        List<String> ids = defs.map(RouteDefinition::getId).collectList().block();
        assertTrue(ids.contains("account"));
        assertTrue(ids.contains("transaction"));
        assertTrue(ids.contains("asset"));
        assertTrue(ids.contains("notification"));
    }

    @Test
    void healthEndpointUp() {
        webTestClient.get().uri("/actuator/health")
            .exchange()
            .expectStatus().isOk()
            .expectBody().jsonPath("$.status").isEqualTo("UP");
    }
}
