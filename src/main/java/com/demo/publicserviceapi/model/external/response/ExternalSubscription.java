package com.demo.publicserviceapi.model.external.response;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExternalSubscription {

    private String subscriptionId;

    private String email;

    private String firstName;

    private String gender;

    private String dateOfBirth;

    private Boolean consentFlag;

    private String newsletterId;

    private Boolean activeFlag;

    private LocalDateTime created;

}
