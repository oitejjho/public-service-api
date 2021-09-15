package com.demo.publicserviceapi.converter;

import com.demo.publicserviceapi.model.external.response.ExternalSubscription;
import com.demo.publicserviceapi.model.response.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public abstract class ExternalSubscriptionResponseToSubscriptionResponseConverter implements Converter<ExternalSubscription, SubscriptionResponse> {

    public abstract SubscriptionResponse convert(ExternalSubscription source);

}
