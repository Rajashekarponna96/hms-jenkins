package com.spdx.hms.mapper;

import java.util.Optional;

public interface IOptionalMapper {

    default <T> T fromOptional(Optional<T> o) {
        return o.orElse(null);
    }

    default <T> Optional<T> toOptional(T value) {
        return Optional.ofNullable(value);
    }
}
