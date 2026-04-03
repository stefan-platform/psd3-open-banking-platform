package com.stefan.gateway.integration.util;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.tomakehurst.wiremock.client.WireMock;

public class AbstractGatewayIntegrationTest {
	
	@Autowired
	public WebTestClient webTestClient;
	
	@BeforeEach
	void resetWireMock() {
		WireMock.reset();
	}
}
