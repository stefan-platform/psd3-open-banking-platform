package com.stefan.gateway.integration.util;

import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import ro.stefan.gateway.ApiGatewayApplication;

@SpringBootTest(
	    classes = ApiGatewayApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "services.consent.base-url=http://localhost:${wiremock.server.port}",
                "services.payment.base-url=http://localhost:${wiremock.server.port}",
                "services.account.base-url=http://localhost:${wiremock.server.port}",
                "services.fraud.base-url=http://localhost:${wiremock.server.port}",
                "services.dispute.base-url=http://localhost:${wiremock.server.port}",
                "spring.security.user.name=admin",
                "spring.security.user.password=admin123",
                "gateway.rate-limit.max-requests-per-minute=3"
        })

@AutoConfigureWebClient
@AutoConfigureWireMock(port = 0)
public abstract class WireMockTestSupport extends AbstractGatewayIntegrationTest{
}
