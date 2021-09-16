package com.demo.publicserviceapi.controller;


import com.demo.publicserviceapi.annotations.IsEnum;
import com.demo.publicserviceapi.annotations.Required;
import com.demo.publicserviceapi.component.SubscriptionComponent;
import com.demo.publicserviceapi.enums.SubscriptionAction;
import com.demo.publicserviceapi.model.Response;
import com.demo.publicserviceapi.model.Status;
import com.demo.publicserviceapi.model.request.CreateSubscriptionRequest;
import com.demo.publicserviceapi.model.response.SubscriptionCreateResponse;
import com.demo.publicserviceapi.model.response.SubscriptionListResponse;
import com.demo.publicserviceapi.model.response.SubscriptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants.*;

@Validated
@RestController
@CrossOrigin
@RequestMapping("/v1")
@RequiredArgsConstructor
@ResponseBody
public class SubscriptionController {

    private final SubscriptionComponent subscriptionComponent;


    @GetMapping(path = "/subscriptions")
    @ResponseStatus(HttpStatus.OK)
    public Response<SubscriptionListResponse> getSubscriptions(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        SubscriptionListResponse response = subscriptionComponent.getSubscriptions(page, size);
        return new Response<>(new Status(SUCCESS), response);
    }

    @GetMapping(path = "/subscriptions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<SubscriptionResponse> getSubscription(@PathVariable String id) {
        SubscriptionResponse response = subscriptionComponent.getSubscription(id);
        return new Response<>(new Status(SUCCESS), response);
    }

    @PostMapping(path = "/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<SubscriptionCreateResponse> createSubscription(@Valid @RequestBody CreateSubscriptionRequest request) {
        SubscriptionCreateResponse response = subscriptionComponent.createSubscription(request);
        return new Response<>(new Status(SUCCESS), response);
    }

    @PutMapping(path = "/subscriptions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelSubscription(
            @PathVariable String id,
            @RequestParam(name = "action", required = false)
            @Required(exception = ACTION_IS_REQUIRED)
            @IsEnum(enumClass = SubscriptionAction.class, exception = ACTION_IS_INVALID)
                    String action) {
        subscriptionComponent.cancelSubscription(id, action);
    }

}
