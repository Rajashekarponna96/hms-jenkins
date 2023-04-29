package com.spdx.hms.v1.model.inbound.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Collection;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AppliedCollegesPaginationResponse {
    Integer currentPage;
    Long totalItems;
    Integer totalPages;
    Collection<AppliedCollegesResponse> appliedColleges;
}
