package com.demo.publicserviceapi.converter;

import com.demo.publicserviceapi.model.external.response.SubscriptionCreate;
import com.demo.publicserviceapi.model.response.SubscriptionCreateResponse;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;


@Mapper(componentModel = "spring")
public abstract class SubscriptionCreateToSubscriptionCreateResponseConverter implements Converter<SubscriptionCreate, SubscriptionCreateResponse> {

    public abstract SubscriptionCreateResponse convert(SubscriptionCreate source);

}
