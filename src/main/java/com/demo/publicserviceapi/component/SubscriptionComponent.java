package com.demo.publicserviceapi.component;

import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionCreateResponse;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionResponse;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionsResponse;
import com.demo.publicserviceapi.model.request.CreateSubscriptionRequest;
import com.demo.publicserviceapi.model.response.SubscriptionCreateResponse;
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

    public SubscriptionListResponse getSubscriptions(int page, int size) {
        ExternalSubscriptionsResponse externalResponse = this.externalSubscriptionService.getSubscriptions(page, size);
        List<SubscriptionResponse> subscriptionListResponse = externalResponse.getData().getSubscriptions().stream().map(response -> this.conversionService.convert(response, SubscriptionResponse.class)).collect(Collectors.toList());
        SubscriptionListResponse response = new SubscriptionListResponse();
        response.setSubscriptions(subscriptionListResponse);
        response.setCurrentPage(externalResponse.getData().getCurrentPage());
        response.setPageSize(externalResponse.getData().getPageSize());
        response.setTotalPage(externalResponse.getData().getTotalPage());
        return response;
    }

    public SubscriptionResponse getSubscription(String id) {
        ExternalSubscriptionResponse externalResponse = this.externalSubscriptionService.getSubscription(id);
        return this.conversionService.convert(externalResponse.getData(), SubscriptionResponse.class);
    }

    public SubscriptionCreateResponse createSubscription(CreateSubscriptionRequest request) {
        ExternalSubscriptionCreateResponse externalResponse = this.externalSubscriptionService.createSubscription(request);
        return this.conversionService.convert(externalResponse.getData(), SubscriptionCreateResponse.class);
    }

    public void cancelSubscription(String id, String action) {
        this.externalSubscriptionService.cancelSubscription(id, action);
    }
}
