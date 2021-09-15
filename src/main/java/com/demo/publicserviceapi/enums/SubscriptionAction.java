package com.demo.publicserviceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum SubscriptionAction implements BaseEnum {

    CANCEL("cancel");

    private final String value;

    public static Optional<SubscriptionAction> find(String value) {
        return Arrays.stream(values())
                .filter(item -> item.getValue().equals(value))
                .findFirst();
    }

}
