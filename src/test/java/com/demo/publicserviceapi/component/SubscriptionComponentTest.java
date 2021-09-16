package com.demo.publicserviceapi.component;

import com.demo.publicserviceapi.model.external.response.*;
import com.demo.publicserviceapi.model.request.CreateSubscriptionRequest;
import com.demo.publicserviceapi.model.response.SubscriptionCreateResponse;
import com.demo.publicserviceapi.model.response.SubscriptionListResponse;
import com.demo.publicserviceapi.model.response.SubscriptionResponse;
import com.demo.publicserviceapi.service.external.ExternalSubscriptionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SubscriptionComponentTest {

    @Mock
    private ExternalSubscriptionService externalSubscriptionService;
    @Mock
    private ConversionService conversionService;

    @InjectMocks
    private SubscriptionComponent subscriptionComponent;

    private SubscriptionResponse subscriptionResponse;
    private ExternalSubscriptionList data;
    private ExternalSubscriptionsResponse externalSubscriptionsResponse;
    private ExternalSubscriptionResponse externalSubscriptionResponse;
    private CreateSubscriptionRequest createSubscriptionRequest;
    private ExternalSubscriptionCreateResponse externalSubscriptionCreateResponse;
    private SubscriptionCreateResponse subscriptionCreateResponse;

    @BeforeEach
    public void setUp() {

        subscriptionResponse = new SubscriptionResponse();
        data = new ExternalSubscriptionList();
        data.setSubscriptions(new ArrayList<>() {{
            add(new ExternalSubscription());
        }});
        data.setCurrentPage(0);
        data.setPageSize(1);
        data.setTotalPage(1);
        externalSubscriptionsResponse = new ExternalSubscriptionsResponse();
        externalSubscriptionsResponse.setData(data);

        externalSubscriptionResponse = new ExternalSubscriptionResponse();
        externalSubscriptionResponse.setData(new ExternalSubscription());

        externalSubscriptionCreateResponse = new ExternalSubscriptionCreateResponse();
        SubscriptionCreate subscriptionCreate = new SubscriptionCreate();
        subscriptionCreate.setSubscriptionId("subscriptionId");
        externalSubscriptionCreateResponse.setData(subscriptionCreate);

        subscriptionCreateResponse = new SubscriptionCreateResponse();
        subscriptionCreateResponse.setSubscriptionId("subscriptionId");

        createSubscriptionRequest = new CreateSubscriptionRequest();
    }

    @Test
    void testGetSubscriptionsSuccess() {

        Mockito.when(externalSubscriptionService.getSubscriptions(Mockito.anyInt(), Mockito.anyInt())).thenReturn(externalSubscriptionsResponse);
        Mockito.when(conversionService.convert(externalSubscriptionsResponse.getData().getSubscriptions().get(0), SubscriptionResponse.class)).thenReturn(subscriptionResponse);

        SubscriptionListResponse response = subscriptionComponent.getSubscriptions(1, 10);

        Mockito.verify(conversionService, Mockito.times(1)).convert(externalSubscriptionsResponse.getData().getSubscriptions().get(0), SubscriptionResponse.class);
        Mockito.verify(externalSubscriptionService, Mockito.times(1)).getSubscriptions(Mockito.anyInt(), Mockito.anyInt());

        Assertions.assertEquals(1, response.getSubscriptions().size());
    }

    @Test
    void testGetSubscriptionsSuccessServiceRuntimeException() {

        Mockito.when(externalSubscriptionService.getSubscriptions(Mockito.anyInt(), Mockito.anyInt())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            SubscriptionListResponse response = subscriptionComponent.getSubscriptions(1, 10);
        });

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).getSubscriptions(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void testGetSubscriptionsSuccessConversionException() {

        Mockito.when(externalSubscriptionService.getSubscriptions(Mockito.anyInt(), Mockito.anyInt())).thenReturn(externalSubscriptionsResponse);
        Mockito.when(conversionService.convert(externalSubscriptionsResponse.getData().getSubscriptions().get(0), SubscriptionResponse.class)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            SubscriptionListResponse response = subscriptionComponent.getSubscriptions(1, 10);
        });

        Mockito.verify(conversionService, Mockito.times(1)).convert(externalSubscriptionsResponse.getData().getSubscriptions().get(0), SubscriptionResponse.class);
        Mockito.verify(externalSubscriptionService, Mockito.times(1)).getSubscriptions(Mockito.anyInt(), Mockito.anyInt());
    }

    @Test
    void testGetSubscriptionSuccess() {

        Mockito.when(externalSubscriptionService.getSubscription(Mockito.anyString())).thenReturn(externalSubscriptionResponse);
        Mockito.when(conversionService.convert(externalSubscriptionResponse.getData(), SubscriptionResponse.class)).thenReturn(subscriptionResponse);

        SubscriptionResponse response = subscriptionComponent.getSubscription("subscriptionId");

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).getSubscription(Mockito.anyString());
        Mockito.verify(conversionService, Mockito.times(1)).convert(externalSubscriptionResponse.getData(), SubscriptionResponse.class);

        Assertions.assertNotNull(response);
    }

    @Test
    void testGetSubscriptionServiceRuntimeException() {

        Mockito.when(externalSubscriptionService.getSubscription(Mockito.anyString())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            SubscriptionResponse response = subscriptionComponent.getSubscription("subscriptionId");
        });

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).getSubscription(Mockito.anyString());
    }

    @Test
    void testGetSubscriptionConversionRuntimeException() {

        Mockito.when(externalSubscriptionService.getSubscription(Mockito.anyString())).thenReturn(externalSubscriptionResponse);
        Mockito.when(conversionService.convert(externalSubscriptionResponse.getData(), SubscriptionResponse.class)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            SubscriptionResponse response = subscriptionComponent.getSubscription("subscriptionId");
        });

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).getSubscription(Mockito.anyString());
        Mockito.verify(conversionService, Mockito.times(1)).convert(externalSubscriptionResponse.getData(), SubscriptionResponse.class);
    }

    @Test
    void testCreateSubscriptionSuccess() {

        Mockito.when(externalSubscriptionService.createSubscription(createSubscriptionRequest)).thenReturn(externalSubscriptionCreateResponse);
        Mockito.when(conversionService.convert(externalSubscriptionCreateResponse.getData(), SubscriptionCreateResponse.class)).thenReturn(subscriptionCreateResponse);

        SubscriptionCreateResponse response = subscriptionComponent.createSubscription(createSubscriptionRequest);

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).createSubscription(createSubscriptionRequest);
        Mockito.verify(conversionService, Mockito.times(1)).convert(externalSubscriptionCreateResponse.getData(), SubscriptionCreateResponse.class);

        Assertions.assertNotNull(response);
    }

    @Test
    void testCreateSubscriptionServiceException() {

        Mockito.when(externalSubscriptionService.createSubscription(createSubscriptionRequest)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            SubscriptionCreateResponse response = subscriptionComponent.createSubscription(createSubscriptionRequest);
        });

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).createSubscription(createSubscriptionRequest);
    }

    @Test
    void testCreateSubscriptionConversionException() {

        Mockito.when(externalSubscriptionService.createSubscription(createSubscriptionRequest)).thenReturn(externalSubscriptionCreateResponse);
        Mockito.when(conversionService.convert(externalSubscriptionCreateResponse.getData(), SubscriptionCreateResponse.class)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            SubscriptionCreateResponse response = subscriptionComponent.createSubscription(createSubscriptionRequest);
        });

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).createSubscription(createSubscriptionRequest);
        Mockito.verify(conversionService, Mockito.times(1)).convert(externalSubscriptionCreateResponse.getData(), SubscriptionCreateResponse.class);
    }

    @Test
    void testCancelSubscriptionSuccess() {

        Mockito.doNothing().when(externalSubscriptionService).cancelSubscription(Mockito.anyString(), Mockito.anyString());

        subscriptionComponent.cancelSubscription("subscriptionId", "cancel");

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).cancelSubscription(Mockito.anyString(), Mockito.anyString());

    }

    @Test
    void testCancelSubscriptionServiceException() {

        Mockito.doThrow(new RuntimeException()).when(externalSubscriptionService).cancelSubscription(Mockito.anyString(), Mockito.anyString());

        assertThrows(RuntimeException.class, () -> {
            subscriptionComponent.cancelSubscription("subscriptionId", "cancel");
        });

        Mockito.verify(externalSubscriptionService, Mockito.times(1)).cancelSubscription(Mockito.anyString(), Mockito.anyString());

    }
}
