package com.stefan.gateway.integration.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;

import com.stefan.gateway.integration.util.WireMockTestSupport;

import reactor.core.publisher.Mono;
import ro.stefan.gateway.filter.CorrelationIdFilter;

public class CorrelationIdFilterTest extends WireMockTestSupport{
    private final CorrelationIdFilter filter = new CorrelationIdFilter();
    
    @Test
    public void shouldGenerateCorrelationIdWhenMissing() {
    	MockServerHttpRequest request = MockServerHttpRequest.get("api/payments/123").build();
    	MockServerWebExchange exchange = MockServerWebExchange.from(request);
    	
    	GatewayFilterChain chain = ex -> {
    		String correlationId = ex.getRequest().getHeaders().getFirst(CorrelationIdFilter.HEADER_NAME);
    		assertThat(correlationId).isNotBlank();
    		assertThat(ex.getRequest().getHeaders().getFirst(CorrelationIdFilter.HEADER_NAME).equals(correlationId));
    		return Mono.empty();
    	};
    	filter.filter(exchange, chain);
    }
    
    @Test
    public void shouldPreserveCorrelationIdWhenAlreadyPresent() {
    	MockServerHttpRequest request = MockServerHttpRequest.get("api/payments/123")
    			.header(CorrelationIdFilter.HEADER_NAME, "corr-123")
    			.build();
    	
    	MockServerWebExchange exchange = MockServerWebExchange.from(request);
    	
    	GatewayFilterChain chain = ex ->{
    		assertThat(ex.getRequest().getHeaders().getFirst(CorrelationIdFilter.HEADER_NAME)).isEqualTo("corr-123");
    		assertThat(ex.getRequest().getHeaders().getFirst(CorrelationIdFilter.HEADER_NAME)).isEqualTo("corr-123");
    		return Mono.empty();
    	};
    	
    	filter.filter(exchange, chain);
    }
}
