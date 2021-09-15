package com.demo.publicserviceapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "subscription-service")
public class SubscriptionServiceConfig {

    @NotEmpty
    private String accessKey;

    @NotEmpty
    private String accessValue;

    @NotEmpty
    private String correlationIdKey;

    @NotEmpty
    private String host;

    @Valid
    private Path path;

    @Getter
    @Setter
    public static class Path {

        @NotEmpty
        private String subscriptionList;

        @NotEmpty
        private String subscription;

        @NotEmpty
        private String createSubscription;

        @NotEmpty
        private String cancelSubscription;

    }

}
