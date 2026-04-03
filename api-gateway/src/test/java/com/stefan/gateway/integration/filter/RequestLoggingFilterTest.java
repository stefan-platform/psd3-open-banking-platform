package com.stefan.gateway.integration.filter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;

import reactor.core.publisher.Mono;
import ro.stefan.gateway.filter.CorrelationIdFilter;
import ro.stefan.gateway.filter.RequestLoggingFilter;

@ExtendWith(OutputCaptureExtension.class)
public class RequestLoggingFilterTest {
	private final RequestLoggingFilter loggingFilter = new RequestLoggingFilter(); 
	
	@Test
	void shouldLogIncomingAndOutgoingRequest(CapturedOutput output) {
		
		//given
		MockServerHttpRequest request = MockServerHttpRequest.get("/api/payments/123")
				.header(CorrelationIdFilter.HEADER_NAME,"corr-123").build();
		
		MockServerWebExchange exchange = MockServerWebExchange.from(request);
		
		GatewayFilterChain chain = ex -> {
			ex.getResponse().setStatusCode(HttpStatus.OK);
			return Mono.empty();
		};
		
		//when
		loggingFilter.filter(exchange, chain).block();
		
		//then
		String logs = output.getOut();
		
        assertThat(logs).contains("Incoming request");
        assertThat(logs).contains("Outgoing response");
        
        assertThat(logs).contains("/api/payments/123");
        assertThat(logs).contains("corr-123");
        assertThat(logs).contains("200");
        
	}
	
}
