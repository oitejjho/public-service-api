package com.demo.publicserviceapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum Gender implements BaseEnum {

    FEMALE("female"),

    MALE("male"),

    OTHERS("others");

    private final String value;

    public static Optional<Gender> find(String value) {
        return Arrays.stream(values())
                .filter(item -> item.getValue().equals(value))
                .findFirst();
    }

}
