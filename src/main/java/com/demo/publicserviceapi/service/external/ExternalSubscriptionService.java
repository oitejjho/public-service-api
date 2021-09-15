package com.demo.publicserviceapi.service.external;

import com.demo.publicserviceapi.config.SubscriptionServiceConfig;
import com.demo.publicserviceapi.model.Response;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionList;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionsResponse;
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
        return response;
    }

    /*public SubscribedUserEntity createSubscription(SubscribedUserEntity entity) {
        Optional<SubscribedUserEntity> existingEntity = this.subscribeUserRepository.findByEmail(entity.getEmail());
        if(existingEntity.isPresent())
            throw new InvalidRequestException(DUPLICATE_EMAIL_ERROR);
        SubscribedUserEntity persistedEntity = this.subscribeUserRepository.save(entity);
        return persistedEntity;
    }

    public SubscribedUserEntity getSubscription(String subscriptionId) {
        Optional<SubscribedUserEntity> subscribedUserEntityOptional = this.subscribeUserRepository.findBySubscriptionId(subscriptionId);
        if(subscribedUserEntityOptional.isEmpty())
            throw new NoSuchElementException();
        return subscribedUserEntityOptional.get();
    }

    public void updateSubscription(SubscribedUserEntity entity) {
        this.subscribeUserRepository.save(entity);
    }*/

}
