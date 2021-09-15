package com.demo.publicserviceapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants;

@Getter
@AllArgsConstructor
public class Status {

    private String code;

    private String message;

    public Status(HttpConstants httpConstantsExpect) {
        this.code = httpConstantsExpect.getCode();
        this.message = httpConstantsExpect.getDesc();
    }

}
