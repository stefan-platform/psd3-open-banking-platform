package com.stefan.gateway.integration.filter;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.InetSocketAddress;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;

import reactor.core.publisher.Mono;
import ro.stefan.gateway.filter.RateLimitFilter;

class RateLimitFilterTest {

    private static final InetSocketAddress LOCALHOST = new InetSocketAddress("127.0.0.1", 8080);
    private static final GatewayFilterChain CHAIN = exchange -> Mono.empty();

    @Test
    void shouldSkipRateLimitingForNonPaymentPath() {
        RateLimitFilter filter = new RateLimitFilter(2);

        MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get("/api/consents/123").build()
        );

        filter.filter(exchange, CHAIN).block();

        assertThat(exchange.getResponse().getStatusCode()).isNull();
    }

    @Test
    void shouldAllowRequestsUpToConfiguredLimit() {
        RateLimitFilter filter = new RateLimitFilter(2);

        MockServerWebExchange exchange1 = MockServerWebExchange.from(
                MockServerHttpRequest.get("/api/payments/123").remoteAddress(LOCALHOST).build()
        );

        MockServerWebExchange exchange2 = MockServerWebExchange.from(
                MockServerHttpRequest.get("/api/payments/123").remoteAddress(LOCALHOST).build()
        );

        filter.filter(exchange1, CHAIN).block();
        filter.filter(exchange2, CHAIN).block();

        assertThat(exchange1.getResponse().getStatusCode()).isNull();
        assertThat(exchange2.getResponse().getStatusCode()).isNull();
    }

    @Test
    void shouldReturnTooManyRequestsWhenLimitIsExceeded() {
        RateLimitFilter filter = new RateLimitFilter(2);

        MockServerWebExchange exchange1 = MockServerWebExchange.from(
                MockServerHttpRequest.get("/api/payments/123").remoteAddress(LOCALHOST).build()
        );

        MockServerWebExchange exchange2 = MockServerWebExchange.from(
                MockServerHttpRequest.get("/api/payments/123").remoteAddress(LOCALHOST).build()
        );

        MockServerWebExchange exchange3 = MockServerWebExchange.from(
                MockServerHttpRequest.get("/api/payments/123").remoteAddress(LOCALHOST).build()
        );

        filter.filter(exchange1, CHAIN).block();
        filter.filter(exchange2, CHAIN).block();
        filter.filter(exchange3, CHAIN).block();

        assertThat(exchange3.getResponse().getStatusCode()).isEqualTo(HttpStatus.TOO_MANY_REQUESTS);
    }
}