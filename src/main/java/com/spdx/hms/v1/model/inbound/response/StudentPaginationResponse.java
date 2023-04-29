package com.spdx.hms.v1.model.inbound.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentPaginationResponse {
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<StudentResponse> students;
}
