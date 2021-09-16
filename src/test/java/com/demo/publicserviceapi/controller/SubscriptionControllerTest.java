package com.demo.publicserviceapi.controller;


import com.demo.publicserviceapi.component.SubscriptionComponent;
import com.demo.publicserviceapi.model.Response;
import com.demo.publicserviceapi.model.request.CreateSubscriptionRequest;
import com.demo.publicserviceapi.model.response.SubscriptionCreateResponse;
import com.demo.publicserviceapi.model.response.SubscriptionListResponse;
import com.demo.publicserviceapi.model.response.SubscriptionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SubscriptionControllerTest {

    @Mock
    private SubscriptionComponent subscriptionComponent;

    @InjectMocks
    private SubscriptionController subscriptionController;

    private SubscriptionListResponse subscriptionListResponse;
    private SubscriptionResponse subscriptionResponse;

    @BeforeEach
    public void setUp() {

        subscriptionListResponse = new SubscriptionListResponse();
        subscriptionResponse = new SubscriptionResponse();
    }

    @Test
    void testGetSubscriptionsSuccess() {

        Mockito.when(subscriptionComponent.getSubscriptions(Mockito.anyInt(), Mockito.anyInt())).thenReturn(subscriptionListResponse);

        Response<SubscriptionListResponse> response = subscriptionController.getSubscriptions(0, 10);

        Mockito.verify(subscriptionComponent, Mockito.times(1)).getSubscriptions(Mockito.anyInt(), Mockito.anyInt());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatus());
        Assertions.assertEquals("0", response.getStatus().getCode());
        Assertions.assertEquals("Success", response.getStatus().getMessage());
        Assertions.assertNotNull(response.getData());

    }

    @Test
    void testGetSubscriptionsComponentException() {

        Mockito.when(subscriptionComponent.getSubscriptions(Mockito.anyInt(), Mockito.anyInt())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            Response<SubscriptionListResponse> response = subscriptionController.getSubscriptions(0, 10);
        });

        Mockito.verify(subscriptionComponent, Mockito.times(1)).getSubscriptions(Mockito.anyInt(), Mockito.anyInt());

    }

    @Test
    void testGetSubscriptionSuccess() {

        Mockito.when(subscriptionComponent.getSubscription(Mockito.anyString())).thenReturn(subscriptionResponse);

        Response<SubscriptionResponse> response = subscriptionController.getSubscription("subscriptionId");

        Mockito.verify(subscriptionComponent, Mockito.times(1)).getSubscription(Mockito.anyString());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatus());
        Assertions.assertEquals("0", response.getStatus().getCode());
        Assertions.assertEquals("Success", response.getStatus().getMessage());
        Assertions.assertNotNull(response.getData());

    }

    @Test
    void testGetSubscriptionComponentException() {

        Mockito.when(subscriptionComponent.getSubscription(Mockito.anyString())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            Response<SubscriptionResponse> response = subscriptionController.getSubscription("subscriptionId");
        });

        Mockito.verify(subscriptionComponent, Mockito.times(1)).getSubscription(Mockito.anyString());

    }

    @Test
    void testCreateSubscriptionSuccess() {

        Mockito.when(subscriptionComponent.createSubscription(Mockito.any(CreateSubscriptionRequest.class))).thenReturn(Mockito.any(SubscriptionCreateResponse.class));

        Response<SubscriptionCreateResponse> response = subscriptionController.createSubscription(new CreateSubscriptionRequest());

        Mockito.verify(subscriptionComponent, Mockito.times(1)).createSubscription(Mockito.any(CreateSubscriptionRequest.class));

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatus());
        Assertions.assertEquals("0", response.getStatus().getCode());
        Assertions.assertEquals("Success", response.getStatus().getMessage());

    }

    @Test
    void testCreateSubscriptionComponentException() {

        Mockito.when(subscriptionComponent.createSubscription(Mockito.any(CreateSubscriptionRequest.class))).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> {
            Response<SubscriptionCreateResponse> response = subscriptionController.createSubscription(new CreateSubscriptionRequest());
        });

        Mockito.verify(subscriptionComponent, Mockito.times(1)).createSubscription(Mockito.any(CreateSubscriptionRequest.class));

    }

    @Test
    void testCancelSubscriptionSuccess() {

        Mockito.doNothing().when(subscriptionComponent).cancelSubscription(Mockito.anyString(), Mockito.anyString());

        subscriptionController.cancelSubscription("subscriptionId", "cancel");

        Mockito.verify(subscriptionComponent, Mockito.times(1)).cancelSubscription(Mockito.anyString(), Mockito.anyString());

    }

    @Test
    void testCancelSubscriptionComponentException() {

        Mockito.doThrow(new RuntimeException()).when(subscriptionComponent).cancelSubscription(Mockito.anyString(), Mockito.anyString());

        assertThrows(RuntimeException.class, () -> {
            subscriptionController.cancelSubscription("subscriptionId", "cancel");
        });

        Mockito.verify(subscriptionComponent, Mockito.times(1)).cancelSubscription(Mockito.anyString(), Mockito.anyString());

    }

}
