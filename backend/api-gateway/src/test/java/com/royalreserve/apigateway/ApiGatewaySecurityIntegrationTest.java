package com.royalreserve.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ApiGatewaySecurityIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void actuatorAccessibleWithoutAuth() {
        webTestClient.get().uri("/actuator/health").exchange()
            .expectStatus().isOk();
    }

    @Test
    void apiRequiresAuth() {
        webTestClient.get().uri("/api/notifications").exchange()
            .expectStatus().isUnauthorized();
    }

    @Test
    void apiAccessibleWithJwt() {
        webTestClient.mutateWith(mockJwt()).get().uri("/api/notifications").exchange()
            .expectStatus().is5xxServerError();
    }
}
