package com.demo.publicserviceapi.interceptors;

import com.demo.publicserviceapi.config.PublicServiceConfig;
import com.demo.publicserviceapi.config.SubscriptionServiceConfig;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Component
public class SubscriptionServiceApiInterceptor implements ClientHttpRequestInterceptor {

    @Autowired
    private SubscriptionServiceConfig subscriptionServiceConfig;

    @Autowired
    private PublicServiceConfig publicServiceConfig;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(subscriptionServiceConfig.getAccessKey(), subscriptionServiceConfig.getAccessValue());
        String correlationId = ThreadContext.get(publicServiceConfig.getCorrelationIdKey());
        if (correlationId == null || correlationId.trim().isEmpty()) {
            correlationId = String.format("PSA-%s", UUID.randomUUID().toString().replace("-", "").toLowerCase());
        }
        headers.add(subscriptionServiceConfig.getCorrelationIdKey(), correlationId);
        return execution.execute(request, body);
    }

}
