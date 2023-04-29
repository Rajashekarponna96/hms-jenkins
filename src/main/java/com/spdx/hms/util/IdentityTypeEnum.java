package com.spdx.hms.util;

import java.util.Optional;
import java.util.stream.Stream;

public enum IdentityTypeEnum {
    AADHAR("1"),
    PAN("2");
    private String value;

    IdentityTypeEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Optional<String> get(String alternateId) {
        return Stream.of(IdentityTypeEnum.values())
                .filter(s->s.name().equalsIgnoreCase(alternateId) || s.getValue().equalsIgnoreCase(alternateId))
                .map(IdentityTypeEnum::getValue)
                .findFirst();
    }
}
