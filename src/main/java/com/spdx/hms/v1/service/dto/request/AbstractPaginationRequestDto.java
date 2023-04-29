package com.spdx.hms.v1.service.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AbstractPaginationRequestDto {
    Integer page;
    Integer size;
    Collection<String> sortFields;
    Integer direction;
    Map<String, String> projectionFields;

}
