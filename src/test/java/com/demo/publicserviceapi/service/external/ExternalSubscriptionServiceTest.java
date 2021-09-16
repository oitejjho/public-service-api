package com.demo.publicserviceapi.service.external;


import com.demo.publicserviceapi.config.SubscriptionServiceConfig;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionCreateResponse;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionResponse;
import com.demo.publicserviceapi.model.external.response.ExternalSubscriptionsResponse;
import com.demo.publicserviceapi.model.request.CreateSubscriptionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static com.demo.publicserviceapi.config.SubscriptionServiceConfig.Path;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ExternalSubscriptionServiceTest {

    @Mock
    private RestTemplate subscriptionServiceApiRestTemplate;

    @Mock
    private SubscriptionServiceConfig subscriptionServiceConfig;

    @InjectMocks
    private ExternalSubscriptionService externalSubscriptionService;

    private ExternalSubscriptionsResponse externalSubscriptionsResponse;
    private ExternalSubscriptionResponse externalSubscriptionResponse;
    private ExternalSubscriptionCreateResponse externalSubscriptionCreateResponse;
    private CreateSubscriptionRequest createSubscriptionRequest;
    private ResponseEntity externalCustomerProfileInfoFromAdminResponseEntity;

    @BeforeEach
    public void setUp() {

        Path path = new Path();
        path.setSubscriptionList("/v1/subscriptions?page=%s&size=%s");
        path.setSubscription("/v1/subscriptions/%s");
        path.setCreateSubscription("/v1/subscriptions");
        path.setCancelSubscription("/v1/subscriptions/%s?action=%s");
        Mockito.when(subscriptionServiceConfig.getHost()).thenReturn("http://subscription.host");
        Mockito.when(subscriptionServiceConfig.getPath()).thenReturn(path);

        externalSubscriptionsResponse = new ExternalSubscriptionsResponse();
        externalSubscriptionResponse = new ExternalSubscriptionResponse();
        externalSubscriptionCreateResponse = new ExternalSubscriptionCreateResponse();
        createSubscriptionRequest = new CreateSubscriptionRequest();

    }

    @Test
    void testGetSubscriptionsSuccess() {

        externalCustomerProfileInfoFromAdminResponseEntity = new ResponseEntity<>(externalSubscriptionsResponse, HttpStatus.OK);
        Mockito.when(subscriptionServiceApiRestTemplate.getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionsResponse.class))).thenReturn(externalCustomerProfileInfoFromAdminResponseEntity);

        ExternalSubscriptionsResponse response = externalSubscriptionService.getSubscriptions(0, 10);

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionsResponse.class));

        Assertions.assertNotNull(response);

    }

    @Test
    void testGetSubscriptionsRestClientException() {

        Mockito.when(subscriptionServiceApiRestTemplate.getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionsResponse.class))).thenThrow(new RestClientException("Rest Client Exception"));

        assertThrows(RestClientException.class, () -> {
            ExternalSubscriptionsResponse response = externalSubscriptionService.getSubscriptions(0, 10);
        });

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionsResponse.class));

    }

    @Test
    void testGetSubscriptionSuccess() {

        externalCustomerProfileInfoFromAdminResponseEntity = new ResponseEntity<>(externalSubscriptionResponse, HttpStatus.OK);
        Mockito.when(subscriptionServiceApiRestTemplate.getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionResponse.class))).thenReturn(externalCustomerProfileInfoFromAdminResponseEntity);

        ExternalSubscriptionResponse response = externalSubscriptionService.getSubscription("subscriptionId");

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionResponse.class));

        Assertions.assertNotNull(response);

    }

    @Test
    void testGetSubscriptionRestClientException() {

        Mockito.when(subscriptionServiceApiRestTemplate.getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionResponse.class))).thenThrow(new RestClientException("Rest Client Exception"));

        assertThrows(RestClientException.class, () -> {
            ExternalSubscriptionResponse response = externalSubscriptionService.getSubscription("subscriptionId");
        });

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).getForEntity(Mockito.anyString(), Mockito.eq(ExternalSubscriptionResponse.class));

    }

    @Test
    void testCreateSubscriptionSuccess() {

        externalCustomerProfileInfoFromAdminResponseEntity = new ResponseEntity<>(externalSubscriptionCreateResponse, HttpStatus.OK);
        Mockito.when(subscriptionServiceApiRestTemplate.postForEntity("http://subscription.host/v1/subscriptions", createSubscriptionRequest, ExternalSubscriptionCreateResponse.class)).thenReturn(externalCustomerProfileInfoFromAdminResponseEntity);

        ExternalSubscriptionCreateResponse response = externalSubscriptionService.createSubscription(createSubscriptionRequest);

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).postForEntity("http://subscription.host/v1/subscriptions", createSubscriptionRequest, ExternalSubscriptionCreateResponse.class);

        Assertions.assertNotNull(response);

    }

    @Test
    void testCreateSubscriptionRestClientException() {

        Mockito.when(subscriptionServiceApiRestTemplate.postForEntity("http://subscription.host/v1/subscriptions", createSubscriptionRequest, ExternalSubscriptionCreateResponse.class)).thenThrow(new RestClientException("Rest Client Exception"));

        assertThrows(RestClientException.class, () -> {
            ExternalSubscriptionCreateResponse response = externalSubscriptionService.createSubscription(createSubscriptionRequest);
        });

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).postForEntity("http://subscription.host/v1/subscriptions", createSubscriptionRequest, ExternalSubscriptionCreateResponse.class);

    }

    @Test
    void testCancelSubscriptionSuccess() {

        Mockito.doNothing().when(subscriptionServiceApiRestTemplate).put(Mockito.anyString(), Mockito.isNull(), Mockito.any(Object.class));

        externalSubscriptionService.cancelSubscription("subscriptionId", "cancel");

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).put(Mockito.anyString(), Mockito.isNull(), Mockito.any(Object.class));

    }

    @Test
    void testCancelSubscriptionRestClientException() {

        Mockito.doThrow(new RestClientException("rest client exception")).when(subscriptionServiceApiRestTemplate).put(Mockito.anyString(), Mockito.isNull(), Mockito.any(Object.class));

        assertThrows(RestClientException.class, () -> {
            externalSubscriptionService.cancelSubscription("subscriptionId", "cancel");
        });

        Mockito.verify(subscriptionServiceApiRestTemplate, Mockito.times(1)).put(Mockito.anyString(), Mockito.isNull(), Mockito.any(Object.class));

    }

}
