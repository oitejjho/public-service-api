package com.demo.publicserviceapi.model.external.response;


import com.demo.publicserviceapi.model.external.ExternalStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExternalSubscriptionsResponse {

    private ExternalSubscriptionList data;

    private ExternalStatus status;

}
