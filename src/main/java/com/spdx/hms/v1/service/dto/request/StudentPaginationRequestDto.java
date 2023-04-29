package com.spdx.hms.v1.service.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;
import java.util.Map;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentPaginationRequestDto extends AbstractPaginationRequestDto {

}
