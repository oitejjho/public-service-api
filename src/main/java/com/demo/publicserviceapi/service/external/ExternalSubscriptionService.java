package com.demo.publicserviceapi.service.external;

import com.demo.publicserviceapi.config.SubscriptionServiceConfig;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionCreateResponse;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionResponse;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionsResponse;
import com.demo.publicserviceapi.model.request.CreateSubscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExternalSubscriptionService {

    private static final Logger LOG = LogManager.getLogger(ExternalSubscriptionService.class);

    private final RestTemplate subscriptionServiceApiRestTemplate;

    private final SubscriptionServiceConfig subscriptionServiceConfig;


    public ExternalSubscriptionsResponse getSubscriptions(int page, int size) {
        StringBuilder endpointBuilder = new StringBuilder().append(subscriptionServiceConfig.getHost()).append(subscriptionServiceConfig.getPath().getSubscriptionList());
        String endpoint = String.format(endpointBuilder.toString(), page, size);
        LOG.info("start getting subscription list from subscription service endpoint {}", endpoint);
        ResponseEntity<ExternalSubscriptionsResponse> responseEntity = subscriptionServiceApiRestTemplate.getForEntity(endpoint, ExternalSubscriptionsResponse.class);
        ExternalSubscriptionsResponse response = responseEntity.getBody();
        LOG.info("done getting subscription list from subscription service endpoint {}", endpoint);
        return response;
    }

    public ExternalSubscriptionResponse getSubscription(String id) {
        StringBuilder endpointBuilder = new StringBuilder().append(subscriptionServiceConfig.getHost()).append(subscriptionServiceConfig.getPath().getSubscription());
        String endpoint = String.format(endpointBuilder.toString(), id);
        LOG.info("start getting subscription from subscription service endpoint {}", endpoint);
        ResponseEntity<ExternalSubscriptionResponse> responseEntity = subscriptionServiceApiRestTemplate.getForEntity(endpoint, ExternalSubscriptionResponse.class);
        ExternalSubscriptionResponse response = responseEntity.getBody();
        LOG.info("done getting subscription from subscription service endpoint {}", endpoint);
        return response;
    }

    public ExternalSubscriptionCreateResponse createSubscription(CreateSubscriptionRequest request) {
        StringBuilder endpointBuilder = new StringBuilder().append(subscriptionServiceConfig.getHost()).append(subscriptionServiceConfig.getPath().getCreateSubscription());
        String endpoint = endpointBuilder.toString();
        LOG.info("start creating subscription from subscription service endpoint {}", endpoint);
        ResponseEntity<ExternalSubscriptionCreateResponse> responseEntity = subscriptionServiceApiRestTemplate.postForEntity(endpoint, request, ExternalSubscriptionCreateResponse.class);
        ExternalSubscriptionCreateResponse response = responseEntity.getBody();
        LOG.info("done creating subscription from subscription service endpoint {}", endpoint);
        return response;
    }

    public void cancelSubscription(String id, String action) {
        StringBuilder endpointBuilder = new StringBuilder().append(subscriptionServiceConfig.getHost()).append(subscriptionServiceConfig.getPath().getCancelSubscription());
        String endpoint = String.format(endpointBuilder.toString(), id, action);
        LOG.info("start cancelling subscription from subscription service endpoint {}", endpoint);
        subscriptionServiceApiRestTemplate.put(endpoint, null, Object.class);
        LOG.info("done cancelling subscription from subscription service endpoint {}", endpoint);
    }

}
