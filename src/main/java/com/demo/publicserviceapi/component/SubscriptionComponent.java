package com.demo.publicserviceapi.component;

import com.demo.publicserviceapi.model.Response;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionList;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionsResponse;
import com.demo.publicserviceapi.model.response.SubscriptionListResponse;
import com.demo.publicserviceapi.model.response.SubscriptionResponse;
import com.demo.publicserviceapi.service.external.ExternalSubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class SubscriptionComponent {

    private final ExternalSubscriptionService externalSubscriptionService;
    private final ConversionService conversionService;

    /*public SubscriptionCreateResponse createSubscription(CreateSubscriptionRequest request) {
        SubscribedUserEntity entity = this.conversionService.convert(request, SubscribedUserEntity.class);

        entity.setSubscriptionId(UUID.randomUUID().toString());
        entity.setCreated(LocalDateTime.now());
        entity.setActiveFlag(true);
        SubscribedUserEntity persistedEntity = this.subscriptionPersistenceService.createSubscription(entity);

        SubscriptionCreateResponse response = new SubscriptionCreateResponse();
        response.setSubscriptionId(persistedEntity.getSubscriptionId());

        SubscriptionCompleteEvent event = new SubscriptionCompleteEvent();
        event.setEmail(entity.getEmail());
        event.setBody("Successfully subscribed");
        rabbitEventSender.send(event.getEmail());

        return response;
    }*/

    public SubscriptionListResponse getSubscriptions(int page, int size) {
        ExternalSubscriptionsResponse externalResponse = this.externalSubscriptionService.getSubscriptions(page, size);
        List<SubscriptionResponse> subscriptionListResponse = externalResponse.getData().getSubscriptions().stream().map(response -> this.conversionService.convert(response, SubscriptionResponse.class)).collect(Collectors.toList());
        SubscriptionListResponse response = new SubscriptionListResponse();
        response.setSubscriptions(subscriptionListResponse);
        response.setCurrentPage(externalResponse.getData().getCurrentPage());
        response.setPageSize(externalResponse.getData().getPageSize());
        response.setCurrentPage(externalResponse.getData().getCurrentPage());
        return response;
    }

    /*public SubscriptionResponse getSubscription(String subscriptionId) {
        SubscribedUserEntity subscribedUserEntity = this.subscriptionPersistenceService.getSubscription(subscriptionId);
        SubscriptionResponse subscriptionResponse = this.conversionService.convert(subscribedUserEntity, SubscriptionResponse.class);
        return subscriptionResponse;
    }

    public void cancelSubscription(String subscriptionId) {
        SubscribedUserEntity subscribedUserEntity = this.subscriptionPersistenceService.getSubscription(subscriptionId);
        subscribedUserEntity.setActiveFlag(false);
        this.subscriptionPersistenceService.updateSubscription(subscribedUserEntity);
    }*/
}
