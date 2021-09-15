package com.demo.publicserviceapi.model.external;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants;

@Getter
@AllArgsConstructor
public class ExternalStatus {

    private String code;

    private String message;

    public ExternalStatus(HttpConstants httpConstantsExpect) {
        this.code = httpConstantsExpect.getCode();
        this.message = httpConstantsExpect.getDesc();
    }

}
