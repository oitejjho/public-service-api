package com.demo.publicserviceapi.exceptions;

import lombok.Getter;

import static com.demo.publicserviceapi.constants.StatusConstants.HttpConstants;


@Getter
public class InvalidRequestException extends RuntimeException {

    private final HttpConstants status;

    public InvalidRequestException(HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }

}
