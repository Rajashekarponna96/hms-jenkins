package com.spdx.hms.mapper;

public interface IDefaultMapper {
    default Long map(String value) {
        return value == null ? null : Long.valueOf(value);
    }

    default String map(Long value) {
        return value == null ? null : String.valueOf(value);
    }
}
