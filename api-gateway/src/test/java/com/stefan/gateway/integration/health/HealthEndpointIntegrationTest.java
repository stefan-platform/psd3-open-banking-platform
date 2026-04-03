package com.stefan.gateway.integration.health;

import org.junit.jupiter.api.Test;

import com.stefan.gateway.integration.util.WireMockTestSupport;

public class HealthEndpointIntegrationTest extends WireMockTestSupport {
	
	@Test
	public void shouldReturnUpForActuatorHealth() {
		webTestClient.get()
			.uri("/actuator/health")
			.exchange()
			.expectStatus().isOk()
			.expectBody()
            .jsonPath("$.status").isEqualTo("UP");
			
	}
	
    @Test
    void shouldReturnUpForInternalHealthEndpoint() {
        webTestClient.get()
                .uri("/internal/health")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.service").isEqualTo("api-gateway")
                .jsonPath("$.status").isEqualTo("UP");
    }
}
