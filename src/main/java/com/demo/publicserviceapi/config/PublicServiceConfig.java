package com.demo.publicserviceapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "public-service")
public class PublicServiceConfig {

    @NotEmpty
    private String xApiKey;

    @NotEmpty
    private String correlationIdKey;

}
