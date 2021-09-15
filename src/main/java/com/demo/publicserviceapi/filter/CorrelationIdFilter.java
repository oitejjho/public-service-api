package com.demo.publicserviceapi.filter;

import com.demo.publicserviceapi.config.PublicServiceConfig;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CorrelationIdFilter extends OncePerRequestFilter {

    private final PublicServiceConfig publicServiceConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        String correlationId = request.getHeader(publicServiceConfig.getCorrelationIdKey());

        if (correlationId == null || correlationId.trim().isEmpty()) {
            correlationId = String.format("PSA-%s", UUID.randomUUID().toString().replace("-", "").toLowerCase());
        }

        ThreadContext.put(publicServiceConfig.getCorrelationIdKey(), correlationId.trim());

        filterChain.doFilter(request, response);

    }

}
